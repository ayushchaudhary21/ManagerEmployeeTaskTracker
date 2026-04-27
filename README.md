# 🧩 Task Management System (Spring Boot)

## 🚀 Overview

This project is a **Task Management Backend System** built using **Spring Boot**.
It allows **Managers** to create and assign tasks to employees, and **Employees** to view and update their assigned tasks.

---

## 🎯 Key Features

### 👨‍💼 Manager Capabilities

* Create new tasks
* View all tasks created by them
* Assign tasks to employees
* Monitor task progress

### 👨‍💻 Employee Capabilities

* View assigned tasks
* Update task status
* Update personal details
* Delete account

---

## 🏗️ Tech Stack

* Java (Spring Boot)
* Spring Security (Authentication)
* Spring Data JPA (Hibernate)
* MySQL Database
* Lombok
* REST APIs

---

## 📂 Project Structure

```
├── Controller
│   ├── TaskController
│   ├── EmployeeController
│
├── Service
│   ├── TaskServiceInterface
│   ├── EmployeeServiceInterface
│
├── Entity
│   ├── TaskEntity
│   ├── EmployeeEntity
│
├── DTOModel
│   ├── TaskRequestModel
│   ├── TaskResponseModel
│   ├── EmployeeRequestModel
│   ├── EmployeeResponseModel
│
├── Repository
│   ├── TaskRepository
│   ├── EmployeeRepository
│
├── Exception
│   ├── GlobalException
│   ├── Custom Exceptions
```

---

## 🔐 Authentication

* Uses **Spring Security**
* Current logged-in user is fetched using:

```java
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
String userName = authentication.getName();
```

---

## 📌 API Endpoints

### 🔹 Task APIs

| Method | Endpoint                                | Description                    |
| ------ | --------------------------------------- | ------------------------------ |
| POST   | `/task/create`                          | Create new task (Manager)      |
| GET    | `/task/managerTask`                     | Get tasks created by manager   |
| GET    | `/task/checkTask`                       | Get tasks assigned to employee |
| POST   | `/task/assignedTask/{title}/{employee}` | Assign task                    |
| PATCH  | `/task/updateStatus/{taskTitle}`        | Update task status             |

---

### 🔹 Employee APIs

| Method | Endpoint           | Description             |
| ------ | ------------------ | ----------------------- |
| DELETE | `/employee/delete` | Delete employee         |
| PUT    | `/employee/update` | Update employee details |

---

## 🗄️ Database Configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ComponyDb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

---

## 🧠 Entity Relationship

* One Manager → Many Tasks
* One Employee → Many Assigned Tasks

```java
@ManyToOne
@JoinColumn(name="employee_id")
private EmployeeEntity taskAssignedTo;

@ManyToOne
@JoinColumn(name="manager_id")
private EmployeeEntity taskAssignedBy;
```

---

## ⚙️ How to Run

1. Clone the repository
2. Setup MySQL database (`ComponyDb`)
3. Update `application.properties`
4. Run Spring Boot application

```bash
mvn spring-boot:run
```

---

## 📌 Future Improvements

* Add role-based authorization (Admin/Manager/Employee)
* Add pagination & filtering
* Add notification system
* Integrate frontend (React/Angular)

---

## 👨‍💻 Author

Ayush Chaudhary

---

## ⭐ Contribution

Feel free to fork and improve the project!
