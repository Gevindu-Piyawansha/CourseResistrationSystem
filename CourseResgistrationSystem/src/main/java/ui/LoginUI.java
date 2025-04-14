/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.StudentDAO;
import dao.UserDAO;
import entity.Student;
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
    // Create a static instance of UserDAO to interact with the database.
    private static final UserDAO userDAO = new UserDAO();

    public static void showLoginScreen() {
        try {
            // Set Nimbus look and feel for a modern UI appearance.
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Create the main frame.
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 400);
        frame.setLocationRelativeTo(null);
        
        // Create a panel with an overridden paintComponent to draw the background image.
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            Image backgroundImage = new ImageIcon(getClass().getResource("/images/background.jpg")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setOpaque(true);
        
        // Create header panel (transparent) for the title.
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setOpaque(false);  // Transparent to show the background image.
        JLabel titleLabel = new JLabel("Welcome to the Course Registration System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(90, 150, 180));
        headerPanel.add(titleLabel);
        
        // Create the login form panel (transparent).
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Username label and text field.
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(userLabel, gbc);
        
        JTextField usernameField = new JTextField(20);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(usernameField, gbc);
        
        // Password label and password field.
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passLabel, gbc);
        
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);
        
        // Login button.
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(loginButton, gbc);
        
        // Assemble panels on the background panel.
        backgroundPanel.add(headerPanel, BorderLayout.NORTH);
        backgroundPanel.add(formPanel, BorderLayout.CENTER);
        frame.setContentPane(backgroundPanel);
        
        // Set up action listener for the login button.
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            
            // Validate the user's credentials.
            User user = userDAO.validateUser(username, password);
            if (user != null) {
                frame.dispose();  // Close the login form.
                if (user.getRole().equalsIgnoreCase("student")) {
                    if (user.isMustChangePassword()) {
                        JOptionPane.showMessageDialog(null,
                                "You are using default credentials. Please update them.",
                                "Update Required", JOptionPane.INFORMATION_MESSAGE);
                        new StudentCredentialsSetupUI(user).setVisible(true);
                    } else {
                        StudentDAO studentDAO = new StudentDAO();
                        Student student = studentDAO.getStudentById(user.getId());
                        if (student != null) {
                            StudentDashboardUI.showDashboard(student);
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Student details not found.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else if (user.getRole().equalsIgnoreCase("admin")) {
                    AdminDashboardUI.showDashboard(user);
                } else if (user.getRole().equalsIgnoreCase("faculty")) {
                    FacultyDashboardUI.showDashboard(user);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Unrecognized user role: " + user.getRole());
                }
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Invalid username or password",
                        "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginUI::showLoginScreen);
    }
}
