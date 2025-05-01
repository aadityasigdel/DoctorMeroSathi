<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DoctorMeroSathi</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" integrity="sha512-..." crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/index.css">
</head>
<body>

<!-- Navigation -->
<nav class="navbar">
    <div class="logo-section">
        <div class="logo-img">
            <img src="assets/DocLogo.jpg" alt="DoctorMeroSathi">
        </div>
        <p class="logo-text">DoctorMeroSathi</p>
    </div>
    <div class="nav-Links">
        <a href="${pageContext.request.contextPath}/login" class="nav-link login-btn">Login</a>
        <a href="${pageContext.request.contextPath}/signup" class="nav-link login-btn">Sign Up</a>
    </div>
</nav>

<!-- Hero Section -->
<section class="hero">
    <h1 class="hero-title">Quality Healthcare at Your Fingertips</h1>
    <p class="hero-subtitle">Book an appointment with top doctors from the comfort of your home.</p>
</section>

<!-- Features Section -->
<section class="features">
    <h2 class="section-title">Our Services</h2>
    <div class="features-grid">
        <div class="feature-card">
            <div class="feature-icon">
                <i class="fas fa-user-md"></i>
            </div>
            <h3 class="feature-title">Expert Doctors</h3>
            <p class="feature-text">Consult with our highly experienced medical professionals.</p>
        </div>

        <div class="feature-card">
            <div class="feature-icon">
                <i class="fas fa-headset"></i>
            </div>
            <h3 class="feature-title">24/7 Support</h3>
            <p class="feature-text">We are here to assist you anytime, anywhere.</p>
        </div>

        <div class="feature-card">
            <div class="feature-icon">
                <i class="fas fa-calendar-check"></i>
            </div>
            <h3 class="feature-title">Easy Scheduling</h3>
            <p class="feature-text">Book and manage your appointments with just a few clicks.</p>
        </div>
    </div>
</section>

<!-- About Section -->
<section class="about">
    <h2 class="about-title">About Us</h2>
    <p class="about-text">We are dedicated to providing top-notch healthcare services with experienced professionals who
        care about your well-being.</p>
</section>

<!-- Footer -->
<footer>
    <p>&copy; 2025 DoctorMeroSathi. All rights reserved.</p>
</footer>
</body>
</html>
