/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import dao.UserDAO;
import entity.User;
import service.AuthService;

import javax.swing.*;
import java.awt.*;
import security.RBACUtils;
/**
 *
 * @author Admin
 */




public class LoginUI {
    private static AuthService authService = new AuthService();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextField usernameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            String role = authService.login(username, password);
            if (role != null) {
                JOptionPane.showMessageDialog(frame, "Login successful! Role: " + role);
                frame.dispose();
                // Redirect based on role
                if ("STUDENT".equals(role)) {
                    StudentDashboardUI.showDashboard();
                } else if ("FACULTY".equals(role)) {
                    FacultyDashboardUI.showDashboard();
                } else if ("ADMIN".equals(role)) {
                    AdminDashboardUI.showDashboard();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials!");
            }
        });

        frame.add(new JLabel("Username:"));
        frame.add(usernameField);
        frame.add(new JLabel("Password:"));
        frame.add(passwordField);
        frame.add(loginButton);

        frame.setVisible(true);
    }
    
   private static UserDAO userDAO = new UserDAO();

    public static void showLoginScreen() {
        JFrame frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            User user = userDAO.authenticateUser(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                frame.dispose();

                // Open role-based dashboard
                if (RBACUtils.hasAdminAccess(user.getRole())) {
                    AdminDashboardUI.showDashboard();
                } else if (RBACUtils.hasFacultyAccess(user.getRole())) {
                    FacultyDashboardUI.showDashboard();
                } else {
                    StudentDashboardUI.showDashboard();
                }

            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(new JLabel());
        frame.add(loginButton);

        frame.setVisible(true);
    }
}
