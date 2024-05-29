package org.example.service;

import java.util.concurrent.ConcurrentHashMap;
import org.example.model.TokenBucket;
import org.springframework.stereotype.Service;

@Service
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
    int refillTokens = (int) (elapsedTime * bucket.getRefillRate()) /1000;
    System.out.println("Available refill tokens after  :"+ elapsedTime + "ms: " + refillTokens);
    if (refillTokens > 0) {
      bucket.setTokens(Math.min(bucket.getMaxTokens(), bucket.getTokens() + refillTokens));
      bucket.setLastRefillTimestamp(now);
    }
  }

  @Override
  public boolean handleRequest(String userId) {
    userBuckets.putIfAbsent(userId, new TokenBucket(10, 1));
    TokenBucket bucket = userBuckets.get(userId);
    return allowRequest(bucket);
  }

  @Override
  public TokenBucket getTokenBucket(String userId) {
    return userBuckets.get(userId);
  }
}
