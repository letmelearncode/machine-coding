package org.example.service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import org.example.models.PaymentGateway;
import org.example.models.Transaction;


// Class representing a Payment Gateway Load Balancer for Razorpay
public class WeightedLoadBalancer {

  // Define weights for payment gateways (for demonstration purposes)
  private static final Map<PaymentGateway, Integer> DEFAULT_WEIGHTS = new HashMap<>();

  static {
    DEFAULT_WEIGHTS.put(PaymentGateway.GATEWAY_A, 2);
    DEFAULT_WEIGHTS.put(PaymentGateway.GATEWAY_B, 3);
    DEFAULT_WEIGHTS.put(PaymentGateway.GATEWAY_C, 5);
  }

  private Map<PaymentGateway, Integer> weights;
  private List<PaymentGateway> gatewayList;
  private Map<PaymentGateway, Integer> gatewayIndexMap;
  private Map<PaymentGateway, Integer> successCount;
  private Map<PaymentGateway, Integer> totalCount;

  // Map to store gateway health status
  private Map<PaymentGateway, Boolean> gatewayHealth;

  public WeightedLoadBalancer() {
    this(DEFAULT_WEIGHTS);
  }

  public WeightedLoadBalancer(Map<PaymentGateway, Integer> weights) {
    this.weights = weights;
    this.gatewayList = new ArrayList<>();
    this.gatewayIndexMap = new HashMap<>();
    this.successCount = new HashMap<>();
    this.totalCount = new HashMap<>();


    // Initialize gateway list and index map
    int index = 0;
    for (PaymentGateway gateway : weights.keySet()) {
      gatewayList.add(gateway);
      gatewayIndexMap.put(gateway, index++);
      successCount.put(gateway, 0);
      totalCount.put(gateway, 0);
      gatewayHealth.put(gateway, true);
    }
  }

  // Route transaction to the appropriate payment gateway for Razorpay
  public PaymentGateway routeTransaction(Transaction transaction) {
    PaymentGateway selectedGateway = selectGateway();
    // Process transaction using the selected gateway
    boolean success = processTransaction(selectedGateway, transaction);
    // Log transaction stats
    logTransaction(selectedGateway, success);
    return selectedGateway;
  }

  // Select payment gateway using weighted load balancing
  private PaymentGateway selectGateway() {
    // Implement weighted load balancing algorithm here
    int totalWeight = weights.values().stream().mapToInt(Integer::intValue).sum();
    int randomNumber = ThreadLocalRandom.current().nextInt(totalWeight);
    int sum = 0;
    for (PaymentGateway gateway : gatewayList) {
      sum += weights.get(gateway);
      if (randomNumber < sum) {
        return gateway;
      }
    }
    return null; // Should not reach here
  }

  // Process transaction using the selected payment gateway
  private boolean processTransaction(PaymentGateway gateway, Transaction transaction) {
    // Implement actual transaction processing logic here
    // For demonstration, we'll simulate transaction success with 90% probability
    return Math.random() < 0.9;
  }

  // Log transaction success/failure and update counters
  private void logTransaction(PaymentGateway gateway, boolean success) {
    if (success) {
      successCount.put(gateway, successCount.get(gateway) + 1);
    }
    totalCount.put(gateway, totalCount.get(gateway) + 1);
    System.out.println("Transaction routed to " + gateway + ". Success: " + success);
  }


  // Method to perform health checks and adjust gateway health status
  private void performHealthChecks() {
    for (PaymentGateway gateway : gatewayList) {
      boolean isHealthy = checkHealth(gateway);
      if (!isHealthy) {
        // Mark gateway as unhealthy
        gatewayHealth.put(gateway, false);
        System.out.println("Gateway " + gateway + " is unhealthy.");
      } else {
        // Mark gateway as healthy
        gatewayHealth.put(gateway, true);
      }
    }
  }


  // Method to check health of a gateway
  private boolean checkHealth(PaymentGateway gateway) {
    // Implement health check logic here
    // Return true if gateway is healthy, false otherwise
    return true;
  }

  // Method to select a healthy gateway for routing
  private PaymentGateway selectHealthyGateway() {
    for (PaymentGateway gateway : gatewayList) {
      if (gatewayHealth.getOrDefault(gateway, true)) {
        return gateway; // Return the first healthy gateway found
      }
    }
    return null; // Return null if no healthy gateway is found
  }

  // Method to route transaction with failover
  public PaymentGateway routeTransactionWithFailover(Transaction transaction) {
    performHealthChecks(); // Perform health checks before routing
    PaymentGateway selectedGateway = selectHealthyGateway();
    if (selectedGateway != null) {
      // Route transaction to a healthy gateway
      boolean success = processTransaction(selectedGateway, transaction);
      logTransaction(selectedGateway, success);
      return selectedGateway;
    } else {
      // No healthy gateway found, route to backup gateway or handle accordingly
      return PaymentGateway.BACKUP; // Assuming there is a backup gateway defined
    }
  }



}

