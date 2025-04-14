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


public class StudentCredentialsSetupUI extends JFrame {
    private User user;
    private JTextField usernameField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    
    // You may want to initialize your DAO here.
    private UserDAO userDAO = new UserDAO();

    public StudentCredentialsSetupUI(User user) {
        this.user = user;
        initialize();
    }

    private void initialize() {
        setTitle("Student Credentials Setup");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create a panel with GridBagLayout.
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Instruction label.
        JLabel instructions = new JLabel("Update your credentials, " + user.getUsername());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(instructions, gbc);
        
        gbc.gridwidth = 1;
        
        // New Username label and field.
        JLabel newUsernameLabel = new JLabel("New Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(newUsernameLabel, gbc);
        
        usernameField = new JTextField(user.getUsername(), 20); // prefill with current username
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(usernameField, gbc);
        
        // New Password label and field.
        JLabel newPassLabel = new JLabel("New Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(newPassLabel, gbc);
        
        newPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(newPasswordField, gbc);
        
        // Confirm Password label and field.
        JLabel confirmPassLabel = new JLabel("Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(confirmPassLabel, gbc);
        
        confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(confirmPasswordField, gbc);
        
        // Update button.
        JButton updateButton = new JButton("Update");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(updateButton, gbc);
        
        updateButton.addActionListener(e -> {
            String newUsername = usernameField.getText().trim();
            String newPass = new String(newPasswordField.getPassword());
            String confirmPass = new String(confirmPasswordField.getPassword());
            
            if (newUsername.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Call the DAO to update the user credentials in MySQL.
            boolean updated = userDAO.updateStudentCredentials(user.getId(), newUsername, newPass);
            if (updated) {
                // Update the local user object.
                user.setUsername(newUsername);
                user.setPassword(newPass);
                user.setMustChangePassword(false); // Mark as updated
                
                JOptionPane.showMessageDialog(this, "Credentials updated successfully!");
                
                // Redirect to student's dashboard.
                dispose();
                // You may need to fetch the Student entity from your StudentDAO.
                // For demonstration, we're constructing a Student with minimal info.
                StudentDashboardUI.showDashboard(new entity.Student(user.getId(), newUsername));
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update credentials. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        add(panel);
    }
}
