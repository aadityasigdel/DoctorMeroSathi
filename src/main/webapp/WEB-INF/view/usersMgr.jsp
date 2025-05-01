<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Css/adminUsrMrg.css">
</head>
<body>
<jsp:include page="AdminNav.jsp" />
<div class="user-management-container">
    <h2>User Management</h2>

    <c:choose>
        <c:when test="${not empty users and users.size() > 0}">
            <table class="user-management-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${not empty user.fullName ? user.fullName : 'N/A'}</td>
                        <td>${not empty user.email ? user.email : 'N/A'}</td>
                        <td>${not empty user.role ? user.role : 'N/A'}</td>
                        <td class="user-management-actions">
                            <a href="editUser.jsp?id=${user.id}" class="user-management-btn user-management-edit-btn">Edit</a>
                            <form method="post" action="deleteUser" style="display:inline;">
                                <input type="hidden" name="id" value="${user.id}">
                                <button type="submit" class="user-management-btn user-management-delete-btn">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div >
                No users found in database
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>