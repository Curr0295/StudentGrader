package com.example.studentgrader;

import java.sql.*;
import java.util.*;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/edutech";
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        USERNAME = loadUsername();
        PASSWORD = loadPassword();
    }

    private static String loadUsername() {
        return "root";
    }

    private static String loadPassword() {
        return "OOPuser1";
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties props = new Properties();
            props.setProperty("user", USERNAME);
            props.setProperty("password", PASSWORD);
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Error establishing a database connection: " + e.getMessage(), e);
        }
    }

    public static List<StudentGrade> getStudentGrades() {
        List<StudentGrade> studentGrades = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM student_grades");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String studentName = resultSet.getString("username");
                int grade = resultSet.getInt("grade");
                int test_Id = resultSet.getInt("test_id");

                studentGrades.add(new StudentGradeBuilder()
                        .setStudentName(studentName)
                        .setScore(grade)
                        .setTestId(test_Id)
                        .createStudentGrade());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentGrades;
    }

    public static boolean isQuestionIdAlreadyUsed(int question_id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM tests WHERE question_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, question_id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return false;
    }

    public static void addQuestion(int question_number, String question_text, String correct_answer, int test_name, int question_id) throws SQLException {
        if (isQuestionIdAlreadyUsed(question_id)) {
            throw new SQLException("Question ID is already used. Please select a new ID.");
        }

        String sql = "INSERT INTO tests (question_number, question_text, correct_answer, test_name, question_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, question_number);
            statement.setString(2, question_text);
            statement.setString(3, correct_answer);
            statement.setInt(4, test_name);
            statement.setInt(5, question_id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public static List<Question> fetchQuestionsFromDatabase(String testId) {
        List<Question> questions = new ArrayList<>();

        String sql = "SELECT question_number, question_text, correct_answer,question_id FROM tests WHERE test_name = ? ORDER BY question_number";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, Integer.parseInt( testId));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int questionId = resultSet.getInt("question_id");
                    int questionNumber = resultSet.getInt("question_number");
                    String questionText = resultSet.getString("question_text");
                    String correctAnswer = resultSet.getString("correct_answer");

                    Question question = new QuestionBuilder().setQuestionId(questionId).setQuestionNumber(questionNumber).setQuestionText(questionText).setCorrectAnswer(correctAnswer).createQuestion();
                    questions.add(question);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return questions;
    }
    public static void addStudentGrade(String username, double score, String testId) throws SQLException {
        String sql = "INSERT INTO student_grades (username, grade, test_id) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setDouble(2, score);
            statement.setString(3, testId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public static List<StudentGrade> getStudentGradesByUsername(String username) {
        List<StudentGrade> studentGrades = new ArrayList<>();

        String sql = "SELECT test_id, grade FROM student_grades WHERE username = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int testId = resultSet.getInt("test_id");
                    double score = resultSet.getDouble("grade");

                    studentGrades.add(new StudentGradeBuilder().setStudentName(username).setScore(score).setTestId(testId).createStudentGrade());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentGrades;
    }
}
