package org.example.model;

public class TokenBucket {
    private final long maxTokens;
    private final long refillRate; // tokens per second
    private double tokens;
    private long lastRefillTimestamp;

    public TokenBucket(long maxTokens, long refillRate) {
      this.maxTokens = maxTokens;
      this.refillRate = refillRate;
      this.tokens = maxTokens;
      this.lastRefillTimestamp = System.nanoTime();
    }

  public long getMaxTokens() {
    return maxTokens;
  }

  public long getRefillRate() {
    return refillRate;
  }

  public double getTokens() {
    return tokens;
  }

  public void setTokens(double tokens) {
    this.tokens = tokens;
  }

  public long getLastRefillTimestamp() {
    return lastRefillTimestamp;
  }

  public void setLastRefillTimestamp(long lastRefillTimestamp) {
    this.lastRefillTimestamp = lastRefillTimestamp;
  }
}
