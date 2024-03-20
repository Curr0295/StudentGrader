<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Test Result</title>
    <link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>

<ul>
    <li><a href="home.jsp">Home</a></li>
    <li><a href="test.jsp">Take Test</a></li>
    <li><a href="StudentGradeView.jsp">Your Grades</a></li>
</ul>
<h1>Test Result</h1>

<p>Your score for this test is: ${score}%</p>

</body>
</html>