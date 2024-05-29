# Rate Limiter

This repository contains a Java implementation of a rate limiter using the Token Bucket algorithm. The rate limiter is designed to control the rate of requests to a public API, ensuring that a user can only make a limited number of requests within a specified time frame.

## Features

- **Token Bucket Algorithm**: Implements a rate limiter using the token bucket algorithm.
- **Concurrency Support**: Uses `ConcurrentHashMap` for thread-safe handling of user buckets.
- **Customizable Parameters**: Easily configurable maximum tokens and refill rate.
- **Rate Limiting**: Returns "429 Too Many Requests" when the rate limit is exceeded.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- An IDE or text editor of your choice

### Running the Code

1. **Clone the repository**:
    ```sh
    git clone https://github.com/yourusername/rate-limiter.git
    cd rate-limiter
    ```

2. **Compile the Java code**:
    ```sh
    javac RateLimiter.java
    ```

3. **Run the Java application**:
    ```sh
    java RateLimiter
    ```

### Example Output

The main method simulates requests from a single user with a 10 ms interval between requests. The expected output will be:
