package org.example.client;

import java.util.HashMap;
import java.util.Map;
import org.example.models.PaymentGateway;
import org.example.models.Transaction;
import org.example.service.RoundRobinLoadBalancer;

public class Client {


  public static void main(String[] args) {
    // Example weights for payment gateways
    Map<PaymentGateway, Integer> weights = new HashMap<>();
    weights.put(PaymentGateway.GATEWAY_A, 2);
    weights.put(PaymentGateway.GATEWAY_B, 3);
    weights.put(PaymentGateway.GATEWAY_C, 5);

    RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer(weights);
    for (int i = 0; i < 10; i++) {
      loadBalancer.routeTransaction(new Transaction());
    }
  }

}
