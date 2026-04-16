# 🚀 Job Tracker System (Full Stack Backend)

A production-style Job Tracking System built using **Spring Boot + JWT Authentication**, designed to help users manage job applications, track progress, and visualize insights.

---

## 🔥 Features

### 🔐 Authentication

* JWT-based authentication
* Secure login & protected APIs
* Stateless session management

### 📊 Dashboard

* Total applications count
* Status-wise breakdown:

  * Applied
  * Interview
  * Offer
  * Rejected

### 📄 Job Management

* Add job applications
* Track job status
* Attach job links

### 🔍 Filtering & Pagination

* Filter jobs by status
* Pagination support using Spring Data
* Efficient database queries

### 📝 Notes

* Add multiple notes per job
* Track interview feedback, follow-ups, etc.

---

## 🧱 Tech Stack

* **Backend:** Java, Spring Boot
* **Security:** Spring Security + JWT
* **Database:** MySQL
* **ORM:** Hibernate / JPA
* **Build Tool:** Maven

---

## 🏗️ Architecture

```text
Controller → Service → Repository → Database
```

* Clean layered architecture
* DTO-based responses (decoupled from entities)
* Global exception handling (in progress)

---

## 🔐 API Highlights

### Auth APIs

* `POST /api/users/register`
* `POST /api/users/login`

### Job APIs

* `POST /api/jobs`
* `GET /api/jobs?page=0&size=5`
* `GET /api/jobs?status=APPLIED`

### Dashboard API

* `GET /api/dashboard`

---

## 📦 Example Request

### Login

```http
POST /api/users/login?email=test@gmail.com&password=123456
```

### Use Token

```http
Authorization: Bearer <JWT_TOKEN>
```

---

## 📊 Example Response (Dashboard)

```json
{
  "total": 10,
  "applied": 4,
  "interview": 3,
  "offer": 1,
  "rejected": 2
}
```

---

## ⚙️ Setup Instructions

1. Clone the repo

```bash
git clone https://github.com/your-username/job-tracker.git
```

2. Configure database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/job_tracker_db
spring.datasource.username=root
spring.datasource.password=your_password
```

3. Run the application

```bash
mvn spring-boot:run
```

---

## 🧠 Key Learnings

* Implemented JWT-based stateless authentication
* Designed RESTful APIs with pagination & filtering
* Built scalable layered architecture
* Applied clean coding and separation of concerns

---

## 🚀 Future Enhancements

* Frontend UI (React)
* Role-based access control
* Docker deployment
* Cloud hosting (AWS)

---

## 👨‍💻 Author

**Nikhil Marathe**

Full Stack Engineer (Java | Spring Boot | React | Angular)

---
