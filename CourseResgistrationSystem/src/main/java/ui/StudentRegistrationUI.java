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

public class StudentRegistrationUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Registration");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2));

        JTextField nameField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField programField = new JTextField();
        JTextField yearField = new JTextField();
        JTextField contactField = new JTextField();
        JButton registerButton = new JButton("Register");

        frame.add(new JLabel("Name:"));
        frame.add(nameField);
        frame.add(new JLabel("DOB (YYYY-MM-DD):"));
        frame.add(dobField);
        frame.add(new JLabel("Program:"));
        frame.add(programField);
        frame.add(new JLabel("Year:"));
        frame.add(yearField);
        frame.add(new JLabel("Contact:"));
        frame.add(contactField);
        frame.add(registerButton);

        registerButton.addActionListener(e -> {
            Student student = new Student(
                    Integer.parseInt(yearField.getText()), Integer.parseInt(yearField.getText()), nameField.getText(),
                programField.getText(),
                contactField.getText());
            StudentService studentService = new StudentService();
            boolean result = studentService.registerStudent(student);
            JOptionPane.showMessageDialog(frame, result ? "Registration Successful!" : "Registration Failed");
        });

        frame.setVisible(true);
    }
}
