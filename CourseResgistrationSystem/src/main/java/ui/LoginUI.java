/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.UserDAO;
import entity.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Admin
 */

public class LoginUI {
    private static final UserDAO userDAO = new UserDAO();

    public static void showLoginScreen() {
        try {
            // Set Nimbus Look and Feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Create the main login frame
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null);

        // Create components for username and password using GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);

        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(loginButton, gbc);

        frame.add(panel);

        // Add the login action listener
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            // Validate user credentials – assumes UserDAO.validateUser returns a User object if successful
            User user = userDAO.validateUser(username, password);
            if (user != null) {
                frame.dispose(); // Close the login window
                // Retrieve and check the user role (converting it to lower case for consistency)
                String role = user.getRole().toLowerCase();
                if (role.equals("admin")) {
                    // Redirect to Admin dashboard
                    AdminDashboardUI.showDashboard(user);
                } else if (role.equals("faculty")) {
                    // Redirect to Faculty dashboard
                    FacultyDashboardUI.showDashboard(user);
                } else if (role.equals("student")) {
                    // Redirect to Student dashboard
                    StudentDashboardUI.showDashboard(user);
                } else {
                    JOptionPane.showMessageDialog(null, "User role not recognized: " + user.getRole());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    // Optional: main method to run the login screen
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginUI::showLoginScreen);
    }
}
