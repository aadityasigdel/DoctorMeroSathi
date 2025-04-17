CREATE SCHEMA IF NOT EXISTS DoctorMeroSathiDB;

USE DoctorMeroSathiDB;


-- users TABLE 
CREATE TABLE users ( 
    user_id INT AUTO_INCREMENT PRIMARY KEY, 
    email VARCHAR(100) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, 
    role ENUM('doctor', 'customer', 'admin') NOT NULL, 
    full_name VARCHAR(100) NOT NULL, 
    phone VARCHAR(20), 
    gender ENUM('male', 'female', 'other'), 
    profile_picture VARCHAR(255), 
    address TEXT, 
    dob DATE, specialization VARCHAR(100), 
    experience_years INT, 
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP );


-- doctor_schedule TABLE 
CREATE TABLE doctor_schedule ( 
    schedule_id INT AUTO_INCREMENT PRIMARY KEY, 
    doctor_id INT NOT NULL, 
    day ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'), 
    start_time TIME, 
    end_time TIME,
    FOREIGN KEY (doctor_id) REFERENCES users(user_id) );

-- appointments TABLE 
CREATE TABLE appointments ( 
    appointment_id INT AUTO_INCREMENT PRIMARY KEY, 
    customer_id INT NOT NULL, doctor_id INT NOT NULL, 
    appointment_datetime DATETIME NOT NULL, 
    status ENUM('pending', 'confirmed', 'completed', 'cancelled') DEFAULT 'pending', 
    reason TEXT, 
    rescheduled_from INT, 
    cancelled_by INT, 
    cancelled_at DATETIME, 
    cancellation_reason TEXT, 
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
    FOREIGN KEY (customer_id) REFERENCES users(user_id), 
    FOREIGN KEY (doctor_id) REFERENCES users(user_id), 
    FOREIGN KEY (rescheduled_from) REFERENCES appointments(appointment_id), 
    FOREIGN KEY (cancelled_by) REFERENCES users(user_id) );

-- reviews TABLE 
CREATE TABLE reviews ( 
    review_id INT AUTO_INCREMENT PRIMARY KEY, 
    user_id INT NOT NULL, 
    rating INT CHECK (rating BETWEEN 1 AND 5), 
    review TEXT, 
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, 
    FOREIGN KEY (user_id) REFERENCES users(user_id) );

-- messages TABLE 
CREATE TABLE messages (
     message_id INT AUTO_INCREMENT PRIMARY KEY, 
     sender_id INT NOT NULL, 
     receiver_id INT NOT NULL, 
     message TEXT NOT NULL, 
     created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES users(user_id), FOREIGN KEY (receiver_id) REFERENCES users(user_id) );