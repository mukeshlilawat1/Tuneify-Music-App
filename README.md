# 🎵 Tuneify - Full Stack Music App

Tuneify is a **Full Stack Music Application** built with **Spring Boot + MongoDB (Backend)** and **React.js (Frontend)**.  
It allows users to upload, manage, and explore music albums with Cloudinary image hosting support.

---

## 🚀 Tech Stack

### Backend
- Java (Spring Boot, Spring Data JPA, Hibernate)
- MongoDB (Database)
- Cloudinary (Image Upload & Storage)

### Frontend
- React.js
- HTML, CSS, JavaScript
- Axios for API calls

### Other Tools
- Git & GitHub
- Postman (API testing)
- VS Code / IntelliJ IDEA

---
### Tuneify-Full-Stack-Project/
- │── com.Tuneify-Music-App/ # Backend (Spring Boot)
- │── Tuneify-music-app-AdminPanel/ # Admin Panel (React.js)
- │── Tuneify-Music-App-Frontend/ # User Frontend (React.js)
- │── .gitignore
- │── README.md


---

## ⚙️ Setup & Installation

### 1. Clone the repository
```bash
git clone https://github.com/<your-username>/Tuneify-FullStack-Project.git
cd Tuneify-FullStack-Project
```

## 2. Backend (Spring Boot)

- Navigate to com.Tuneify-Music-App

- Configure application.properties (keep local, don’t push sensitive info)

### Run:

- mvn spring-boot:run

## 3. Frontend (React.js)

- Navigate to Tuneify-Music-App-Frontend

### Install dependencies:

- npm install
- npm start

## 4. Admin Panel (React.js)

- Navigate to Tuneify-music-app-AdminPanel


## 🔑 Environment Variables

- Create a .env file (or use application.properties for backend) with your own credentials:

## MongoDB
- spring.data.mongodb.uri=mongodb://localhost:27017/Tuneify-Music-App

## Cloudinary
- cloudinary.cloud-name=your_cloud_name
- cloudinary.api-key=your_api_key
- cloudinary.api-secret=your_api_secret

## 📸 Features

- 🎵 Upload and manage albums

- 🖼️ Cloudinary-based image storage

- 📊 Separate Admin Panel for album management

- ⚡ Modern frontend with React

- 🔒 Secure configuration (sensitive keys ignored in repo)

## 🤝 Contributing
- Contributions, issues, and feature requests are welcome!
- Feel free to fork this repo and submit a pull request.


