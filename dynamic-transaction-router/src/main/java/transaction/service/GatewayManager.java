package transaction.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import transaction.models.PaymentGateway;

public class GatewayManager {
  private final ConcurrentHashMap<String, PaymentGateway> gateways = new ConcurrentHashMap<>();

  public void addGateway(PaymentGateway gateway) {
    gateways.put(gateway.getId(), gateway);
    updateHealthStatus(gateway);
  }

  public List<PaymentGateway> getAvailableGateways() {
    List<PaymentGateway> availableGateways = new ArrayList<>();
    for (PaymentGateway gateway : gateways.values()) {
      if (gateway.getHealth().equals("healthy")) {
        availableGateways.add(gateway);
      }
    }
    return availableGateways;
  }

  public void updateHealthStatus(PaymentGateway gateway) {
    gateway.setHealth(checkHealth(gateway));
  }

  private String checkHealth(PaymentGateway gateway) {
    // Perform health check (e.g., ping or API call)
    try {
      // Simulate health check
      boolean isHealthy = true; // Replace with actual health check logic
      if (isHealthy) {
        return "healthy";
      }
    } catch (Exception e) {
      return "unhealthy";
    }
    return "unhealthy";
  }
}

