<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 5/4/2025
  Time: 7:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Css/editprofile.css">
</head>
<body>
<jsp:include page="AdminNav.jsp" />
<div class="edit-user-container">
    <h2>Edit User</h2>

    <form action="${pageContext.request.contextPath}/admin/editUser" method="post">
        <input type="hidden" name="id" value="${param.id}">

        <div class="form-group">
            <label for="fullName">Full Name</label>
            <input type="text" name="fullName" id="fullName" value="${user.fullName}" required>
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" name="email" id="email" value="${user.email}" required>
        </div>

        <div class="form-group">
            <label for="role">Role</label>
            <select name="role" id="role">
                <option value="doctor" ${user.role == 'doctor' ? 'selected' : ''}>Doctor</option>
                <option value="customer" ${user.role == 'customer' ? 'selected' : ''}>Customer</option>
                <option value="admin" ${user.role == 'admin' ? 'selected' : ''}>Admin</option>
            </select>
        </div>

        <button type="submit" class="user-management-btn user-management-edit-btn">Update User</button>
    </form>
</div>
</body>
</html>
