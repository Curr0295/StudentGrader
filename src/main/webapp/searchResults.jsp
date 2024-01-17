<%@ page import="java.util.List" %>
<%@ page import="com.example.studentgrader.StudentGrade" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Results</title>
    <link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>
<ul>
    <li><a href="TeacherInterface.jsp">Home</a></li>
    <li><a href="account.jsp">Account</a></li>
</ul>

<%
    String searchedUsername = (String) request.getAttribute("searchedUsername");
    List<StudentGrade> searchedStudentGrades = (List<StudentGrade>) request.getAttribute("searchedStudentGrades");

    if (searchedStudentGrades != null && !searchedStudentGrades.isEmpty()) {
%>
<h3>Search Results:</h3>
<p>Student Grades for <%= searchedUsername %>:</p>
<table class="student-grades-table">
    <thead>
    <tr>
        <th>Test ID</th>
        <th>Grade</th>
    </tr>
    </thead>
    <tbody>
    <% for (StudentGrade grade : searchedStudentGrades) { %>
    <tr>
        <td><%= grade.getTestId() %></td>
        <td><%= grade.getGrade() %></td>
    </tr>
    <% } %>
    </tbody>
</table>
<%
} else {
%>
<p>No student grades found for <%= searchedUsername %>.</p>
<%
    }
%>
</body>
</html>