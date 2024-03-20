<%@ page import="com.example.studentgrader.StudentGrade" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Grades</title>
    <link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>
<h2>Your Grades</h2>
<ul>
    <li><a href="home.jsp">Home</a></li>
    <li><a href="StudentGradeView.jsp">Your Grades</a></li>
</ul>
<table class="student-grades-table">
    <thead>
    <tr>
        <th>Test ID</th>
        <th>Grade</th>
    </tr>
    </thead>
    <tbody>
    <% List<StudentGrade> studentGrades = com.example.studentgrader.DatabaseConnection.getStudentGrades();
        for (StudentGrade grade : studentGrades) { %>
    <tr>
        <td><%= grade.getTestId() %></td>
        <td><%= grade.getGrade() %></td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>