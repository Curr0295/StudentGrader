<%--
  Created by IntelliJ IDEA.
  User: alyci
  Date: 2024-01-08
  Time: 1:26 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Question</title>
    <link rel="stylesheet" type="text/css" href="decor.css">
</head>
<body>
<h1>Add Question</h1>

<% if (request.getAttribute("message") != null) { %>
<p style="color: green;"><%= request.getAttribute("message") %></p>
<% } %>

<% if (request.getAttribute("error") != null) { %>
<p style="color: red;"><%= request.getAttribute("error") %></p>
<% } %>

<form action="AddQuestion" method="post">
    <label for="question_number">Question Number:</label>
    <input type="text" id="question_number" name="question_number" required>
    <br>
    <label for="question_text">Question:</label>
    <input type="text" id="question_text" name="question_text" required>
    <label for="question_id">Question ID:</label>
    <input type="text" id="question_id" name="question_id" required>
    <br>
    <label for="correct_answer">Correct Answer:</label>
    <input type="text" id="correct_answer" name="correct_answer" required>
    <br>
    <label for="test_name">Test Number</label>
    <input type="text" id="test_name" name="test_name" required>
    <br>
    <input type="submit" value="Add Question">
</form>

<% if (request.getAttribute("message") != null) { %>
<p><a href="addQuestion.jsp">Add Another Question</a></p>
<% } %>

<p><a href="TeacherInterface.jsp">Back to Teacher Interface</a></p>
</body>
</html>