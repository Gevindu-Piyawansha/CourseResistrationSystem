/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import service.StudentService;
import entity.Student;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class CourseRegistrationPanel extends JPanel {

    public CourseRegistrationPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Create labels and text fields.
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField firstNameField = new JTextField(25);
        firstNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField lastNameField = new JTextField(25);
        lastNameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel dobLabel = new JLabel("DOB (YYYY-MM-DD):");
        dobLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField dobField = new JTextField(25);
        dobField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

       

        JLabel programLabel = new JLabel("Program:");
        programLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField programField = new JTextField(25);
        programField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField emailField = new JTextField(25);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel yearLabel = new JLabel("Enrollment Year:");
        yearLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField yearField = new JTextField(25);
        yearField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        registerButton.setBackground(new Color(0, 120, 215));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);

        int row = 0;

        // Row 0: First Name.
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        add(firstNameLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(firstNameField, gbc);

        // Row 1: Last Name.
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        add(lastNameLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(lastNameField, gbc);

        // Row 2: DOB.
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        add(dobLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(dobField, gbc);

        // Row 3: Program.
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        add(programLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(programField, gbc);

        // Row 4: Email.
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        add(emailLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(emailField, gbc);

        // Row 5: Enrollment Year.
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        add(yearLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(yearField, gbc);

        // Row 6: Register Button.
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(registerButton, gbc);

        // Register button action.
        registerButton.addActionListener(e -> {
            try {
                int enrollmentYear = Integer.parseInt(yearField.getText().trim());
                // Create a Student with all required fields.
                Student student = new Student(
                        0, // id: auto-generated
                        firstNameField.getText().trim(),
                        lastNameField.getText().trim(),
                        dobField.getText().trim(),
                        programField.getText().trim(),
                        emailField.getText().trim(),
                        enrollmentYear
                );
                StudentService studentService = new StudentService();
                boolean result = studentService.registerStudent(student);
                JOptionPane.showMessageDialog(this,
                        result ? "Registration Successful!" : "Registration Failed");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter a valid number for Enrollment Year",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Course Registration Panel Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new CourseRegistrationPanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
