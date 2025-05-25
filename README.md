# Finance-Management
Finance management application developed using micro services architecture


A Spring Boot-based microservices application that helps users manage personal finances by creating budgets, tracking expenses, and receiving notifications when budgets are exceeded.

---

## 📌 Requirements

- Java 17+
- Maven
- Postman (for API testing)

---

## 📦 Microservices Overview

| Service Name        | Description                                      |
|---------------------|--------------------------------------------------|
| `user-service`    | Manage user details
| `budget-service`    | Manage user-defined budget categories and limits |
| `expense-service`   | Record and track expenses across categories      |
| `notification-service` | Sends alerts when expenses exceed budgets     |
| `eureka-server` | Service Discovery via Eureka |

---

## 🔄 Inter-Service Communication

Services communicate via REST using `Feign Client` with resilience patterns (like retries and circuit breakers) implemented where necessary.

---

## 💡 Features

- Set and manage budgets
- Add and track expenses
- Trigger notifications when budgets are exceeded.
- Domain-driven design
- Spring Data JPA with H2 in-memory DB
- Open API (no authentication)
- Swagger UI & Postman collection included

---

## 🚀 Getting Started

### 1. Clone the repository
```bash
https://github.com/ht-yashwanthkumar/Finance-Management.git
cd finance-management    
```
### 2. Build and Run Services
Each microservice is a separate Spring Boot project. You can start them individually:
```bash
cd budget-service
mvn spring-boot:run
```
#### Repeat the above for:
- expense-service
- notification-service
- user-service
- eureka-server
##### Ensure ports don't conflict. Each service should run on a different port.

### 3. Access Swagger UI
- notification_service : http://localhost:6060/swagger-ui/index.html
- user_service : http://localhost:7070/swagger-ui/index.html
- budget_service : http://localhost:8080/swagger-ui/index.html
- expense_service : http://localhost:9090/swagger-ui/index.html
- eureka_server : http://localhost:8761/

---
## 📬 Postman Collection
  - Use the provided [Postman collection](https://github.com/ht-yashwanthkumar/Finance-Management/blob/main/postman_and_swagger_specs/Finance%20Manage%20Application.postman_collection.json) to test APIs.
---

## 🧪 Testing
```bash
mvn test
```
Each service contains unit tests for major business logic and API endpoints.

## 🧱 Design Principles
- API-First or Code-First approach (as chosen)
- Domain-driven design with clear service boundaries
- RESTful best practices (status codes, resource naming)
- Resilience through retry/circuit breaker patterns
---
##  📊 Assessment Criteria Coverage
| Criteria                          | Coverage |
| --------------------------------- | -------- |
| Well-defined domain boundaries    | ✅        |
| Inter-service communication       | ✅        |
| Domain-driven database design     | ✅        |
| Failure handling/resilience       | ✅        |
| Application starts without errors | ✅        |
| Completion of essential APIs      | ✅        |
| REST best practices               | ✅        |
| Clean code                        | ✅        |
| Unit tests and documentation      | ✅        |
---
## 📁 Folder Structure (Example)
````bash
finance-manager/

├── budget-service/
├── eureka-server/
├── expense-service/
├── notification-service/
├── postman_and_swagger_specs/
│   └── budget_service_swagger.json
│   └── expense_service_swagger.json
│   └── Finance Manage Application.postman_collection.json
│   └── notification_service_swagger.json
│   └── user_service_swagger.json
├── user-service/
└── README.md
````

## 🔗 Author
👤 Yashwanth Kumar HT <br/>
📧 ht.yashwanthkumar@gmail.com <br/>
 🌐 https://github.com/ht-yashwanthkumar <br/>


   





