<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="decor.css">
</head>
<body>
<h1>Welcome to Edutech Grading</h1>
<p>Please login to continue</p>
<form action="Authenticator" method="post">
    <label for="Username">Username:</label>
    <input type="text" name="Username" id="Username" required><br>

    <label for="Password">Password</label>
    <input type="password" id="Password" name="Password" required><br>

    <input type="hidden" name="action" value="login">
    <input type="submit" value="Submit">
    <a href="signUp.jsp"><input type="button" value="Sign up" ></a>
</form>
<a href="home.jsp">Continue as a Guest!</a><br>
<a href="TeacherInterface.jsp">Continue as a Guest Teacher!</a>
</body>
</html>