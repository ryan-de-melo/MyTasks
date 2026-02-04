# MyTasks

**MyTasks** is a full stack web application for task management, developed with a focus on 
robust **API** production, secure authentication, performance, and clear communication between 
the frontend and the API.

The project uses **Spring** on the backend, **React** on the frontend, **JWT** for authentication, Spring 
Cache with **Redis** for performance optimization, and **Docker** for environment virtualization.

---

## Features

- User registration and authentication
- Login with **JWT (JSON Web Token)**  
- Complete task management (CRUD):
    - Add tasks
    - List tasks
    - Update tasks
    - Remove tasks
- Tasks associated with the authenticated user
- Data caching with Spring Cache + Redis
- Backend route protection 
- Frontend / backend communication via **Axios**
- Fully containerized environment with **Docker**

---

## Technologies Used

### Backend
- Java  
- Spring Boot  
- Spring Web  
- Spring Security  
- Spring Data JPA  
- Spring Cache
- Redis
- JWT (JSON Web Token)  
- Hibernate  
- Maven  
- PostgreSQL  

### Frontend
- JavaScript  
- React
- Axios
- Vite
- Tailwind

### Infrastructure
- Docker  
- Docker Compose  

---

## How to Run the Application

You can run the entire application using Docker and Docker Compose, without needing to install Java, Node.js, Redis, or PostgreSQL locally.

### Prerequisites
Make sure you have the following installed:
- Docker
- Docker Compose

### Running the Application

**1.** Clone the repository:
```bash
git clone https://github.com/jose-s-melo/MyTasks.git
cd MyTasks
``` 

**2.** Start the application using Docker Compose:
```bash
docker-compose up --build
```

**3.** Wait for the services to start. The backend API will be accessible at `http://localhost:8080` and the frontend at `http://localhost:5173`.

### Stopping the Application

To stop the application, run:
```bash
docker-compose down
```