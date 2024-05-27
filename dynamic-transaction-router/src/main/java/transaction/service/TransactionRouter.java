package transaction.service;

import java.util.List;
import java.util.logging.Logger;
import transaction.common.LoggingService;
import transaction.models.PaymentGateway;
import transaction.models.Transaction;

public class TransactionRouter {
  private static final Logger logger = Logger.getLogger(TransactionRouter.class.getName());
  private final GatewayManager gatewayManager;
  private final LoggingService loggingService;

  public TransactionRouter(GatewayManager gatewayManager, LoggingService loggingService) {
    this.gatewayManager = gatewayManager;
    this.loggingService = loggingService;
  }

  public PaymentGateway routeTransaction(Transaction transaction) {
    List<PaymentGateway> gateways = gatewayManager.getAvailableGateways();
    PaymentGateway bestGateway = chooseBestGateway(transaction, gateways);
    logTransaction(transaction, bestGateway);
    return bestGateway;
  }

  private PaymentGateway chooseBestGateway(Transaction transaction, List<PaymentGateway> gateways) {
    PaymentGateway bestGateway = null;
    double bestScore = Double.NEGATIVE_INFINITY;
    for (PaymentGateway gateway : gateways) {
      double score = evaluateGateway(transaction, gateway);
      if (score > bestScore) {
        bestScore = score;
        bestGateway = gateway;
      }
    }
    return bestGateway;
  }

  private double evaluateGateway(Transaction transaction, PaymentGateway gateway) {
    double score = 0;
    if (gateway.getHealth().equals("healthy")) {
      score += 10;
    }
    if (gateway.getFee() < transaction.getAmount() * 0.02) {
      score += 5;
    }
    // Add more criteria as needed
    return score;
  }

  private void logTransaction(Transaction transaction, PaymentGateway gateway) {
    loggingService.log(transaction.getId(),transaction.getAmount(), gateway.getId(), "Routed to gateway");
  }
}

