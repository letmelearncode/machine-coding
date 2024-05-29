package com.example.model;

public class TokenBucket {
    private final long maxTokens;
    private final long refillRate; // tokens per second
    private long tokens;
    private long lastRefillTimestamp;

    public TokenBucket(long maxTokens, long refillRate) {
      this.maxTokens = maxTokens;
      this.refillRate = refillRate;
      this.tokens = maxTokens;
      this.lastRefillTimestamp = System.currentTimeMillis();
    }

  public long getMaxTokens() {
    return maxTokens;
  }

  public long getRefillRate() {
    return refillRate;
  }

  public long getTokens() {
    return tokens;
  }

  public void setTokens(long tokens) {
    this.tokens = tokens;
  }

  public long getLastRefillTimestamp() {
    return lastRefillTimestamp;
  }

  public void setLastRefillTimestamp(long lastRefillTimestamp) {
    this.lastRefillTimestamp = lastRefillTimestamp;
  }

  @Override
  public String toString() {
    return "TokenBucket: {" +
        "maxTokens=" + maxTokens +
        ", refillRate=" + refillRate +
        ", tokens=" + tokens +
        ", lastRefillTimestamp=" + lastRefillTimestamp +
        '}';
  }
}
