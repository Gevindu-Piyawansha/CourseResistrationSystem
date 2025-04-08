/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import service.StudentService;
import entity.Student;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Admin
 */

public class StudentRegistrationUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            
            JFrame frame = new JFrame("Student Registration");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            
            JPanel mainPanel = new JPanel(new GridBagLayout());
            mainPanel.setBackground(Color.WHITE);
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;     // Allows text fields to expand horizontally
            gbc.anchor = GridBagConstraints.CENTER;
            
            JLabel nameLabel = new JLabel("Name:");
            nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            JTextField nameField = new JTextField(25);
            nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            
            JLabel dobLabel = new JLabel("DOB (YYYY-MM-DD):");
            dobLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            JTextField dobField = new JTextField(25);
            dobField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            
            JLabel programLabel = new JLabel("Program:");
            programLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            JTextField programField = new JTextField(25);
            programField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            
            JLabel yearLabel = new JLabel("Year:");
            yearLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            JTextField yearField = new JTextField(25);
            yearField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            
            JLabel contactLabel = new JLabel("Contact:");
            contactLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
            JTextField contactField = new JTextField(25);
            contactField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            
            JButton registerButton = new JButton("Register");
            registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
            registerButton.setBackground(new Color(0, 120, 215));
            registerButton.setForeground(Color.WHITE);
            registerButton.setFocusPainted(false);
            
            int row = 0;
            
            // Row 0: Name label and field
            gbc.gridx = 0; 
            gbc.gridy = row; 
            gbc.weightx = 0;    // Label is not stretched
            mainPanel.add(nameLabel, gbc);
            
            gbc.gridx = 1; 
            gbc.weightx = 1;    // TextField can stretch
            mainPanel.add(nameField, gbc);
            
            // Row 1: DOB label and field
            row++;
            gbc.gridx = 0; 
            gbc.gridy = row; 
            gbc.weightx = 0; 
            mainPanel.add(dobLabel, gbc);
            
            gbc.gridx = 1; 
            gbc.weightx = 1; 
            mainPanel.add(dobField, gbc);
            
            // Row 2: Program label and field
            row++;
            gbc.gridx = 0; 
            gbc.gridy = row; 
            gbc.weightx = 0; 
            mainPanel.add(programLabel, gbc);
            
            gbc.gridx = 1; 
            gbc.weightx = 1; 
            mainPanel.add(programField, gbc);
            
            // Row 3: Year label and field
            row++;
            gbc.gridx = 0; 
            gbc.gridy = row; 
            gbc.weightx = 0; 
            mainPanel.add(yearLabel, gbc);
            
            gbc.gridx = 1; 
            gbc.weightx = 1; 
            mainPanel.add(yearField, gbc);
            
            // Row 4: Contact label and field
            row++;
            gbc.gridx = 0; 
            gbc.gridy = row; 
            gbc.weightx = 0; 
            mainPanel.add(contactLabel, gbc);
            
            gbc.gridx = 1; 
            gbc.weightx = 1; 
            mainPanel.add(contactField, gbc);
            
            // Row 5: Register button (centered, spanning both columns)
            row++;
            gbc.gridx = 0; 
            gbc.gridy = row; 
            gbc.gridwidth = 2; 
            gbc.weightx = 0; 
            gbc.anchor = GridBagConstraints.CENTER;
            mainPanel.add(registerButton, gbc);
            
            registerButton.addActionListener(e -> {
                try {
                    Student student = new Student(
                        Integer.parseInt(yearField.getText()), 
                        Integer.parseInt(yearField.getText()), 
                        nameField.getText(),
                        programField.getText(),
                        contactField.getText()
                    );
                    StudentService studentService = new StudentService();
                    boolean result = studentService.registerStudent(student);
                    JOptionPane.showMessageDialog(frame, 
                        result ? "Registration Successful!" : "Registration Failed");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, 
                        "Please enter valid numerical values for Year", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            });
            
            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}
