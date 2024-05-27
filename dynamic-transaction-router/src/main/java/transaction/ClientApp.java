package transaction;

import transaction.common.LoggingService;
import transaction.models.PaymentGateway;
import transaction.models.Transaction;
import transaction.service.GatewayManager;
import transaction.service.TransactionRouter;

public class ClientApp {
  public static void main(String[] args) {
    GatewayManager gatewayManager = new GatewayManager();
    LoggingService loggingService = new LoggingService();
    TransactionRouter transactionRouter = new TransactionRouter(gatewayManager, loggingService);

    // Adding gateways
    PaymentGateway gateway1 = new PaymentGateway("gateway1", 0.01);
    PaymentGateway gateway2 = new PaymentGateway("gateway2", 0.015);
    PaymentGateway gateway3 = new PaymentGateway("gateway3", 0.02);

    gatewayManager.addGateway(gateway1);
    gatewayManager.addGateway(gateway2);
    gatewayManager.addGateway(gateway3);

    // Simulate transactions
    Transaction transaction1 = new Transaction("tx1", 100.0);
    Transaction transaction2 = new Transaction("tx2", 200.0);

    PaymentGateway selectedGateway1 = transactionRouter.routeTransaction(transaction1);
    System.out.println("Transaction 1 routed to: " + selectedGateway1.getId());

    PaymentGateway selectedGateway2 = transactionRouter.routeTransaction(transaction2);
    System.out.println("Transaction 2 routed to: " + selectedGateway2.getId());
  }
}

