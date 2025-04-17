<%--
  Created by IntelliJ IDEA.
  User: Aaditya sigdel
  Date: 4/11/2025
  Time: 9:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="">
    <fieldset>
        <legend>Create an Account</legend>
        <label for="full_name">Full Name: <input type="text" id="full_name" name="full_name" required></label><br><br>
        <label for="email">Email: <input type="email" id="email" name="email" required></label><br><br>
        <label for="password">Password: <input type="password" id="password" name="password" required></label><br><br>
        <label for="role">Role:
            <select name="role" id="role" required>
                <option value="">--Select--</option>
                <option value="customer">Customer</option>
                <option value="doctor">Doctor</option>
            </select>
        </label><br><br>
        <label for="phone">Phone: <input type="text" id="phone" name="phone"></label><br><br>
        <label for="gender">Gender:
            <select name="gender" id="gender">
                <option value="">--Select--</option>
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
            </select>
        </label><br><br>
        <label for="dob">Date of Birth: <input type="date" id="dob" name="dob"></label><br><br>
        <label for="specialization">Specialization: <input type="text" id="specialization" name="specialization"></label><br><br>
        <label for="experience">Experience (Years): <input type="number" id="experience" name="experience" min="0"></label><br><br>
        <input type="submit" value="Register">
    </fieldset>
</form>

</body>
</html>
