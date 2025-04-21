<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/21/2025
  Time: 8:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.example.doctormerosathi.model.UsersModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    UsersModel user = (UsersModel) request.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Css/edit-profile.css">
</head>
<%@ include file="/WEB-INF/view/navbar.jsp" %>
<body>
<div class="main-content">
<div class="edit-container">
    <h2>Edit Profile</h2>

    <form action="<%= request.getContextPath() %>/edit-profile" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="full_name">Full Name:</label>
            <input type="text" id="full_name" name="full_name" value="<%= user.getFullName() %>" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required readonly>
        </div>

        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" value="<%= user.getPhoneNumber() %>">
        </div>

        <div class="form-group">
            <label for="gender">Gender:</label>
            <select id="gender" name="gender">
                <option value="male" <%= "male".equals(user.getGender()) ? "selected" : "" %>>Male</option>
                <option value="female" <%= "female".equals(user.getGender()) ? "selected" : "" %>>Female</option>
                <option value="other" <%= "other".equals(user.getGender()) ? "selected" : "" %>>Other</option>
            </select>
        </div>

        <div class="form-group">
            <label for="address">Address:</label>
            <textarea id="address" name="address" rows="4" required><%= user.getAddress() %></textarea>
        </div>

        <div class="form-group">
            <label for="dob">Date of Birth:</label>
            <input type="date" id="dob" name="dob" value="<%= user.getDob() != null ? user.getDob().toString() : "" %>">
        </div>

        <% if ("doctor".equalsIgnoreCase(user.getRole())) { %>
        <div class="form-group">
            <label for="specialization">Specialization:</label>
            <input type="text" id="specialization" name="specialization" value="<%= user.getSpecialization() %>">
        </div>

        <div class="form-group">
            <label for="experience_years">Experience (years):</label>
            <input type="number" id="experience_years" name="experience_years" value="<%= user.getExperienceYears() != null ? user.getExperienceYears() : "" %>">
        </div>
        <% } %>


        <div class="form-group">
            <label for="profile_picture">Profile Picture:</label>
            <input type="file" id="profile_picture" name="profile_picture"><br>
        </div>



        <div class="form-group">
            <button type="submit">Update Profile</button>
        </div>
    </form>
</div>
    </div>
</body>
</html>
