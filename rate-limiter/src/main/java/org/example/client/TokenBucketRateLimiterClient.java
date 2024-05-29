package org.example.client;

import java.util.concurrent.TimeUnit;
import org.example.service.RateLimiterService;
import org.example.service.TokenBucketBasedRateLimiter;

public class TokenBucketRateLimiterClient {

  public static void main(String[] args) throws InterruptedException {
    RateLimiterService rateLimiter = new TokenBucketBasedRateLimiter();
    // Simulate requests from the same user
    String userId = "user_123";
    for (int i = 0; i < 15; i++) {
      System.out.println(rateLimiter.handleRequest(userId));
      // Sleep for a short duration to simulate time passing
      TimeUnit.MILLISECONDS.sleep(10);
    }
  }

}
