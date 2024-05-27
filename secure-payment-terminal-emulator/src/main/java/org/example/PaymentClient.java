package org.example;

import org.example.api.PaymentTerminalAPI;
import org.example.model.PaymentMethod;

public class PaymentClient {
  public static void main(String[] args) {
    PaymentTerminalAPI api = new PaymentTerminalAPI();
    String result = api.initiateTransaction("1234567890123456",
        "John Doe", "12/24", "123", 100.0, PaymentMethod.CREDIT_CARD);
    System.out.println(result);
  }

}
