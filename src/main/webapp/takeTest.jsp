<%@ page import="com.example.studentgrader.Question" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Take Test</title>
    <link rel="stylesheet" type="text/css" href="home.css">
</head>
<body>
<h1>Take Test</h1>

<p>Test ID: <%= request.getAttribute("test_id") %></p>

<form action="testGrader" method="post">
    <% List<Question> questions = (List<Question>) request.getAttribute("questions");
        for (Question question : questions) { %>
    <div  class="question-container">
        <p><%= question.getQuestionText() %></p>
        <input type="hidden" name="question_id" value="<%= question.getQuestionId() %>">
        <label for="answer_<%= question.getQuestionId() %>">Your Answer:</label>
        <input type="text" name="answer_<%= question.getQuestionId() %>" id="answer_<%= question.getQuestionId() %>">
    </div>
    <% } %>

    <input type="hidden" name="test_id" value="<%= request.getAttribute("test_id") %>">
    <input type="hidden" name="action" value="submitTest">
    <input type="submit" value="Submit Test">
</form>
</body>
</html>