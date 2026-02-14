#### ActiFit Flow – Backend System

**** Important Note
In this repository, I have intentionally kept all dependencies and configurations visible for learning purposes.
In production environments, sensitive credentials (DB passwords, JWT secrets) should always be stored securely using environment variables or secret managers.
This project structure is designed to help Level 1 beginners clearly understand how a real backend system works without confusion.

**** Project Overview

ActiFit Flow is a secure and scalable fitness workflow and activity tracking backend application.

It allows users to:

Register and authenticate securely

Create and manage workouts

Track activity data

Access role-based protected endpoints

The backend is built using modern industry-standard technologies with clean architecture principles.


***** Tech Stack

Language: Java

Framework: Spring Boot

Security: Spring Security + JWT Authentication

Database: MySQL

ORM: Spring Data JPA (Hibernate)

Build Tool: Maven

Containerization: Docker

API Style: RESTful APIs



🏗️ System Architecture
                ┌────────────────────┐
                │     Client App     │
                │ (Frontend/Postman) │
                └──────────┬─────────┘
                           │ HTTP Request
                           ▼
                ┌────────────────────┐
                │  Spring Security   │
                │  JWT Filter        │
                └──────────┬─────────┘
                           ▼
                ┌────────────────────┐
                │   Controller Layer │
                └──────────┬─────────┘
                           ▼
                ┌────────────────────┐
                │   Service Layer    │
                │ (Business Logic)   │
                └──────────┬─────────┘
                           ▼
                ┌────────────────────┐
                │ Repository Layer   │
                │ (JPA/Hibernate)    │
                └──────────┬─────────┘
                           ▼
                ┌────────────────────┐
                │   PostgreSQL DB    │
                └────────────────────┘


****** Register Flow
User → /register
        ↓
Validate Input (@Valid annotations)
        ↓
Encrypt Password (BCrypt)
        ↓
Save User in DB
        ↓
Return Success Response

Validation Annotations Used

@NotNull

@Email

@Size

@NotBlank

These ensure that invalid data never enters the database.
**** Login Flow
User → /login
        ↓
Verify Email & Password
        ↓
Generate JWT Token
        ↓
Return Token to Client


The token contains:

userId

role

expiration time

📍 Protected Request Flow
Client sends:
Authorization: Bearer <token>

        ↓
JWT Filter extracts token
        ↓
Validate token signature
        ↓
Set authentication in SecurityContext
        ↓
Allow access to endpoint


The system is stateless (no server session stored).

👤 2️⃣ User Module
User Entity Structure
User
---------------------
id (Primary Key)
name
email (Unique)
password (Encrypted)
role (USER / ADMIN)
createdAt

Role-Based Access Control (RBAC)

USER → Can manage own activities

ADMIN → Can manage all users & data

Security config checks roles before allowing access.

Example:

@PreAuthorize("hasRole('ADMIN')")

🏃 3️⃣ Activity Module

Each activity belongs to a user.

Relationship
User (1) ─────── (Many) Activity

Activity Entity
Activity
---------------------
id (Primary Key)
title
description
duration
caloriesBurned
user_id (Foreign Key)
createdAt

Flow
Authenticated User
        ↓
Create Activity
        ↓
User ID extracted from JWT
        ↓
Activity saved with user_id


This ensures users cannot access other users' activities.

🤖 4️⃣ Recommendation Module

The recommendation system analyzes activity data and suggests fitness improvements.

Flow
User Activity Data
        ↓
Business Logic in Service Layer
        ↓
Analyze calories / duration
        ↓
Generate Recommendation
        ↓
Return Response


Example:

If low activity → Suggest more workout

If high calories burned → Suggest recovery

🛡️ 5️⃣ Validation Layer

Validation happens before service logic.

Example DTO:

public class RegisterRequest {
    @NotBlank
    @Email
    private String email;

    @Size(min = 6)
    private String password;
}


This prevents invalid API calls.

🗄️ Database Design Overview
+---------+        1        *       +-----------+
|  User   |  -------------------->  | Activity  |
+---------+                         +-----------+
| id      |                         | id        |
| name    |                         | title     |
| email   |                         | duration  |
| role    |                         | user_id   |
+---------+                         +-----------+

🧱 Complete Request Lifecycle
1. User sends API request
2. JWT filter validates token
3. Controller receives request
4. Service executes business logic
5. Repository interacts with DB
6. Response returned

🐳 Docker Flow
Source Code
     ↓
Maven Build → JAR
     ↓
Docker Image
     ↓
Docker Container
     ↓
Application Running on Port 8080


Docker ensures:

Same environment everywhere

Easy deployment

Dependency isolation

###### What This Architecture Demonstrates

Secure JWT authentication

Role-based access control

Clean layered architecture

Proper entity relationships

Validation using annotations

Secure password storage

Containerized deployment

Stateless backend design

##### Why This is Strong

This backend demonstrates:

✔ Authentication
✔ Authorization
✔ Data Isolation
✔ Secure Token Handling
✔ Clean Architecture
✔ Real Database Relationships
✔ Beginner-Friendly Structure
✔ Production-Ready Foundation
