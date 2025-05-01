<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/24/2025
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Access Denied</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Css/acessdenied.css"> <!-- Adjust path as necessary -->
</head>
<body>
<div class="container">
    <h1>Access Denied</h1>
    <p>You do not have permission to access this page.</p>
    <p><a href="<%= request.getContextPath() %>/">Return to Home</a></p>
</div>
</body>
</html>