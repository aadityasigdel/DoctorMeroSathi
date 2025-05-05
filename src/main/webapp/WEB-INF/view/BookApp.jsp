<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/24/2025
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Appointment - Doctor Mero Sathi</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/bookApp.css">
</head>
<body>
<div class="header-area">
    <h1>Doctor Mero Sathi</h1>
    <h2>Book Appointment with Doctor</h2>
</div>

<form class="appointment-form" action="${pageContext.request.contextPath}/BookApp" method="post">
    <input type="hidden" name="doctorId" value="${doctorId}">

    <div class="form-group">
        <label for="full_name">Full Name</label>
        <input type="text" id="full_name" name="full_name" placeholder="Your Full Name" required>
    </div>
    <div class="form-group">
        <label for="email">Email Address</label>
        <input type="email" id="email" name="email" placeholder="Your Email Address" required>
    </div>
    <div class="form-group">
        <label for="phone">Phone Number</label>
        <input type="tel" id="phone" name="phone" placeholder="Your Phone Number" required>
    </div>
    <div class="form-group date-input-wrapper">
        <label for="appointment_date">Appointment Date</label>
        <input type="date" id="appointment_date" name="appointment_date" required>
        <span class="calendar-icon">&#128197;</span>
    </div>

    <!-- New Appointment Time Field -->
    <div class="form-group">
        <label for="appointment_time">Appointment Time</label>
        <input type="time" id="appointment_time" name="appointment_time" required>
    </div>

    <div class="form-group">
        <label for="symptoms">Symptoms / Notes</label>
        <textarea id="symptoms" name="symptoms" rows="4"
                  placeholder="Describe your symptoms or any notes for the doctor" required></textarea>
    </div>
    <button type="submit" class="book-appointment-button">Confirm Appointment</button>
</form>
</body>
</html>
