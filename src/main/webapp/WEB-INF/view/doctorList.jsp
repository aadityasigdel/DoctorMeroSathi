<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/21/2025
  Time: 9:14 AM
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        form {
            display: flex;
            margin-bottom: 20px;
            justify-content: center;
        }

        form input[type="text"] {
            flex-grow: 1;
            padding: 10px;
            border: 1px solid #D3DCE0;
            border-radius: 5px 0 0 5px;
            font-size: 1rem;
            color: #333333;
        }

        form a {
            background-color: #FF8A7D;
            color: #FFFFFF;
            border: none;
            padding: 10px 15px;
            border-radius: 0 5px 5px 0;
            cursor: pointer;
            font-size: 1rem;
            transition: background-color 0.3s ease;
        }

        form a:hover {
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
            background-image: url('path-to-placeholder-image.jpg'); /* Add placeholder image */
            background-size: cover;
            background-position: center;
        }

        .doctor-name {
            font-weight: bold;
            font-size: 1.2rem;
            color: #333333;
        }

        .doctor-info div div:last-child {
            color: #2A7F9D;
            font-size: 0.9rem;
        }

        .actions button {
            margin-left: 10px;
            border: none;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 0.9rem;
            transition: background-color 0.3s ease;
            color: #FFFFFF;
        }

        .actions button:first-child {
            background-color: #5DB4B8;
        }

        .actions button:first-child:hover {
            background-color: #4CA3A7;
        }

        .actions button:last-child {
            background-color: #FF8A7D;
        }

        .actions button:last-child:hover {
            background-color: #E67A6E;
        }

        /* "See More" button */
        .container > button {
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

        .container > button:hover {
            background-color: #4CA3A7;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/view/navbar.jsp" %>

<div class="container">
    <form>
        <input type="text" placeholder="Search doctors..." name="search">
        <a href="#">Search</a>
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
                <div><%= doctor.getSpecialization() != null ? doctor.getSpecialization() : "General" %></div>
            </div>
        </div>
        <div class="actions">
            <form action="doctor-profile" method="get" style="display:inline;">
                <input type="hidden" name="id" value="<%= doctor.getId() %>">
                <button type="submit">View Profile</button>
            </form>
            <form action="book-appointment" method="get" style="display:inline;">
                <button type="submit">Book Appointment</button>
            </form>
        </div>
    </div>
    <% } %>

    <button>See More</button>
</div>

</body>
</html>
