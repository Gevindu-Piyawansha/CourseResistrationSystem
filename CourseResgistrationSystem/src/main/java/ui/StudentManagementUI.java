/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.StudentDAO;
import entity.Student;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Admin
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class StudentManagementUI {

    public static void showUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JFrame frame = new JFrame("Student Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 550);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        
        // Header
        JLabel header = new JLabel("Add Student");
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(header, gbc);

        gbc.gridwidth = 1;

        // New User ID Field (to supply the user's id from the users table)
        gbc.gridy = 1;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("User ID:"), gbc);
        gbc.gridx = 1;
        JTextField userIdField = new JTextField(10);
        userIdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(userIdField, gbc);

        // First Name Field
        gbc.gridy = 2;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        JTextField firstNameField = new JTextField(20);
        firstNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(firstNameField, gbc);

        // Last Name Field
        gbc.gridy = 3;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        JTextField lastNameField = new JTextField(20);
        lastNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(lastNameField, gbc);

        // DOB Field
        gbc.gridy = 4;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("DOB (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        JTextField dobField = new JTextField(10);
        dobField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(dobField, gbc);

        // Program Field
        gbc.gridy = 5;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Program:"), gbc);
        gbc.gridx = 1;
        JTextField programField = new JTextField(20);
        programField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(programField, gbc);

        // Email Field
        gbc.gridy = 6;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        JTextField emailField = new JTextField(20);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(emailField, gbc);

        // Enrollment Year Field
        gbc.gridy = 7;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Enrollment Year:"), gbc);
        gbc.gridx = 1;
        JTextField yearField = new JTextField(5);
        yearField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(yearField, gbc);

        // Add Button
        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton addButton = new JButton("Add Student");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addButton.setBackground(new Color(0, 120, 215));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        mainPanel.add(addButton, gbc);

        addButton.addActionListener(e -> {
            String userIdStr = userIdField.getText().trim();
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String dobInput = dobField.getText().trim();
            String program = programField.getText().trim();
            String email = emailField.getText().trim();
            String yearStr = yearField.getText().trim();

            if (userIdStr.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || dobInput.isEmpty() ||
                program.isEmpty() || email.isEmpty() || yearStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int userId = Integer.parseInt(userIdStr);
                int enrollmentYear = Integer.parseInt(yearStr);
                // Validate date format. Throws exception if not valid.
                java.time.LocalDate dobDate = java.time.LocalDate.parse(dobInput);
                String dobFormatted = dobDate.toString();
                // Create the student object with id=0 (auto-generated) and set the studentId as the user id.
                Student student = new Student(userId, firstName, lastName, dobFormatted, program, email, enrollmentYear);
                new StudentDAO().addStudent(student);
                JOptionPane.showMessageDialog(frame, "Student Added!");
                refreshTable(); // make sure you have a method to refresh your table.
            } catch (java.time.format.DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter the date in YYYY-MM-DD format.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "User ID and Enrollment Year must be valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    // Dummy refreshTable method; implement as needed to update your table data.
    private static void refreshTable() {
        // Implementation code to refresh your table.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementUI::showUI);
    }
}
