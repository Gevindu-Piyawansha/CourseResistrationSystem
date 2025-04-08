/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import dao.UserDAO;
import entity.User;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Admin
 */




public class LoginUI {
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
            // Use equalsIgnoreCase to handle different case formats
            if (user.getRole().equalsIgnoreCase("admin")) {
                AdminDashboardUI.showDashboard();
            } else if (user.getRole().equalsIgnoreCase("faculty")) {
                FacultyDashboardUI.showDashboard();
            } else {
                StudentDashboardUI.showDashboard();
            }
            frame.dispose();
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
