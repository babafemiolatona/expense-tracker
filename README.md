# Expense Tracker API
A secure and RESTful backend API for managing personal finances including expenses, incomes, and budgets. Built with Java Spring Boot, JWT authentication, and PostgreSQL.

## Features
- JWT Authentication
+ Expense Management
* Income Tracking
+ Budget Setup
- DTO pattern

## Tech Stack
| Layer      | Technology                  |
| ---------- | --------------------------- |
| Language   | Java 17+                    |
| Framework  | Spring Boot                 |
| Database   | PostgreSQL                  |
| Security   | Spring Security + JWT       |
| Docs       | Springdoc OpenAPI (Swagger) |
| Validation | Jakarta Bean Validation     |
| Build Tool | Maven                       |

## Getting Started

### 1. Clone the repo
   ```
   git clone https://github.com/babafemiolatona/expense-tracker.git
   cd expense-tracker
   ```
### 2. Set up the Database
   Make sure PostgreSQL is installed and running.
   Create a database:
   ```
   CREATE DATABASE expensedb;
   ```
### 3. Configure Environment
   Create a .env or use application.properties for local dev:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/expensedb
   spring.datasource.username=postgres
   spring.datasource.password=your_password
    
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    
   # JWT
   app.jwt.secret=your_jwt_secret_key

   ```
   ### 4. Build the project
   ```
   mvn clean install
   ```
   ### 5. Run the App
   ```
   ./mvnw spring-boot:run
   ```

## API Endpoints
### Auth
| Method | Endpoint             | Description         |
| ------ | -------------------- | ------------------- |
| POST   | `/api/v1/auth/register` | Register a new user |
| POST   | `/api/v1/auth/login`    | Login & get JWT     |

### Expenses
| Method | Endpoint                | Auth Required | Description       |
| ------ | ----------------------- | ------------- | ----------------- |
| GET    | `/api/v1/expenses`      | ✅ Yes         | List all expenses |
| GET    | `/api/v1/expenses/{id}` | ✅ Yes         | Get expense by ID |
| POST   | `/api/v1/expenses`      | ✅ Yes         | Add a new expense |
| PUT    | `/api/v1/expenses/{id}` | ✅ Yes         | Update an expense |
| DELETE | `/api/v1/expenses/{id}` | ✅ Yes         | Delete an expense |

### Income
| Method | Endpoint               | Auth Required | Description   |
| ------ | ---------------------- | ------------- | ------------- |
| GET    | `/api/v1/income`      | ✅ Yes         | List incomes  |
| GET    | `/api/v1/income/{id}` | ✅ Yes         | Get income by ID|
| POST   | `/api/v1/income`      | ✅ Yes         | Add income    |
| PUT    | `/api/v1/income/{id}` | ✅ Yes         | Update income |
| DELETE | `/api/v1/income/{id}` | ✅ Yes         | Delete income |

### Budget
| Method | Endpoint               | Auth Required | Description     |
| ------ | ---------------------- | ------------- | --------------- |
| GET    | `/api/v1/budgets`      | ✅ Yes         | List budgets    |
| GET    | `api/v1/budgets/{id}`       | ✅ Yes         | Get budget by ID|
| POST   | `/api/v1/budgets`      | ✅ Yes         | Create a budget |
| PUT    | `/api/v1/budgets/{id}` | ✅ Yes         | Update a budget |
| DELETE | `/api/v1/budgets/{id}` | ✅ Yes         | Delete a budget |

## Swagger UI
Access the interactive documentation:
```
http://localhost:8080/swagger-ui/index.html
```

