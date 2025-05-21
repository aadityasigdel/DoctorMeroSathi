<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/21/2025
  Time: 9:14 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.doctormerosathi.model.UsersModel" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctors - Doctor Mero Saathi</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #FFFFFF;
            margin: 0;
            padding: 0;
            color: #333333;
        }

        /* Navbar */
        .navbar {
            background-color: #2A7F9D;
            padding: 1rem;
            color: #FFFFFF;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar Button[type="submit"] {
            color: #FFFFFF;
            margin-left: 15px;
            text-decoration: none;
            transition: background-color 0.3s ease;
            padding: 5px 10px;
            border-radius: 3px;
        }

        .navbar Button:hover {
            background-color: #5DB4B8;
        }

        /* Main container */
        .container {
            max-width: 1000px;
            margin: 30px auto;
            padding: 20px;
        }

        /* Search form */
        .search-form {
            display: flex;
            margin-bottom: 20px;
            justify-content: center;
        }

        .search-form input[type="text"] {
            flex-grow: 1;
            padding: 10px;
            border: 1px solid #D3DCE0;
            border-radius: 5px 0 0 5px;
            font-size: 1rem;
            color: #333333;
        }

        .search-form button {
            background-color: #FF8A7D;
            color: #FFFFFF;
            border: none;
            padding: 10px 15px;
            border-radius: 0 5px 5px 0;
            cursor: pointer;
            font-size: 1rem;
            transition: background-color 0.3s ease;
        }

        .search-form button:hover {
            background-color: #E67A6E;
        }

        /* Doctor card */
        .doctor-card {
            display: flex;
            align-items: center;
            justify-content: space-between;
            border: 1px solid #CCD1D5;
            border-radius: 8px;
            padding: 15px;
            margin-bottom: 15px;
            background-color: #FFFFFF;
            transition: box-shadow 0.3s ease, transform 0.3s ease;
        }

        .doctor-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        .doctor-info {
            display: flex;
            align-items: center;
        }

        .avatar {
            width: 60px;
            height: 60px;
            background-color: #D3E8E6;
            border-radius: 50%;
            margin-right: 20px;
            background-size: cover;
            background-position: center;
        }

        .doctor-name {
            font-weight: bold;
            font-size: 1.2rem;
            color: #333333;
        }

        .doctor-specialization {
            color: #2A7F9D;
            font-size: 0.9rem;
        }

        .actions {
            display: flex;
            gap: 10px;
        }

        .btn {
            border: none;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 0.9rem;
            transition: background-color 0.3s ease;
            color: #FFFFFF;
            text-decoration: none;
            display: inline-block;
        }

        .btn-profile {
            background-color: #5DB4B8;
        }

        .btn-profile:hover {
            background-color: #4CA3A7;
        }

        .btn-book {
            background-color: #FF8A7D;
        }

        .btn-book:hover {
            background-color: #E67A6E;
        }

        /* "See More" button */
        .see-more {
            background-color: #5DB4B8;
            color: #FFFFFF;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            display: block;
            margin: 20px auto;
            font-size: 1rem;
            transition: background-color 0.3s ease;
        }

        .see-more:hover {
            background-color: #4CA3A7;
        }

        .btn-appointment {
            background-color: #4CAF50;
            color: #FFFFFF;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 1rem;
            text-align: center;
            display: inline-block;
            margin: 10px 0;
            text-decoration: none;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }


    </style>
</head>
<body>
<%@ include file="/WEB-INF/view/navbar.jsp" %>

<div class="container">
    <a href="${pageContext.request.contextPath}/ViewAppointments" class="btn btn-appointment">View Appointment</a>
    <form class="search-form">
        <input type="text" placeholder="Search doctors..." name="search">
        <button type="submit">Search</button>
    </form>

    <%
        List<UsersModel> doctors = (List<UsersModel>) request.getAttribute("doctors");
        for (UsersModel doctor : doctors) {
    %>
    <div class="doctor-card">
        <div class="doctor-info">
            <div class="avatar"></div>
            <div>
                <div class="doctor-name">Dr. <%= doctor.getFullName() %></div>
                <div class="doctor-specialization">
                    <%= doctor.getSpecialization() != null ? doctor.getSpecialization() : "General Practitioner" %>
                </div>
            </div>
        </div>
        <div class="actions">
            <form action="${pageContext.request.contextPath}/userprofile" method="get" style="display:inline;">
                <input type="hidden" name="id" value="<%= doctor.getId() %>">
                <button type="submit" class="btn btn-profile">View Profile</button>
            </form>
            <a href="${pageContext.request.contextPath}/BookApp?doctorId=<%= doctor.getId() %>" class="btn btn-book">Book Appointment</a>
        </div>
    </div>
    <% } %>

    <button class="see-more">See More Doctors</button>
</div>

</body>
</html>