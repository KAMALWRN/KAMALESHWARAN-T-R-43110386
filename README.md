# Capstone Project - Travel Tours Package Management System

This repository contains the complete capstone project submission for the Travel Tours Package Management System.

## 📁 Repository Structure

```
CAPSTONE_PROJECT/
├── travel-tours-system/          # Main Spring Boot application
│   ├── src/                      # Source code
│   ├── database/                 # Database scripts
│   └── pom.xml                   # Maven configuration
├── Website Images/               # Screenshots of the application
├── CAPSTONE_PROJECT_REPORT.docx  # Project report (Word document)
└── Website_video_Drive_Link.txt  # Link to demo video

```

## 🚀 Quick Start

### 1. Navigate to the Project
```bash
cd travel-tours-system
```

### 2. Setup Database
- Create MySQL database: `travel_tours_db`
- Run scripts from `travel-tours-system/database/` folder

### 3. Configure Application
- Copy `application.properties.template` to `src/main/resources/application.properties`
- Update database credentials

### 4. Run Application
```bash
mvn spring-boot:run
```

### 5. Access Application
- **Frontend:** http://localhost:8080/index.html
- **Backend API:** http://localhost:8080/api/
- **Admin Login:** admin@tours.com / admin123

## 📚 Documentation

- **Project Report:** See `CAPSTONE_PROJECT_REPORT.docx`
- **Demo Video:** See `Website_video_Drive_Link.txt` for Google Drive link
- **Screenshots:** See `Website Images/` folder
- **Project README:** See `travel-tours-system/README.md`

## 🛠️ Technologies Used

- **Backend:** Spring Boot 2.7.18, Java 17
- **Database:** MySQL 8.0
- **Security:** Spring Security, JWT
- **Frontend:** HTML5, CSS3, JavaScript, Bootstrap 5
- **Build Tool:** Apache Maven

## 📸 Screenshots

Screenshots of the application are available in the `Website Images/` folder, showing:
- Home page
- Package browsing
- Booking system
- Admin dashboard
- User authentication

## 🎥 Demo Video

A complete demo video is available via the Google Drive link in `Website_video_Drive_Link.txt`.

## 📝 Project Features

- ✅ User Authentication (JWT-based)
- ✅ Tour Package Management
- ✅ Online Booking System
- ✅ Customer Enquiry Management
- ✅ Newsletter Subscription
- ✅ Admin Dashboard
- ✅ Role-Based Access Control

## 👥 User Roles

- **Guest:** Browse packages, submit enquiries
- **User:** All guest features + make bookings
- **Admin:** All features + manage system

## 📋 API Endpoints

### Public Endpoints
- `POST /api/auth/signup` - User registration
- `POST /api/auth/signin` - User login
- `GET /api/packages/public/all` - Get all packages
- `POST /api/enquiries/public/create` - Submit enquiry

### User Endpoints (JWT Required)
- `POST /api/bookings` - Create booking
- `GET /api/bookings/my-bookings` - View bookings

### Admin Endpoints (ADMIN Role Required)
- `GET /api/bookings` - View all bookings
- `PUT /api/bookings/{id}/status` - Update booking status
- `GET /api/enquiries` - View all enquiries

## 🔒 Security

- JWT-based authentication
- Password encryption (BCrypt)
- Role-based access control
- CORS configuration
- Input validation

## 📞 Contact

For questions or issues, please refer to the project report or contact the project author.

---

**Note:** This is a capstone project submission for educational purposes.
