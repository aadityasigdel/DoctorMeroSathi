<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 4/24/2025
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Sign Up - Doctor Mero Sathi</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Css/signup.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<div class="signup-container">
    <div class="image-section">
        <img src="${pageContext.request.contextPath}/assets/DoctorSignup.svg" alt="Logo">
    </div>
    <div class="form-section">
        <div class="logo-area">
            <span class="logo-text">Doctor Mero Sathi</span>
        </div>
        <h1>Sign Up</h1>
        <form method="post" action="${pageContext.request.contextPath}/signup">
            <div class="input-group">
                <label for="full_name">Full Name</label>
                <i class="fas fa-user icon"></i>
                <input type="text" id="full_name" name="full_name" placeholder="Your Full Name" required>
            </div>
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
            <div class="input-group">
                <label for="role">Role</label>
                <i class="fas fa-briefcase icon"></i>
                <select name="role" id="role" required>
                    <option value="">--Select--</option>
                    <option value="customer">Customer</option>
                    <option value="doctor">Doctor</option>
                </select>
            </div>
            <div class="input-group">
                <label for="gender">Gender</label>
                <i class="fas fa-venus-mars icon"></i>
                <select name="gender" id="gender">
                    <option value="">--Select--</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>
            </div>
            <div class="input-group">
                <label for="dob">Date of Birth</label>
                <i class="fas fa-calendar-alt icon"></i>
                <input type="date" id="dob" name="dob">
            </div>
            <div class="input-group">
                <label for="specialization">Specialization</label>
                <i class="fas fa-stethoscope icon"></i>
                <input type="text" id="specialization" name="specialization" placeholder="Your Specialization (if doctor)">
            </div>
            <div class="input-group">
                <label for="experience">Experience (Years)</label>
                <i class="fas fa-user-md icon"></i>
                <input type="number" id="experience" name="experience" min="0" placeholder="Years of Experience (if doctor)">
            </div>
            <button type="submit" class="signup-button">Sign Up</button>
        </form>
        <div class="login-link">
            Already have an account? <a href="${pageContext.request.contextPath}/login" class="nav-link login-btn">Login</a>
        </div>
    </div>
</div>
</body>
</html>