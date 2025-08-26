# 📚 E-Library Management System (Backend – Java Spring Boot)


A **Backend part of  E-Library Management System** built using **Spring Boot**.  
It provides RESTful APIs for managing library operations such as **user accounts, authentication, books, borrowing, and returns**.  

---

## ✨ Features
- 👤 **User Management** – Registration, login, and role-based authentication  
- 📖 **Book Management** – Add, update and search books  
- 📅 **Borrow & Return** – Track issued books, due dates, returns if due date exceed fine is added to student. 
- 🔒 **Authentication & Security** – Spring Security + role-based access control  
- ⚡ **Redis Integration** – For caching and session/token storage  
- 📊 **Reports** – Borrowing history and availability  

---

## 🛠️ Tech Stack
- **Language**: Java 17  
- **Framework**: Spring Boot 3.5.3  
- **Database**: MySQL  
- **ORM**: Spring Data JPA + Hibernate  
- **Security**: Spring Security  
- **Cache**: Redis  
- **Build Tool**: Maven  
- **Utilities**: Lombok, Validation API  
- **Testing**: JUnit, Mockito, Spring Boot Test  

---

## 📂 Project Structure

ELibraryManagement/
│── src/
│ ├── main/java/com/example/ELibraryManagement/ # Source code
│ └── resources/ # Configurations (application.properties)
│── pom.xml # Maven dependencies
│── mvnw / mvnw.cmd # Maven wrapper
│── README.md # Project documentation


## 🔮 Future Enhancements

1. JWT-based authentication & refresh tokens

2. Email/SMS notifications for due dates

3. Export reports (CSV/PDF)

4. Integration with digital resources (e-books, PDFs)


## 🤝 Contributing

**Contributions are welcome!**

1. Fork the repo

2. Create a feature branch (git checkout -b feature-name)

3. Commit changes (git commit -m 'Add feature')

4. Push to branch (git push origin feature-name)

5. Create a Pull Request 🎉
