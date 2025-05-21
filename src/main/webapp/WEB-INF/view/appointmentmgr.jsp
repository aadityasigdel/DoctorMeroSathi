<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/21/2025
  Time: 11:15 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Appointment Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/appointmgmt.css">
</head>
<body>
<jsp:include page="AdminNav.jsp" />

<div class="appointment-container">
    <h2>Appointment Management</h2>
    <form action="${pageContext.request.contextPath}/addApointment" method="get">
        <button class = "subApp" type="submit">Add New Appointment</button>
    </form>
    <!-- Display Success Message -->
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>

    <!-- Display Error Message -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <!-- Check if there are any appointments -->
    <c:choose>
        <c:when test="${not empty appointments and appointments.size() > 0}">
            <table class="appointment-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Patient ID</th>
                    <th>Doctor ID</th>
                    <th>Appointment Time</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${appointments}" var="appointment">
                    <tr>
                        <td>${appointment.id}</td>
                        <td>${appointment.customerId}</td>
                        <td>${appointment.doctorId}</td>
                        <td>${appointment.appointmentDatetime}</td>
                        <td>${appointment.status}</td>
                        <td class="actions">
                            <!-- Edit Button -->
                            <a href="editAppointment.jsp?id=${appointment.id}" class="btn edit-btn">Edit</a>

                            <!-- Delete Button -->
                            <form method="post" action="${pageContext.request.contextPath}/admin/appointmentmgr" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${appointment.id}">
                                <button type="submit" class="btn delete-btn">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="no-appointments">No appointments found</div>
        </c:otherwise>
    </c:choose>
</div>


</body>
</html>
