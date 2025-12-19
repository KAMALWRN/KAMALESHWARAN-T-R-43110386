-- Travel Tours System Database Schema
-- Run this script to create the database and tables

CREATE DATABASE IF NOT EXISTS travel_tours_db;
USE travel_tours_db;

-- Roles Table
CREATE TABLE IF NOT EXISTS roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME NOT NULL,
    updated_at DATETIME
);

-- User Roles Junction Table
CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Categories Table
CREATE TABLE IF NOT EXISTS categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(500),
    image_url VARCHAR(255)
);

-- Tour Packages Table
CREATE TABLE IF NOT EXISTS tour_packages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    destination VARCHAR(100),
    description TEXT,
    itinerary TEXT,
    duration INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    discount_price DECIMAL(10,2),
    main_image_url VARCHAR(500),
    category_id BIGINT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    available_seats INT NOT NULL,
    start_date DATE,
    end_date DATE,
    tour_type VARCHAR(50),
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

-- Tour Package Images Table
CREATE TABLE IF NOT EXISTS tour_package_images (
    tour_package_id BIGINT NOT NULL,
    image_url VARCHAR(500),
    FOREIGN KEY (tour_package_id) REFERENCES tour_packages(id) ON DELETE CASCADE
);

-- Bookings Table
CREATE TABLE IF NOT EXISTS bookings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booking_number VARCHAR(50) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    tour_package_id BIGINT NOT NULL,
    number_of_travelers INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    status VARCHAR(50),
    special_requests VARCHAR(500),
    booking_date DATETIME NOT NULL,
    updated_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (tour_package_id) REFERENCES tour_packages(id) ON DELETE CASCADE
);

-- Enquiries Table
CREATE TABLE IF NOT EXISTS enquiries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    message VARCHAR(500),
    tour_package_id BIGINT,
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (tour_package_id) REFERENCES tour_packages(id) ON DELETE SET NULL
);

-- Newsletter Subscribers Table
CREATE TABLE IF NOT EXISTS newsletter_subscribers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    subscribed_at DATETIME NOT NULL
);

-- Insert Default Roles
INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER')
ON DUPLICATE KEY UPDATE name=name;

-- Insert Default Admin User (password: admin123)
-- Note: Password is bcrypt encoded
INSERT INTO users (email, password, first_name, last_name, phone, active, created_at, updated_at)
VALUES ('admin@tours.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iwK8pJ5m', 'Admin', 'User', '1234567890', TRUE, NOW(), NOW())
ON DUPLICATE KEY UPDATE email=email;

-- Link Admin User to Admin Role
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r 
WHERE u.email = 'admin@tours.com' AND r.name = 'ROLE_ADMIN'
ON DUPLICATE KEY UPDATE user_id=user_id;

-- Insert Sample Categories
INSERT INTO categories (name, description, image_url) VALUES
('Beach Holidays', 'Relaxing beach destinations', 'https://images.unsplash.com/photo-1507525428034-b723cf961d3e'),
('Adventure Tours', 'Thrilling adventure experiences', 'https://images.unsplash.com/photo-1464822759844-d150ad6d0e0b'),
('Cultural Tours', 'Explore rich cultural heritage', 'https://images.unsplash.com/photo-1515542622106-78bda8ba0e5b'),
('Mountain Tours', 'Mountain trekking and hiking', 'https://images.unsplash.com/photo-1464822759844-d150ad6d0e0b')
ON DUPLICATE KEY UPDATE name=name;
