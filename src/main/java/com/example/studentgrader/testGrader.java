package com.example.studentgrader;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/testGrader")
public class testGrader extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testId = request.getParameter("test_id");

        List<Question> questions = DatabaseConnection.fetchQuestionsFromDatabase(testId);

        int totalQuestions = questions.size();
        int correctAnswers = 0;

        for (Question question : questions) {
            int questionId = question.getQuestionId();
            String userAnswer = request.getParameter("answer_" + questionId);
            String correctAnswer = question.getCorrectAnswer();

            if (userAnswer != null && userAnswer.equals(correctAnswer)) {
                correctAnswers++;
            }
        }

        double score = ((double) correctAnswers / totalQuestions) * 100;

        String username = (String) request.getSession().getAttribute("User");

        try {
            DatabaseConnection.addStudentGrade(username, score, testId);
        } catch (SQLException e) {
            e.printStackTrace();
    }

        request.setAttribute("score", score);
        request.getRequestDispatcher("results.jsp").forward(request, response);
    }
}