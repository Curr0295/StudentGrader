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
@WebServlet("/Authenticator")
public class Authenticator extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("login".equals(action)) {
            handleLogin(req, resp);
        } else if ("signup".equals(action)) {
            handleSignup(req, resp);
        }
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("Username");
        String password = req.getParameter("Password");

        Username user = isValidUser(username, password);

        if (user != null) {
            req.getSession().setAttribute("User", user.getUsername());

            if ("Teacher".equals(user.getRole())) {
                resp.sendRedirect("TeacherInterface.jsp");
            } else if("Student".equals((user.getRole())))
            {
                resp.sendRedirect("home.jsp");
            }
        } else {
            resp.sendRedirect("loginFailed.jsp?error=true");
        }
    }

    private void handleSignup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newUsername = req.getParameter("Username");
        String newPassword = req.getParameter("Password");
        String Role = "Student";

        if (req.getParameter("Role") != null) {
            Role = req.getParameter("Role");
        }

        Username newUser = new Username(newUsername, newPassword, Role);
        Connection connection = null;

        try {
            connection = DatabaseConnection.getConnection();

            System.out.println("Before saving user to database");
            newUser.saveToDatabase(connection);
            System.out.println("User saved to the database");

            if ("Teacher".equals(Role)) {
                resp.sendRedirect("TeacherInterface.jsp");
            } else {
                resp.sendRedirect("home.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendRedirect("signupFailed.jsp?error=true");
        } finally {
            if (connection != null) {
                try {
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Username isValidUser(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM user_login WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String userRole = resultSet.getString("user_role");
                return new Username(username, password, userRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}