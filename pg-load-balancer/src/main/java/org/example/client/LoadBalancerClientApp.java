package org.example.client;

import org.example.models.Transaction;
import org.example.service.WeightedLoadBalancer;

public class LoadBalancerClientApp {


  // Main method for testing Razorpay Load Balancer
  public static void main(String[] args) {
    WeightedLoadBalancer loadBalancer = new WeightedLoadBalancer();
    for (int i = 0; i < 10; i++) {
      loadBalancer.routeTransaction(new Transaction());
    }
  }




}
