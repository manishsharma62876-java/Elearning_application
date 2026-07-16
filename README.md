# 🎓 E-Learning Application

A Full Stack E-Learning Management System developed using **Spring Boot, React.js, and MySQL**.

This application provides an online learning platform where students can register, login, explore courses, enroll in courses, and manage their learning activities. Admin users can manage courses and monitor the platform.

The project follows a **Frontend + REST API Backend Architecture**.

---

# 📌 Project Features

## 👨‍🎓 Student Features

- Student Registration
- Student Login
- JWT Based Authentication
- Browse Available Courses
- View Course Details
- Enroll in Courses
- View Enrolled Courses
- Student Dashboard
- Secure Role-Based Access


## 👨‍💼 Admin Features

- Admin Login
- Admin Dashboard
- Add New Courses
- Update Courses
- Delete Courses
- Manage Course Information
- View Platform Data


---

# 🏗️ Application Architecture


```
                     React JS Frontend
                            |
                            |
                       REST API Calls
                            |
                            |
                   Spring Boot Backend
                            |
                            |
                       MySQL Database

```


The application is divided into two major parts:

```
E-Learning Application

│
├── Frontend
│     └── React.js
│
└── Backend
      └── Spring Boot REST API

```


---

# 💻 Frontend Technology Stack

## React.js

Used for building dynamic and responsive user interfaces.


### Technologies Used:

| Technology | Purpose |
|---|---|
| React.js | UI Development |
| React Router DOM | Client Side Routing |
| Axios | API Communication |
| JavaScript ES6 | Programming |
| HTML5 | Structure |
| CSS3 | Styling |
| Bootstrap | Responsive Design |
| Vite | Frontend Build Tool |


---

# ⚙️ Backend Technology Stack


| Technology | Purpose |
|---|---|
| Java 17 | Programming Language |
| Spring Boot | Backend Framework |
| Spring MVC | REST API Development |
| Spring Security | Application Security |
| JWT | Authentication & Authorization |
| Hibernate | ORM Framework |
| Spring Data JPA | Database Operations |
| Maven | Dependency Management |
| Lombok | Reduce Boilerplate Code |
| Swagger | API Documentation |


---

# 🗄️ Database


## MySQL Database


Database Name:

```
elearning_db
```


### Tables


## User Table

Stores student and admin information.

Fields:

```
id
name
email
password
role
```


Roles:

```
ADMIN
STUDENT
```



## Course Table


Fields:

```
id
title
description
category
fees
duration
createdAt
```



## Enrollment Table


Fields:

```
id
student_id
course_id
enrollment_date
```


---

# 🔐 Authentication & Security


The application uses:

## JWT Authentication


Authentication Flow:


```
User Login

      |
      |

Validate Email & Password

      |
      |

Generate JWT Token

      |
      |

Send Token To Frontend

      |
      |

Frontend Stores Token

      |
      |

Send Token With API Requests

```



Security Features:

- Password Encryption using BCrypt
- JWT Token Authentication
- Role Based Authorization
- Protected APIs


Example:


Student:

```
ROLE_STUDENT
```


Admin:

```
ROLE_ADMIN
```



---

# 📂 Backend Project Structure


```
src/main/java/com/elearning


│
├── controller
│
├── service
│
├── serviceImpl
│
├── repository
│
├── entity
│
├── dto
│
│    ├── request
│    └── response
│
├── security
│
└── config

```



---

# 📂 Frontend Project Structure


```
src


│
├── pages
│
│    ├── Login.jsx
│    ├── Signup.jsx
│    ├── Home.jsx
│    ├── CourseList.jsx
│    ├── CourseDetails.jsx
│    ├── MyCourses.jsx
│    ├── StudentDashboard.jsx
│    └── AdminDashboard.jsx
│
│
├── components
│
│    ├── Navbar.jsx
│    └── CourseCard.jsx
│
│
├── services
│
│    ├── api.js
│    ├── AuthService.js
│    ├── CourseService.js
│    └── EnrollmentService.js
│
│
└── App.jsx

```


---

# 🔥 REST API Documentation


Base URL:

```
http://localhost:8080/api
```


---

# Authentication APIs


## Register User


### POST

```
/auth/signup
```


Request:


```json
{
"name":"Rahul",
"email":"rahul@gmail.com",
"password":"12345",
"role":"STUDENT"
}

```



Response:

```json
{
"message":"User registered successfully"
}

```


---

## Login User


### POST


```
/auth/login
```


Request:


```json
{
"email":"rahul@gmail.com",
"password":"12345"
}

```



Response:


```json
{
"token":"jwt-token"
}

```


---

# Course APIs


## Get All Courses


### GET


```
/courses
```


Response:


```json
[
 {
 "id":1,
 "title":"Java Full Stack",
 "description":"Complete Java Course",
 "category":"Programming",
 "fees":25000,
 "duration":"6 Months"
 }
]

```


---

## Get Course By Id


### GET


```
/courses/{id}
```


Example:


```
/courses/1
```


---

## Add Course (Admin)


### POST


```
/courses/add
```


Authorization:

```
ADMIN
```



Request:


```json
{
"title":"Spring Boot",
"description":"Learn Spring Boot",
"category":"Backend",
"fees":20000,
"duration":"4 Months"
}

```



---

## Update Course


### PUT


```
/courses/{id}
```


Admin Only


---

## Delete Course


### DELETE


```
/courses/{id}
```


Admin Only


---

# Enrollment APIs


## Enroll Course


### POST


```
/enrollments/add
```


Authorization:

```
STUDENT
```


Request:


```json
{
"courseId":1
}

```



---

## My Courses


### GET


```
/enrollments/my-courses
```


Authorization:

```
STUDENT
```



---

# ⚙️ Backend Setup


## Clone Repository


```
git clone repository-url
```


Go to backend folder:


```
cd elearning-backend
```



Install dependencies:


```
mvn clean install
```


Run application:


```
mvn spring-boot:run
```


Backend runs on:


```
http://localhost:8080
```


---

# ⚙️ Frontend Setup


Go to frontend folder:


```
cd elearning-frontend
```


Install packages:


```
npm install
```


Run application:


```
npm run dev
```


Frontend runs on:


```
http://localhost:5173
```


---

# 🔧 Configuration


Backend:

`application.properties`


```properties
spring.datasource.url=jdbc:mysql://localhost:3306/elearning_db

spring.datasource.username=root

spring.datasource.password=password


spring.jpa.hibernate.ddl-auto=update


server.port=8080
```



---

# 🧪 Testing Tools


Used Tools:


- Postman
- Swagger UI
- MySQL Workbench
- Browser Developer Tools


---

# 📸 Screenshots

(Add application screenshots here)


---

# 🚀 Future Enhancements


- Email Notification after Enrollment
- Payment Gateway Integration
- Course Reviews and Ratings
- Video Lecture Upload
- Certificate Generation
- Forgot Password
- Cloud Deployment
- Docker Support


---

# 👨‍💻 Developer


**Manish Sharma**

Java Backend Developer


Skills:

- Java
- Spring Boot
- Spring Security
- REST API
- React
- MySQL


---

# ⭐ Conclusion


This project demonstrates a complete Full Stack application development process using modern technologies like Spring Boot, React.js, JWT Security, and MySQL.

The application follows industry-standard practices including REST API architecture, DTO pattern, layered architecture, authentication, and role-based authorization.
