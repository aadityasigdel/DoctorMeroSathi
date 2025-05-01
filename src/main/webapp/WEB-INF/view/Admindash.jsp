<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
        <div class="stat-card">
            <h3>Total Users</h3>
            <div class="value">${dashboardStats.totalUsers}</div>
            <p>Registered in system</p>
        </div>
        <div class="stat-card">
            <h3>Active Doctors</h3>
            <div class="value">${dashboardStats.activeDoctors}</div>
            <p>Available for appointments</p>
        </div>
        <div class="stat-card">
            <h3>Today's Appointments</h3>
            <div class="value">${dashboardStats.todaysAppointments}</div>
            <p>Scheduled for today</p>
        </div>
    </div>

    <!-- Recent Activity -->
    <h3 class="section-title">Recent Activity</h3>
    <div class="activity-log">
        <c:forEach items="${recentActivities}" var="activity">
            <div class="activity-item">
                <span class="activity-time">${activity.time}</span>
                <span class="activity-text">${activity.description}</span>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>