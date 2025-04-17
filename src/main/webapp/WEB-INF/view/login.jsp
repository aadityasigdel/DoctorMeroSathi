<%--
  User: Aaditya sigdel
  Date: 4/5/2025
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<div>
    <h1>Login</h1>
    <form method="post" action="login.jsp">
        <fieldset>
            <legend>Login</legend>
            <label for="email">Email: <input type="email" id="email" name="email" required></label><br><br>
            <label for="password">Password: <input type="password" id="password" name="password" required></label><br><br>
            <input type="submit" value="Login">
        </fieldset>
    </form>
</div>
</body>
</html>
