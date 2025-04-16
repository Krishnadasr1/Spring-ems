# Employee Management System (EMS)

A robust and secure Employee Management System built with Spring Boot, providing comprehensive employee data management capabilities.

## 🚀 Features

- **User Authentication & Authorization**
  - Secure login and registration system
  - Role-based access control
  - Password encryption using BCrypt

- **Employee Management**
  - Create, Read, Update, and Delete employee records
  - View employee profiles
  - List all employees with pagination support

- **Security**
  - Spring Security integration
  - BCrypt password hashing
  - Secure API endpoints

## 🛠️ Tech Stack

- **Backend Framework**: Spring Boot 3.3.5
- **Database**: MySQL
- **ORM**: Spring Data JPA
- **Security**: Spring Security Core
- **Build Tool**: Maven
- **Java Version**: 21
- **Additional Tools**: Lombok

## 📋 Prerequisites

- Java 21 or higher
- MySQL Server
- Maven 3.9.9 or higher

## 🔧 Installation

1. Clone the repository:
   ```bash
   git clone [your-repository-url]
   ```

2. Configure the database:
   - Create a MySQL database
   - Update `application.properties` with your database credentials

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## 📚 API Documentation

The application provides the following REST endpoints:

- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Authenticate user
- `GET /api/employees` - Get all employees
- `GET /api/employees/{id}` - Get employee by ID
- `PUT /api/employees/{id}` - Update employee
- `DELETE /api/employees/{id}` - Delete employee

## 🔒 Security

- All passwords are encrypted using BCrypt
- JWT-based authentication
- Role-based access control
- CSRF protection enabled

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📝 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.
