/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBConnection;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    // Retrieve a user by username
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

// Create a new user record; returns the created user with the generated id.
    public User createUser(User user) {
        String sql = "INSERT INTO users (username, password, role, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getRole());
            pst.setString(4, user.getEmail());
            int affected = pst.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setId(rs.getInt(1));
                    }
                }
                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Authenticate user with plain text password comparison
    public User authenticateUser(String username, String plainPassword) {
        String sql = "SELECT id, username, password, role FROM users WHERE username = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    if (plainPassword.equals(storedPassword)) {
                        return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            storedPassword,
                            rs.getString("role")
                            
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Validate a user’s credentials. (Note: Passwords should be hashed in production.)
    public User validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
             pstmt.setString(1, username);
             pstmt.setString(2, password);
             
             try (ResultSet rs = pstmt.executeQuery()) {
                 if (rs.next()) {
                     User user = new User();
                     user.setId(rs.getInt("id"));
                     user.setUsername(rs.getString("username"));
                     user.setPassword(rs.getString("password"));
                     user.setRole(rs.getString("role"));
                    
                     user.setEmail(rs.getString("email"));
                     return user;
                 }
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // New method: update a student's credentials (username and password) given the user's id.
    public boolean updateStudentCredentials(int userId, String newUsername, String newPassword) {
        String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, newUsername);
            pst.setString(2, newPassword); // In production, hash the password.
            pst.setInt(3, userId);

            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    // Retrieve a user by id.
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, userId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    user.setEmail(rs.getString("email"));
                    return user;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
