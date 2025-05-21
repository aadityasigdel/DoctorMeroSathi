<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin - Book Appointment | Doctor Mero Sathi</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/bookApp.css">
  <style>
    .admin-banner {
      background-color: #333;
      color: #fff;
      padding: 10px 20px;
      text-align: center;
      font-size: 1.1rem;
      font-weight: bold;
      margin-bottom: 20px;
    }
    .appointment-form {
      border: 2px solid #ccc;
      padding: 25px;
      background-color: #f9f9f9;
    }
    .form-group select, .form-group input, .form-group textarea {
      width: 100%;
      padding: 8px;
      margin-top: 5px;
      margin-bottom: 15px;
    }
  </style>
</head>
<body>
<div class="admin-banner">Admin Portal - Appointment Management</div>

<div class="header-area">
  <h1>Doctor Mero Sathi</h1>
  <h2>Book Appointment for Patient</h2>
</div>

<form class="appointment-form" action="${pageContext.request.contextPath}/BookApp" method="post">

  <!-- Doctor selection dropdown -->
  <div class="form-group">
    <label for="doctorId">Select Doctor</label>
    <select id="doctorId" name="doctorId" required>
      <c:forEach var="doctor" items="${doctors}">
        <option value="${doctor.id}">${doctor.fullName} - ${doctor.specialization}</option>
      </c:forEach>
    </select>
  </div>

  <!-- Patient Information -->
  <div class="form-group">
    <label for="patient_name">Patient Full Name</label>
    <input type="text" id="patient_name" name="full_name" placeholder="Enter patient's full name" required>
  </div>
  <div class="form-group">
    <label for="email">Patient Email Address</label>
    <input type="email" id="email" name="email" placeholder="Enter patient's email address" required>
  </div>
  <div class="form-group">
    <label for="phone">Patient Phone Number</label>
    <input type="tel" id="phone" name="phone" placeholder="Enter patient's phone number" required>
  </div>
  <div class="form-group date-input-wrapper">
    <label for="appointment_date">Appointment Date</label>
    <input type="date" id="appointment_date" name="appointment_date" required>
    <span class="calendar-icon">&#128197;</span>
  </div>

  <div class="form-group">
    <label for="appointment_time">Appointment Time</label>
    <input type="time" id="appointment_time" name="appointment_time" required>
  </div>

  <div class="form-group">
    <label for="symptoms">Symptoms / Notes</label>
    <textarea id="symptoms" name="symptoms" rows="4"
              placeholder="Describe symptoms or notes from patient" required></textarea>
  </div>

  <button type="submit" class="book-appointment-button">Book Appointment</button>
</form>

</body>
</html>
