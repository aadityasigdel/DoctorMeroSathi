<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/24/2025
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Doctor Mero Sathi</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/adminDash.css">
</head>
<body>

<jsp:include page="AdminNav.jsp" />

<div class="dashboard-container">
    <div class="header">
        <h1>Doctor Mero Sathi</h1>
        <h2>Admin Dashboard</h2>
    </div>

    <!-- Stats Cards -->
    <div class="stats-container">
        <!-- Total Users Card -->
        <div class="stat-card">
            <h3>Total Users</h3>
            <div class="value">${dashboardStats.totalUsers}</div>
            <p>Registered in the system</p>
            <div class="sub-stats">
                <span>${dashboardStats.totalUsers} Total Users</span>
                <span>+${dashboardStats.totalUsers > 100 ? 'High Growth' : 'Stable Growth'}</span>
            </div>
        </div>

        <!-- Active Doctors Card -->
        <div class="stat-card">
            <h3>Active Doctors</h3>
            <div class="value">${dashboardStats.activeDoctors}</div>
            <p>Available for appointments</p>
            <div class="sub-stats">
                <span>${dashboardStats.activeDoctors} Active Doctors</span>
                <span>+${dashboardStats.activeDoctors > 50 ? 'Excellent availability' : 'Growing availability'}</span>
            </div>
        </div>
    </div>

    <!-- System Insights Section -->
    <div class="additional-stats">
        <h3>System Insights</h3>
        <p>We have seen a substantial increase in user registrations and doctor availability. Keep an eye on the system performance metrics!</p>
    </div>

    <!-- Graphical Insights Section -->
    <div class="graph-container">
        <h3>Growth Overview</h3>
        <p>The growth of users and doctors has been steadily increasing, showing a positive trend across the system.</p>
        <!-- You can embed a graph here later if needed -->
    </div>
</div>

</body>
</html>
