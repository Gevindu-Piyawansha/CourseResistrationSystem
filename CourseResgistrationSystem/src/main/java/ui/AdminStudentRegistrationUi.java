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
import java.awt.*;
import java.time.LocalDate;

/**
 *
 * @author Admin
 */


public class AdminStudentRegistrationUi extends JDialog {

    public AdminStudentRegistrationUi() {
        // Set as modal so it always comes in front of its parent.
        setModal(true);
        setTitle("Admin - Register New Student");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header
        JLabel header = new JLabel("Register New Student", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(header, gbc);
        gbc.gridwidth = 1;
        
        // Default Username
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Default Username:"), gbc);
        gbc.gridx = 1;
        JTextField usernameField = new JTextField(20);
        panel.add(usernameField, gbc);
        
        // Default Password
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Default Password:"), gbc);
        gbc.gridx = 1;
        // For consistency, you could force default password "student8"
        JTextField passwordField = new JTextField("student8", 20);
        passwordField.setEditable(false);
        panel.add(passwordField, gbc);
        
        // First Name
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        JTextField firstNameField = new JTextField(20);
        panel.add(firstNameField, gbc);
        
        // Last Name
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        JTextField lastNameField = new JTextField(20);
        panel.add(lastNameField, gbc);
        
        // DOB
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("DOB (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        JTextField dobField = new JTextField(10);
        panel.add(dobField, gbc);
        
        // Program
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Program:"), gbc);
        gbc.gridx = 1;
        JTextField programField = new JTextField(20);
        panel.add(programField, gbc);
        
        // Email
        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        JTextField emailField = new JTextField(20);
        panel.add(emailField, gbc);
        
        // Enrollment Year
        gbc.gridx = 0; gbc.gridy = 8;
        panel.add(new JLabel("Enrollment Year:"), gbc);
        gbc.gridx = 1;
        JTextField yearField = new JTextField(5);
        panel.add(yearField, gbc);
        
        // Add Button
        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 2;
        JButton addButton = new JButton("Register Student");
        panel.add(addButton, gbc);
        
        addButton.addActionListener(e -> {
            String defUsername = usernameField.getText().trim();
            String defPassword = new String(passwordField.getText()); // "student8"
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String dobInput = dobField.getText().trim();
            String program = programField.getText().trim();
            String email = emailField.getText().trim();
            String yearStr = yearField.getText().trim();
            
            if(defUsername.isEmpty() || defPassword.isEmpty() || firstName.isEmpty() || lastName.isEmpty()
               || dobInput.isEmpty() || program.isEmpty() || email.isEmpty() || yearStr.isEmpty()){
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                LocalDate dobDate = LocalDate.parse(dobInput);
                String dobFormatted = dobDate.toString();
                int enrollmentYear = Integer.parseInt(yearStr);
                
                // Create User with default credentials.
                UserDAO userDAO = new UserDAO();
                User newUser = new User(defUsername, defPassword, "student", email);
                newUser = userDAO.createUser(newUser);
                if(newUser == null) {
                    JOptionPane.showMessageDialog(this, "Failed to create user record.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Create Student record with student_id set to the new user's id.
                StudentDAO studentDAO = new StudentDAO();
                Student newStudent = new Student(newUser.getId(), firstName, lastName, dobFormatted, program, email, enrollmentYear);
                studentDAO.addStudent(newStudent);
                
                JOptionPane.showMessageDialog(this, "Student registered successfully!\nDefault credentials:\nUsername: " + defUsername + "\nPassword: " + defPassword);
                dispose(); // Close the registration dialog.
            } catch(Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminStudentRegistrationUi().setVisible(true);
        });
    }
}
