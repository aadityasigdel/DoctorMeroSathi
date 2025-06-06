<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/24/2025
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Doctor Login - Doctor Mero Sathi</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<script>
    const urlParams = new URLSearchParams(window.location.search);
    const message = urlParams.get('message');
    if (message) {
        alert(message);

        if (window.history.replaceState) {
            const url = new URL(window.location);
            url.searchParams.delete('message');
            window.history.replaceState({}, document.title, url.toString());
        }
    }
</script>

<c:if test="${not empty error}">
    <script>
        alert("${error}");
    </script>
</c:if>

<div class="login-container">
    <div class="image-section">
        <img src="${pageContext.request.contextPath}/assets/DoctorLogin.svg" alt="Logo">
    </div>
    <div class="form-section">
        <div class="logo-area">
            <span class="logo-text">Doctor Mero Sathi</span>
        </div>
        <h1>Welcome Back, Doctor</h1>
        <form method="post" action="${pageContext.request.contextPath}/login">
            <div class="input-group">
                <label for="email">Email</label>
                <i class="fas fa-envelope icon"></i>
                <input type="email" id="email" name="email" placeholder="Your Email" required>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <i class="fas fa-lock icon"></i>
                <input type="password" id="password" name="password" placeholder="Your Password" required>
            </div>
            <div class="remember-forgot">
                <div class="remember-me">
                    <input type="checkbox" id="remember" name="remember">
                    <label for="remember">Remember me</label>
                </div>
                <div class="forgot-password">
                    <a href="#">Forgot password</a>
                </div>
            </div>
            <button type="submit">Login</button>
        </form>
        <div class="signup-link">
            New here?  <a href="${pageContext.request.contextPath}/signup" class="nav-link login-btn">Sign Up</a>
        </div>
    </div>
</div>
</body>
</html>