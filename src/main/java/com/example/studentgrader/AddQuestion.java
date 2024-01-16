package com.example.studentgrader;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionNumberParam = request.getParameter("question_number");

        if (questionNumberParam != null && !questionNumberParam.isEmpty()) {
            try {
                int questionNumber = Integer.parseInt(questionNumberParam);

                String correctAnswer = request.getParameter("correct_answer");
                String questionText = request.getParameter("question_text");
                int test_name;
                int questionId;

                try {
                    test_name = Integer.parseInt(request.getParameter("test_name"));
                    questionId = Integer.parseInt(request.getParameter("question_id"));
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Invalid test name or question ID format. Please provide valid integers.");
                    request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
                    return;
                }

                if (questionText.length() > 255 || correctAnswer.length() > 255) {
                    request.setAttribute("error", "Question or answer exceeds the maximum length of 255 characters.");
                    request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
                    return;
                }

                try {
                    if (DatabaseConnection.isQuestionIdAlreadyUsed(questionId)) {
                        request.setAttribute("error", "Question ID is already used. Please select a new ID.");
                        request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
                        return;
                    }

                    DatabaseConnection.addQuestion(questionNumber, questionText, correctAnswer, test_name, questionId);
                    request.setAttribute("message", "Question added successfully!");

                    request.getRequestDispatcher("success.jsp").forward(request, response);
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("error", "Error adding question. Please try again.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid question number format. Please provide a valid integer.");
            }
        } else {
            request.setAttribute("error", "Question number parameter is missing or empty.");
        }

        request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
    }
}
