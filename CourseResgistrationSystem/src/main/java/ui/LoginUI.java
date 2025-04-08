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
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null);

        // Custom background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel(loadImage("/images/background.jpg"));
        backgroundPanel.setLayout(new GridBagLayout());
        frame.setContentPane(backgroundPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Semi-transparent overlay panel
        JPanel overlayPanel = new JPanel();
        overlayPanel.setLayout(new BoxLayout(overlayPanel, BoxLayout.Y_AXIS));
        overlayPanel.setOpaque(false);
        overlayPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Heading label
        JLabel headingLabel = new JLabel("Course Management System");
        headingLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        overlayPanel.add(headingLabel);

        // Optional subtitle
        JLabel subtitleLabel = new JLabel("Manage Courses, Faculty, and More");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        overlayPanel.add(subtitleLabel);

        overlayPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Form panel for username/password
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setOpaque(false);
        formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JTextField usernameField = new JTextField(15);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        overlayPanel.add(formPanel);
        overlayPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 120, 215));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        overlayPanel.add(loginButton);

        // Add the overlay panel to the background panel using GridBagConstraints
        backgroundPanel.add(overlayPanel, gbc);

        // Login action
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User user = userDAO.authenticateUser(username, password);
            if (user != null) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                switch (user.getRole().toLowerCase()) {
                    case "admin":
                        AdminDashboardUI.showDashboard();
                        break;
                    case "faculty":
                        FacultyDashboardUI.showDashboard();
                        break;
                    default:
                        StudentDashboardUI.showDashboard();
                        break;
                }
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    // Loads a background image
    private static Image loadImage(String path) {
        java.net.URL imgURL = LoginUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
    }

    // Custom panel for drawing the background
    static class BackgroundPanel extends JPanel {
        private final Image image;

        public BackgroundPanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        showLoginScreen();
    }
}
