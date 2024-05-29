package org.example.service;

import java.util.concurrent.ConcurrentHashMap;
import org.example.model.TokenBucket;

public class TokenBucketBasedRateLimiter implements RateLimiterService {

  private final ConcurrentHashMap<String, TokenBucket> userBuckets = new ConcurrentHashMap<>();

  public synchronized boolean allowRequest(TokenBucket bucket) {
    refillTokens(bucket);
    if (bucket.getTokens() >= 1) {
      bucket.setTokens(bucket.getTokens() - 1);
      return true;
    } else {
      return false;
    }
  }

  private void refillTokens(TokenBucket bucket) {
    long now = System.currentTimeMillis();
    long elapsedTime = now - bucket.getLastRefillTimestamp();
    double refillTokens = elapsedTime / 1000 * bucket.getRefillRate();
    if (refillTokens > 0) {
      bucket.setTokens(Math.min(bucket.getMaxTokens(), bucket.getTokens()) + refillTokens);
      bucket.setLastRefillTimestamp(now);
    }
  }

  @Override
  public String handleRequest(String userId) {
    userBuckets.putIfAbsent(userId, new TokenBucket(10, 10));
    TokenBucket bucket = userBuckets.get(userId);
    if (allowRequest(bucket)) {
      return "Request allowed";
    } else {
      return "429 Too Many Requests";
    }
  }
}
