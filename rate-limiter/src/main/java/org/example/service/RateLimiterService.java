package org.example.service;

import org.example.model.TokenBucket;

public interface RateLimiterService {

  boolean handleRequest(String userId);

  TokenBucket getTokenBucket(String userId);
}
