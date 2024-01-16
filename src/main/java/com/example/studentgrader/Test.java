package com.example.studentgrader;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/TestPicker")
public class Test extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testId = request.getParameter("test_id");

        List<Question> questions = fetchQuestionsFromDatabase(testId);

        if (questions != null && !questions.isEmpty()) {
            List<String> questionTexts = new ArrayList<>();
            List<String> correctAnswers = new ArrayList<>();

            for (Question question : questions) {
                questionTexts.add(question.getQuestionText());
                correctAnswers.add(question.getCorrectAnswer());
            }

            request.setAttribute("test_id", testId);
            request.setAttribute("questionTexts", questionTexts);
            request.setAttribute("correctAnswers", correctAnswers);
            request.setAttribute("questions", questions);
            request.getRequestDispatcher("takeTest.jsp").forward(request, response);
        } else {
            response.sendRedirect("test.jsp?error=invalidTestId");
        }
    }

    private List<Question> fetchQuestionsFromDatabase(String testId) {
        List<Question> questions = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT question_number,question_text, correct_answer, question_id FROM tests WHERE test_name = ? ORDER BY RAND()");
        ) {
            preparedStatement.setString(1, testId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int questionId = resultSet.getInt("question_id");
                    int question_number = resultSet.getInt("question_number");
                    String question_Text = resultSet.getString("question_text");
                    String correctAnswer = resultSet.getString("correct_answer");

                    Question question = new QuestionBuilder().setQuestionId(questionId).setQuestionNumber(question_number).setQuestionText(question_Text).setCorrectAnswer(correctAnswer).createQuestion();
                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

}