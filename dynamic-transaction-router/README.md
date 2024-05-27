# Dynamic Transaction Routing System

## ****Description**:**

Design and implement a dynamic transaction routing system that can route transactions to the most optimal payment gateway based on various factors such as transaction type, amount, gateway health, and processing fees.

## **Key Requirements**:
1. **Real-Time Decision Making**: The system should make routing decisions in real-time based on the given factors.
2. **Health Checks and Fallback Mechanisms**: Implement periodic health checks for all gateways and provide fallback mechanisms to reroute transactions in case of failures.
3. **Scalability**: The system should be able to handle high transaction volumes without performance degradation.
4. **Logging**: Provide comprehensive logging for all transactions to facilitate auditing and troubleshooting.

## **Skills Tested:**

### System design

1. Real-time processing
2. Decision-making algorithms
3. Fault tolerance
4. Logging
5. Scalability
6. Performance optimization


## The system consists of the following components:

1. **Transaction Router**: The main component that makes routing decisions.
   1. Receives transactions and evaluates routing criteria.
   2. Factors to consider: transaction type, amount, gateway health, processing fees.
   3. Uses a decision-making algorithm (e.g., weighted scoring system) to choose the best gateway.
2. **Gateway Manager**: Manages the health and status of payment gateways.
   1. Monitors the health of gateways using periodic health checks.
   2. Maintains a list of healthy gateways and provides this list to the Transaction Router.
3. **Logging Service**: Logs all transactions and routing decisions.
   1. Provides a centralized logging mechanism for all transactions.
   2. Stores logs in a persistent storage solution like a database or a log management system (e.g., ELK stack).
4. **Configuration Service**: Manages routing rules and configurations.
## High-Level Architecture:

                +-------------------+
                | Transaction Router |
                +--------+----------+
                         |
        +----------------+----------------+
        |                                 |
+-------+--------+                 +-------+--------+
| Gateway Manager|                 | Logging Service |
+----------------+                 +-----------------+
          |
+----------------+----------------+-----------------+
|                |                |                 |
|                |                |                 |
| Payment Gateway|                | Payment Gateway |
|       A        |                |        B        |
+----------------+                +-----------------+
