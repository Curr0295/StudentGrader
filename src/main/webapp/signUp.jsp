<%--
  Created by IntelliJ IDEA.
  User: alyci
  Date: 2023-12-19
  Time: 5:17 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up!</title>
    <link rel="stylesheet" type="text/css" href="decor.css">
</head>
<body>
<form action="Authenticator" method="post">
    <label for="Username">Username:</label>
    <input type="text" name="Username" id="Username" required>
    <br>
    <label for="Password">Password:</label>
    <input type="password" id="Password" name="Password" required>
    <br>
    <label>Role:</label>
    <div class="radio-container">

        <input type="radio" name="Role" value="Student" id="Student" required>
        <label for="Student">Student</label>

        <input type="radio" name="Role" value="Teacher" id="Teacher" required>
        <label for="Teacher">Teacher</label>
    </div>

    <input type="hidden" name="action" value="signup">
    <input type="submit" value="Submit">
</form>
</body>
</html>