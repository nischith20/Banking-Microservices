# Banking Microservices Application

A backend banking system built using Spring Boot Microservices architecture.
This project demonstrates service-to-service communication, API Gateway, JWT authentication, role-based authorization, and database integration.

## 🚀 Features

- User registration and login
- JWT based authentication
- Role-based authorization (CUSTOMER / ADMIN)
- Account management
- Customer management
- Transaction service
- Secure API Gateway
- Microservice communication using REST APIs
- Service discovery using Eureka Server
- Database integration using PostgreSQL/MySQL
- Spring Security implementation


## 🏗️ Architecture


Client
  |
  |
API Gateway (8085)
  |
  |
--------------------------------
|              |               |
Customer     Account       Transaction
Service      Service        Service
(8080)       (8081)          (8082)

          |
          |
      Eureka Server


## 🛠️ Technologies Used

### Backend
- Java 26
- Spring Boot
- Spring Cloud
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate

### Database
- PostgreSQL

### Tools
- Maven
- Git & GitHub
- Postman
- IntelliJ IDEA / VS Code


## 📂 Microservices

### API Gateway
Port: 8085

Responsibilities:
- Single entry point for clients
- JWT token validation
- Routing requests to services


### Customer Service
Port: 8080

Responsibilities:
- User registration
- User authentication
- Customer management


### Account Service
Port: 8081

Responsibilities:
- Account creation
- Account details
- Account operations


### Transaction Service
Port: 8082

Responsibilities:
- Money transfer
- Transaction history
- Transaction processing


## 🔐 Security

Authentication flow:

1. User registers
2. User logs in
3. Server generates JWT token
4. Client sends token with requests

Example:

Authorization Header:

Bearer <JWT_TOKEN>


## 📌 API Endpoints


### Register User

POST
