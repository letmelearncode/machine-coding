package org.example.controller;

import java.time.Instant;
import org.example.service.RateLimiterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RateLimiterApiController {

  private static final Logger logger = LoggerFactory.getLogger(RateLimiterApiController.class);


  private final RateLimiterService rateLimiter;
  public RateLimiterApiController(RateLimiterService rateLimiter) {
    this.rateLimiter = rateLimiter;
  }

  @RequestMapping(path = "/resource")
  public ResponseEntity<String> getResource(@RequestParam String userId) {
    if (rateLimiter.handleRequest(userId)) {
      logger.info("request allowed for userId : "+ userId+" at "+ Instant.now()+" "+rateLimiter.getTokenBucket(userId));
      return ResponseEntity.ok("Request allowed");
    } else {
      logger.info("request denied for userId : "+ userId+" at "+Instant.now()+" "+rateLimiter.getTokenBucket(userId));
      return ResponseEntity.status(429).body("Too Many Requests");
    }
  }

}
