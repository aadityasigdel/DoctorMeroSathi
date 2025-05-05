<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 5/3/2025
  Time: 8:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dr. ${doctor.fullName} Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/userprofile.css">
</head>
<body>
<%@ include file="/WEB-INF/view/navbar.jsp" %>
<div class="profile-container">
    <!-- Profile Header -->
    <div class="profile-header">
        <h1>${doctor.fullName}'s Profile</h1>
        <p>Specialization: ${doctor.specialization}</p>
        <p>Experience: ${doctor.experienceYears} years</p>
    </div>

    <!-- Profile Image Section -->
    <div class="profile-image-section">
        <c:if test="${not empty doctor.profilePicture}">
            <img src="data:image/jpeg;base64,${doctor.profilePicture}" alt="${doctor.fullName}" class="profile-image">
        </c:if>
        <c:if test="${empty doctor.profilePicture}">
            <div class="no-picture">
                <span>${fn:substring(doctor.fullName, 0, 1)}</span>
            </div>
        </c:if>
    </div>

    <!-- Profile Body -->
    <div class="profile-body">
        <!-- Profile Details Section -->
        <div class="profile-details">
            <div class="profile-info-card">
                <h3>About Dr. ${doctor.fullName}</h3>
                <p><strong>Full Name:</strong> ${doctor.fullName}</p>

            </div>

            <div class="profile-info-card">
                <h3>Doctor Information</h3>
                <p><strong>Specialization:</strong> ${doctor.specialization}</p>
                <p><strong>Experience:</strong> ${doctor.experienceYears} years</p>
            </div>
        </div>

        <!-- Schedule Section -->
        <div class="schedule-card">
            <h3>Schedule</h3>
            <c:if test="${not empty schedule}">
                <div class="schedule-list">
                    <ul>
                        <c:forEach var="day" items="${schedule}">
                            <li>${day.day}: ${day.startTime} - ${day.endTime}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>
            <c:if test="${empty schedule}">
                <p>No schedule available at the moment.</p>
            </c:if>
        </div>
    </div>
    <form action="submitReview" method="post">
        <input type="hidden" name="appointmentId" value="${appointmentId}" />
        <input type="hidden" name="doctorId" value="${doctor.id}" />
        <label>Rating:</label>
        <select name="rating" required>
            <c:forEach var="i" begin="1" end="5">
                <option value="${i}">${i}</option>
            </c:forEach>
        </select>
        <textarea name="review" required placeholder="Write your review..."></textarea>
        <button type="submit">Submit Review</button>
    </form>
    <h3>Reviews</h3>
    <c:forEach var="r" items="${reviews}">
        <div style="border:1px solid #ccc; padding:10px; margin:10px 0;">
            <strong>${r.userName}</strong> rated <strong>${r.rating}/5</strong><br>
            <p>${r.review}</p>
            <small>${r.createdAt}</small>
        </div>
    </c:forEach>

    <c:if test="${empty reviews}">
        <p>No reviews yet.</p>
    </c:if>
</div>

</body>
</html>
