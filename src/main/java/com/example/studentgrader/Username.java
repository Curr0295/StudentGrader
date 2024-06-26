package com.example.studentgrader;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Username {
    private final String Username;
    private final String Password;
    private final String Role;

    public Username(String Username, String Password, String Role) {
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
    }

    public String getUsername() {
        return Username;
    }

    public String getRole() {
        return Role;
    }

    public void saveToDatabase(Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO user_login (username, password,user_role) VALUES (?, ?,?)")) {
            ps.setString(1, Username);
            ps.setString(2, Password);
            ps.setString(3, Role);
            ps.executeUpdate();
        }
    }
}

