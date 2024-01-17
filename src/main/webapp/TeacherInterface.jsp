<%--
  Created by IntelliJ IDEA.
  User: alyci
  Date: 2024-01-08
  Time: 12:49 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Teacher Interface</title>
  <link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>
<h1>Teacher Interface</h1>
<div id=teacherWelcome>
<p>Welcome, <%=session.getAttribute("Username") %></p>
<p>This is the teacher interface.</p>
</div>
<ul>
  <li><a href="addQuestion.jsp">Add question</a></li>
  <li><a href="index.jsp">Log out</a></li>
  <li><a href="teacherStudentgrade.jsp">Student Grades</a></li>
</ul>
</body>
</html>