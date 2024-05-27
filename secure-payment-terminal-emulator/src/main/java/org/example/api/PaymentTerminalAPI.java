package org.example.api;

import java.util.HashMap;
import java.util.Map;
import org.example.model.PaymentMethod;
import org.example.model.Transaction;
import org.example.service.PaymentTerminal;

public class PaymentTerminalAPI {

  private PaymentTerminal paymentTerminal;
  private Map<String, Transaction> transactionHistory;

  public PaymentTerminalAPI() {
    this.paymentTerminal = new PaymentTerminal();
    this.transactionHistory = new HashMap<>();
  }

  public String initiateTransaction(String cardNumber, String cardHolderName, String expiryDate,
      String cvv, double amount, PaymentMethod paymentMethod) {
    Transaction transaction = new Transaction(cardNumber, cardHolderName, expiryDate, cvv, amount,
        paymentMethod);
    boolean success = paymentTerminal.processTransaction(transaction);
    String transactionId = generateTransactionId();
    transactionHistory.put(transactionId, transaction);
    return transactionId + ": " + (success ? "Success" : "Failure");
  }

  private String generateTransactionId() {
    return "TXN" + System.currentTimeMillis();
  }

  public Transaction getTransaction(String transactionId) {
    return transactionHistory.get(transactionId);
  }
}

