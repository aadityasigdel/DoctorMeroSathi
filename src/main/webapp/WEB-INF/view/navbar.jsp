<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>DoctorMeroSathi</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Css/navigation.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
<header class="navbar">
    <div class="navbar-container">
        <!-- Logo Section -->
        <div class="brand-container">
            <div class="logo">
                <div class="logo-img">
                    <img src="${pageContext.request.contextPath}/assets/DocLogo.jpg" alt="DoctorMeroSathi Logo">
                </div>
                <span class="main-brand">DoctorMeroSathi</span>
            </div>
        </div>

        <!-- Navigation Section -->
        <nav class="nav-section" role="navigation" aria-label="Main Navigation">
            <ul class="nav-links">
                <li>
                    <a href="${pageContext.request.contextPath}/home" class="nav-item">
                        <i class="fas fa-home"></i>
                        <span>Home</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/view-appointments" class="nav-item">
                        <i class="fas fa-calendar-check"></i>
                        <span>Appointments</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/profile" class="nav-item">
                        <i class="fas fa-user"></i>
                        <span>Profile</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/about" class="nav-item">
                        <i class="fas fa-info-circle"></i>
                        <span>About Us</span>
                    </a>
                </li>
            </ul>

            <!-- Logout Button -->
            <div class="logout">
                <a href="${pageContext.request.contextPath}/logout" class="logout-btn">
                    <i class="fas fa-sign-out-alt"></i>
                    <span>Logout</span>
                </a>
            </div>
        </nav>
    </div>
</header>
</body>
</html>
