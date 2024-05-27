package org.example.model;

public class Transaction {
  private String cardNumber;
  private String cardHolderName;
  private String expiryDate;
  private String cvv;
  private double amount;
  private PaymentMethod paymentMethod;

  // Constructor, getters, and setters

  public Transaction(String cardNumber, String cardHolderName, String expiryDate, String cvv, double amount, PaymentMethod paymentMethod) {
    this.cardNumber = cardNumber;
    this.cardHolderName = cardHolderName;
    this.expiryDate = expiryDate;
    this.cvv = cvv;
    this.amount = amount;
    this.paymentMethod = paymentMethod;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public String getCardHolderName() {
    return cardHolderName;
  }

  public String getExpiryDate() {
    return expiryDate;
  }

  public String getCvv() {
    return cvv;
  }

  public double getAmount() {
    return amount;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }
}

