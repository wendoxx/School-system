# School-system

**School-system** is a RESTful API designed to manage the operations of a school system. It allows for the creation, updating, and retrieval of classes, students, and other educational entities. This project is part of a portfolio to demonstrate backend development skills using Java, Spring Boot, PostgreSQL, and Swagger for documentation.

## Features

- **CRUD operations** for managing classes and students.
- Exception handling with custom exceptions.
- Data transfer using DTOs to optimize performance and simplify data management.
- Integration with PostgreSQL as the database.
- Use of **Swagger** for API documentation (branch `docs/add-swagger`).

## Technologies Used

- **Java** (version 21)
- **Spring Boot** (v3.3.3)
- **PostgreSQL** (v14)
- **Swagger** for API documentation

## Project Structure

The project follows a standard Spring Boot structure:

```bash
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.schoolsystem
│   │   └── resources
│   └── test
│       └── java
│           └── com.example.schoolsystem
├── pom.xml
└── README.md

