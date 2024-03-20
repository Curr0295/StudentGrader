<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Choose Test</title>
  <link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>
<h1>Choose Test</h1>
<%
  String error = request.getParameter("error");
  if (error != null && error.equals("invalidTestId")) {
%>
<p style="color: red;">ERROR! Test doesn't exist</p>
<%
  }
%>
<ul>
  <li><a href="home.jsp">Home</a></li>
  <li><a href="results.jsp">Results</a></li>
  <li><a href="test.jsp">Take Test</a></li>
</ul>

<form action="TestPicker" method="post">
  <label for="test_id">Enter Test ID:</label>
  <input type="text" name="test_id" id="test_id">
  <br>
  <input type="submit" value="Start Test">
</form>
</body>
</html>