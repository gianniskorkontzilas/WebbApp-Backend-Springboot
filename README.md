# Store Management System - Backend

## Overview
This repository contains the backend of a **Store Management System** that allows authenticated users to manage stores and customers. The backend is built using **Spring Boot** and provides a **RESTful API** for communication with the frontend.

## Features
- **CRUD operations** for Stores and Customers
- **JWT Authentication** for secure access to the API
- **Spring Security** for role-based endpoint protection
- **PostgreSQL** as the database management system
- **Spring Data JPA** for efficient database interaction
- **Custom error handling** to return structured responses

## Technologies Used
- **Spring Boot**
- **Spring Security & JWT**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

## API Endpoints
### **Authentication**
- `POST /api/auth/login` → Authenticates a user and returns a JWT token

### **Stores**
- `GET /api/stores` → Retrieves all stores
- `POST /api/stores` → Creates a new store
- `PUT /api/stores/{id}` → Updates an existing store
- `DELETE /api/stores/{id}` → Deletes a store
- `GET /api/stores/{id}` → Searches a store by Id

  

### **Customers**
- `GET /api/customers` → Retrieves all customers
- `POST /api/customers` → Creates a new customer
- `PUT /api/customers/{id}` → Updates an existing customer
- `DELETE /api/customers/{id}` → Deletes a customer
- `GET /api/customers/{id}` → Searches a customer by Id
- `GET //api/customers/store/{storeId} → Searches a customer by StoreId
 
### **Bonus Feature**
- `GET /api/customers/vat/{vatNumber} → Searches for a customer by VAT number

## Database Setup
1. Install **PostgreSQL** and create a database: `postgres`
2. Execute the SQL scripts found in the `/sql` folder
3. Ensure the `application.properties` file contains the correct database credentials

## Installation & Running
```bash
git clone https://github.com/gianniskorkontzilas/WebbApp-Backend-Springboot.git
cd backend-repo
mvn clean install
mvn spring-boot:run


Authentication Process
To interact with protected endpoints:

Send a POST request to /api/auth/login with valid credentials.
Copy the JWT token received in the response.
Include the token in the Authorization header of subsequent requests.
Testing with Postman
Before accessing the stores and customers endpoints, you must log in and obtain a JWT token.
All requests should include the token in the Authorization: Bearer <your_token> header.
