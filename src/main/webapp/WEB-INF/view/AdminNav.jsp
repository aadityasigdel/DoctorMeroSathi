<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Navigation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/AdminNav.css">
</head>
<body>
<div class="sidebar">
    <div class="logo-area">
        <img src="${pageContext.request.contextPath}/assets/DocLogo.jpg" alt="DoctorMeroSathi Logo">
        <span>DoctorMeroSathi</span>
    </div>
    <nav>
        <ul>
            <li class="active">
                <a href="${pageContext.request.contextPath}/admin/dashboard">
                    <i class="fas fa-tachometer-alt"></i>
                    Dashboard
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/appointmentmgr">
                    <i class="fas fa-user-md"></i>
                    Appointments
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/admin/usersmgr">
                    <i class="fas fa-hospital-user"></i>
                    users
                </a>
            </li>

            <li class="logout">
                <a href="${pageContext.request.contextPath}/logout">
                    <i class="fas fa-sign-out-alt"></i>
                    Logout
                </a>
            </li>
        </ul>
    </nav>
</div>
    </body>
</html>