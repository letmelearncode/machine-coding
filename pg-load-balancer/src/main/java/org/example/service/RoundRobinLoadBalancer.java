package org.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.models.PaymentGateway;
import org.example.models.Transaction;

public class RoundRobinLoadBalancer {
  private Map<PaymentGateway, Integer> weights;
  private List<PaymentGateway> gatewayList;
  private Map<PaymentGateway, Integer> gatewayIndexMap;
  private Map<PaymentGateway, Integer> successCount;
  private Map<PaymentGateway, Integer> totalCount;

  public RoundRobinLoadBalancer(Map<PaymentGateway, Integer> weights) {
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
    }
  }

  // Route transaction to the appropriate payment gateway
  public PaymentGateway routeTransaction(Transaction transaction) {
    PaymentGateway selectedGateway = selectGateway();
    // Simulate transaction processing
    boolean success = processTransaction(selectedGateway, transaction);
    // Log transaction stats
    logTransaction(selectedGateway, success);
    return selectedGateway;
  }

  // Select payment gateway using round-robin or weighted load balancing
  private PaymentGateway selectGateway() {
    // Implement round-robin or weighted load balancing algorithm here
    // For simplicity, using round-robin in this example
    int currentIndex = totalCount.values().stream().mapToInt(Integer::intValue).sum() % gatewayList.size();
    return gatewayList.get(currentIndex);
  }

  // Process transaction using the selected payment gateway
  private boolean processTransaction(PaymentGateway gateway, Transaction transaction) {
    // Simulate transaction processing
    // Return true if transaction succeeds, false otherwise
    return true;
  }

  // Log transaction success/failure and update counters
  private void logTransaction(PaymentGateway gateway, boolean success) {
    if (success) {
      successCount.put(gateway, successCount.get(gateway) + 1);
    }
    totalCount.put(gateway, totalCount.get(gateway) + 1);
    System.out.println("Transaction routed to " + gateway + ". Success: " + success);
  }

}
