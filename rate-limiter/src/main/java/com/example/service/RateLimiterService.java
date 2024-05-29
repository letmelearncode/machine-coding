package com.example.service;

import com.example.model.TokenBucket;

public interface RateLimiterService {

  boolean handleRequest(String userId);

  TokenBucket getTokenBucket(String userId);
}
