# 💰Finance-Management
A Spring Boot-based microservices application designed to help users effectively manage their personal finances.
## 🧩 Key Features

- Create and manage budgets
- Track expenses in real time
- Receive notifications when budgets are exceeded

This application is built using the Microservices Architecture, promoting modularity, scalability, and ease of maintenance.

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
| `api-gateway` | Gateway service to route APIs and to introduce cross cutting concerns |

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
cd eureka-server
mvn spring-boot:run
```
#### Repeat the above for:
- expense-service
- notification-service
- user-service
- budget-service
- api-gateway
  
##### Ensure ports don't conflict. Each service should run on a different port.
### Startup Order:
 The eureka-server must be launched before any other microservices. This ensures that service discovery is available when other services attempt to register themselves.

### 3. Access Swagger UI
- notification_service : http://localhost:6060/swagger-ui/index.html
- user_service : http://localhost:7070/swagger-ui/index.html
- budget_service : http://localhost:8080/swagger-ui/index.html
- expense_service : http://localhost:9090/swagger-ui/index.html
- eureka_server : http://localhost:8761/

---
## 📬 Postman Collection

  Utilize the provided Postman collection to test the APIs. You may choose to access the services directly or route the requests through the API Gateway, depending on your testing needs.
   - Via API Gateway: Route requests through the API Gateway to test end-to-end flows.
     - [Postman collection with api gateway](https://github.com/ht-yashwanthkumar/Finance-Management/blob/main/postman_and_swagger_specs/Finance%20Manage%20Application%20ApiGateway.postman_collection.json)
   - Direct Service Access: Call individual microservices directly for isolated testing.
     - [Postman collection direct service acess](https://github.com/ht-yashwanthkumar/Finance-Management/blob/main/postman_and_swagger_specs/Finance%20Manage%20Application.postman_collection.json)
---

## 🧪 Testing
```bash
mvn test
```
Each service contains unit tests for major business logic and API endpoints.

---

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
| Gateway to route APIs             | ✅        |
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

├── api-gateway/
├── budget-service/
├── eureka-server/
├── expense-service/
├── notification-service/
├── postman_and_swagger_specs/
│   └── budget_service_swagger.json
│   └── expense_service_swagger.json
│   └── Finance Manage Application.postman_collection.json
│   └── Finance Manage Application ApiGateway.postman_collection.json
│   └── notification_service_swagger.json
│   └── user_service_swagger.json
├── user-service/
└── README.md
````
---

## 🔗 Author
👤 Yashwanth Kumar HT <br/>
📧 ht.yashwanthkumar@gmail.com <br/>
🌐 https://github.com/ht-yashwanthkumar <br/>
📞 +971-582279099 | +91-7353638399 <br/>  



   





