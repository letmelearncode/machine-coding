package org.example.service;

import java.util.Random;
import org.example.model.Transaction;
import org.example.util.EncryptionUtil;

public class PaymentTerminal {
  private static final String ENCRYPTION_KEY = "0123456789abcdef"; // Simplified for demonstration purposes

  public boolean processTransaction(Transaction transaction) {
    try {
      // Simulate card swipe and PIN entry
      String encryptedCardNumber = EncryptionUtil.encrypt(transaction.getCardNumber(), ENCRYPTION_KEY);
      String encryptedCVV = EncryptionUtil.encrypt(transaction.getCvv(), ENCRYPTION_KEY);

      // Simulate authorization
      boolean authorized = authorizeTransaction(transaction, encryptedCardNumber, encryptedCVV);

      // Handle authorization result
      if (authorized) {
        logTransaction(transaction, true);
        return true;
      } else {
        handleAuthorizationFailure(transaction);
        return false;
      }
    } catch (Exception e) {
      handleException(e);
      return false;
    }
  }

  private boolean authorizeTransaction(Transaction transaction, String encryptedCardNumber, String encryptedCVV) {
    // Simulate authorization process (e.g., contacting bank)
    Random random = new Random();
    return random.nextBoolean(); // Simplified for demonstration purposes
  }

  private void handleAuthorizationFailure(Transaction transaction) {
    // Implement retry mechanism and error handling
    System.out.println("Transaction authorization failed for: " + transaction.getCardHolderName());
  }

  private void logTransaction(Transaction transaction, boolean success) {
    // Log transaction details securely
    System.out.println("Transaction " + (success ? "successful" : "failed") + " for: " + transaction.getCardHolderName());
  }

  private void handleException(Exception e) {
    // Implement exception handling
    System.err.println("An error occurred: " + e.getMessage());
  }
}
