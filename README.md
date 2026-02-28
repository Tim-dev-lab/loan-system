# How to Run

* `mvn clean install`
* `mvn test`
* `mvn spring-boot:run`

# Test APIs

* Create Loan:

`POST/loans {
    "loanAmount": 1000,
    "term": 12
 }`

* Make Payment:

`POST/payments {
  "loanId": "UUID",
  "paymentAmount": 200
 }`

* Get Loan Details:

`GET /loans/{loanId}`