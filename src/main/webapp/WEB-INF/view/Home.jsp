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
    <div style="
  font-family: 'Segoe UI', sans-serif;
  background: #ffffff;
  border: 1px solid #c7d2fe;
  border-radius: 12px;
  padding: 20px;
  max-width: 420px;
  margin: 24px auto;
  box-shadow: 0 6px 20px rgba(42, 127, 157, 0.15);
  transition: transform 0.3s ease;
">
        <div style="display: flex; align-items: center; gap: 14px; margin-bottom: 16px;">
            <div style="background: #e0f2ff; padding: 12px; border-radius: 50%; width: 40px; height: 40px;"></div>
            <h3 style="color: #2A7F9D; font-size: 1.3rem; margin: 0; font-weight: 600;">Need Immediate Help?</h3>
        </div>
        <p style="color: #374151; font-size: 1rem; margin-bottom: 18px; line-height: 1.5;">
            Chat directly with our doctors for <strong>quick medical advice</strong> anytime.
        </p>
        <a href="${pageContext.request.contextPath}/chat" style="
    display: inline-block;
    background: #2A7F9D;
    color: white;
    padding: 10px 18px;
    border-radius: 8px;
    text-decoration: none;
    font-size: 0.95rem;
    font-weight: 500;
    box-shadow: 0 4px 12px rgba(42, 127, 157, 0.3);
    transition: background 0.3s ease, transform 0.2s ease;
  " onmouseover="this.style.transform='translateY(-2px)'" onmouseout="this.style.transform='translateY(0)'">
            Chat Now
        </a>
    </div>


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

            </div>
        </div>
    </section>
</div>

<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</body>
</html>
