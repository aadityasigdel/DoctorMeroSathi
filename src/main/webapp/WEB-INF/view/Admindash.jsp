<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <div class="value">124</div>
            <p>Registered in system</p>
        </div>
        <div class="stat-card">
            <h3>Active Doctors</h3>
            <div class="value">24</div>
            <p>Available for appointments</p>
        </div>
        <div class="stat-card">
            <h3>Today's Appointments</h3>
            <div class="value">18</div>
            <p>Scheduled for today</p>
        </div>
    </div>



    <!-- Recent Activity -->
    <h3 class="section-title">Recent Activity</h3>
    <div class="activity-log">
        <div class="activity-item">
            <span class="activity-time">10:30 AM</span>
            <span class="activity-text">New patient registration - Sunita Sharma</span>
        </div>
        <div class="activity-item">
            <span class="activity-time">9:15 AM</span>
            <span class="activity-text">Appointment completed with Dr. Ram Prasad</span>
        </div>
        <div class="activity-item">
            <span class="activity-time">Yesterday</span>
            <span class="activity-text">3 new doctors added to the system</span>
        </div>
    </div>
</div>
</body>
</html>