# Loan Repayment Management System
A simple Loan Payment System built with Spring Boot, H2 Database, JPA, and Lombok, implementing:
* Loan domain (create & view loans)
* Payment domain (record payments, reduce balances)
* DTO validation and centralized error handling

# Features
- Loan creation & retrieval
- Payment processing with balance reduction
- Centralized error handling with GlobalExceptionHandler
- H2 in-memory persistence
- Unit tests for core services

# How to Run the Project
# Prerequisites
* Java 17 +
* Maven 3.x
* Git
* Postman / curl for API testing

1. Clone the repository
```
git clone https://github.com/Tim-dev-lab/loan-system.git
cd loan-system  
```
2. Run 
```
 mvn clean install
 mvn spring-boot:run
```
- Application runs on: http://localhost:8080

3. DB
- H2 Console (in-memory DB) available at: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:loandb`
- Username: `radix`
- Password: `radix`


# API Endpoints 
All requests/responses are JSON. DTO validation is applied — invalid inputs return structured errors.


## 1. Create Loan
### Request: 

**POST** `/loans`
#### Postman Request
```json
{
  "loanAmount": 800,
  "term": 3
}
```
#### curl Request
```bash

curl -X POST http://localhost:8080/loans \
-H "Content-Type: application/json" \
-d '{"loanAmount": 800, "term": 3}'
```
### Response:
```
{
  "loanId": "uuid",
  "loanAmount": 800,
  "remainingBalance": 800,
  "term": 3,
  "status": "ACTIVE",
  "createdAt": "2026-02-28T15:50:00"
}
```
### Validation Errors Example:
```
{
  "message": "Loan amount must be greater than zero",
  "timestamp": "2026-02-28T15:51:00"
}
```

## 2. Get Loan Details
### Request:

**GET** `/loans/{loanId}`
#### Postman Request
```
http://localhost:8080/loans/{loanId}
```

#### curl Request
```bash

curl -X GET http://localhost:8080/loans/{loanId}
```

### Successful Response:
```
{
  "loanId": "c2f9e9a1-1234-4567-890a-bcdef1234567",
  "loanAmount": 800.0,
  "remainingBalance": 800.0,
  "term": 3,
  "status": "ACTIVE",
  "createdAt": "2026-03-01T12:00:00"
}
```

### Validation Errors Example (Not Found Example):
```
{
  "message": "Loan not found",
  "timestamp": "2026-03-01T12:05:00"
}
```

## 3. Make Payment

### Endpoint:

**POST** `/payments`
#### Postman Request
```
{
  "loanId": "c2f9e9a1-1234-4567-890a-bcdef1234567",
  "paymentAmount": 300
}
```

#### curl Request
```bash

curl -X POST http://localhost:8080/payments \
-H "Content-Type: application/json" \
-d '{"loanId": "c2f9e9a1-1234-4567-890a-bcdef1234567", "paymentAmount": 300}'
```

### Successful Response:
```
{
  "paymentId": "f8a2c123-9876-4321-1111-abcdef123456",
  "loanId": "c2f9e9a1-1234-4567-890a-bcdef1234567",
  "paymentAmount": 300.0,
  "createdAt": "2026-03-01T12:10:00"
}
```

### Validation Errors Example :

Over payment
```
{
  "message": "Payment exceeds remaining balance",
  "timestamp": "2026-03-01T12:15:00"
}
```
Invalid amount
```
{
  "message": "Payment amount must be greater than zero",
  "timestamp": "2026-03-01T12:16:00"
}
```
## Notes

- Payments reduce the remaining loan balance.
- The loan term is informational and does not automatically adjust based on payments.
- Amortization or schedule recalculation can be added as a future enhancement.

# Testing Flow

1. Create a loan
2. Copy the returned loanId
3. Make a partial payment
4. Retrieve loan - confirm balance reduced
5. Make final payment - confirm status becomes SETTLED
6. Try overpayment - confirm error response
