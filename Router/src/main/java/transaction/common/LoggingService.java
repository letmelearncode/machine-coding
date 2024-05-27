package transaction.common;

import java.util.logging.Logger;

public class LoggingService {
  private static final Logger logger = Logger.getLogger(LoggingService.class.getName());

  public void log(String transactionId, double amount, String gatewayId, String message) {
    String logEntry = String.format("Transaction ID: %s, Transaction ID: %s,Gateway ID: %s, Message: %s", transactionId, amount,gatewayId, message);
    // Log to console for simplicity, can be extended to log to a persistent storage
    logger.info(logEntry);
  }
}

