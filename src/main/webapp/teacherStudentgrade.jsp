<%@ page import="com.example.studentgrader.StudentGrade" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Results</title>
    <link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>
<ul>
    <li><a href="home.jsp">Home</a></li>
    <li><a href="results.jsp">Results</a></li>
    <li><a href="account.jsp">Account</a></li>
    <li><a href="test.jsp">Take Test</a></li>
    <li><a href="StudentGradeView.jsp">Your Grades</a></li>
</ul>
<h2>Search for a student</h2>
<form action="GradeSearchServlet" method="post">
    Enter Student Username: <input type="text" name="username">
    <input type="submit" value="Search">
</form>
</body>

</html>