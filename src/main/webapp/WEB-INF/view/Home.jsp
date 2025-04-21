<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Mero Sathi</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/home.css">
</head>

<body>
<%@ include file="/WEB-INF/view/navbar.jsp" %>
<div class="homepage-wrapper">
    <!-- Hero Section -->
    <header class="hero">
        <h1>Welcome to <span>Doctor Mero Sathi</span></h1>
        <p>Your trusted partner for medical appointments and health guidance.</p>
    </header>

    <!-- Search Bar Section -->
    <div class="search-bar">
        <form>
            <input type="text" placeholder="Search doctors by name or specialty">
            <button type="submit">Search</button>
        </form>
    </div>

    <!-- Doctor Cards Section -->
    <section class="doctor-section">
        <h2>Available Doctors</h2>
        <div class="doctor-grid">
            <div class="doctor-card">
                <div class="doctor-details">
                    <h3>Dr. Aayush Sharma</h3>
                    <p>Cardiologist</p>
                </div>
                <div class="doctor-actions">
                    <a href="#" class="view-btn">View Profile</a>
                    <a href="#" class="book-btn">Book Appointment</a>
                </div>
            </div>

            <div class="doctor-card">
                <div class="doctor-details">
                    <h3>Dr. Nisha Karki</h3>
                    <p>Pediatrician</p>
                </div>
                <div class="doctor-actions">
                    <a href="#" class="view-btn">View Profile</a>
                    <a href="#" class="book-btn">Book Appointment</a>
                </div>
            </div>

            <div class="doctor-card">
                <div class="doctor-details">
                    <h3>Dr. Ramesh Thapa</h3>
                    <p>Neurologist</p>
                </div>
                <div class="doctor-actions">
                    <a href="#" class="view-btn">View Profile</a>
                    <a href="#" class="book-btn">Book Appointment</a>
                </div>
            </div>
        </div>
    </section>
</div>

<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</body>
</html>
