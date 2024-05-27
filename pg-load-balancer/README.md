# Payment Gateway Load Balancer

## Overview

This project implements a Payment Gateway Load Balancer in Java. The load balancer distributes transaction requests across multiple payment gateways to prevent overload and ensure high availability. It supports round-robin or weighted load balancing algorithms and includes mechanisms for monitoring gateway performance and dynamically adjusting routing.

## Features

- **Load Balancing:** Implements round-robin and weighted load balancing algorithms to distribute transactions.
- **Fault Tolerance:** Monitors gateway performance and dynamically adjusts routing to handle failures.
- **Scalability:** Designed to handle high transaction volumes.
- **Logging:** Tracks and logs transaction distribution and success rates.

## Components

- **Transaction:** Represents a transaction to be processed.
- **PaymentGateway:** Enum representing different payment gateways.
- **RazorpayLoadBalancer:** Main class implementing the load balancer logic.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- A Java IDE or a text editor

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/payment-gateway-load-balancer.git
   ```
2. Navigate to the project directory:
   ```bash
   cd payment-gateway-load-balancer
   ```

### Running the Load Balancer

1. Compile the Java files:
   ```bash
   javac RazorpayLoadBalancer.java
   ```
2. Run the main class:
   ```bash
   java RazorpayLoadBalancer
   ```

## Code Explanation

### `PaymentGateway` Enum

```java
enum PaymentGateway {
    GATEWAY_A,
    GATEWAY_B,
    GATEWAY_C
}
```

Represents different payment gateways.

### `Transaction` Class

```java
class Transaction {
    // Transaction details
}
```

Represents a transaction to be processed.

### `RazorpayLoadBalancer` Class

#### Fields

- `weights`: A map storing the weights for each payment gateway.
- `gatewayList`: A list of payment gateways.
- `gatewayIndexMap`: A map storing the index of each gateway in the list.
- `successCount`: A map storing the success count for each gateway.
- `totalCount`: A map storing the total transaction count for each gateway.

#### Methods

- `RazorpayLoadBalancer(Map<PaymentGateway, Integer> weights)`: Constructor initializing the load balancer with the specified weights.
- `routeTransaction(Transaction transaction)`: Routes a transaction to an appropriate payment gateway.
- `selectGateway()`: Selects a payment gateway using the weighted load balancing algorithm.
- `processTransaction(PaymentGateway gateway, Transaction transaction)`: Simulates processing a transaction using the selected payment gateway.
- `logTransaction(PaymentGateway gateway, boolean success)`: Logs the transaction result and updates counters.

### Example Usage

```java
public static void main(String[] args) {
    RazorpayLoadBalancer loadBalancer = new RazorpayLoadBalancer();
    for (int i = 0; i < 10; i++) {
        loadBalancer.routeTransaction(new Transaction());
    }
}
```

## Contributing

1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature-branch
   ```
3. Make your changes and commit them:
   ```bash
   git commit -m "Add some feature"
   ```
4. Push to the branch:
   ```bash
   git push origin feature-branch
   ```
5. Open a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## Additional Notes

- **Real-World Implementation:** Replace the `processTransaction` method with actual interaction logic for real-world payment gateways.
- **Health Checks:** Implement health checks for each gateway to ensure fault tolerance.
- **Configuration Management:** Consider using a configuration management system for dynamic load balancing adjustments.

---
