/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import service.EnrollmentService;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Admin
 */


public class EnrollmentUI {
    private static EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Course Enrollment");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextField studentIdField = new JTextField(10);
        JTextField courseIdField = new JTextField(10);
        JButton enrollButton = new JButton("Enroll");

        enrollButton.addActionListener(e -> {
            int studentId = Integer.parseInt(studentIdField.getText());
            int courseId = Integer.parseInt(courseIdField.getText());

            boolean success = enrollmentService.enrollStudent(studentId, courseId);
            if (success) {
                JOptionPane.showMessageDialog(frame, "Enrollment successful!");
            } else {
                JOptionPane.showMessageDialog(frame, "Enrollment failed.");
            }
        });

        frame.add(new JLabel("Student ID:"));
        frame.add(studentIdField);
        frame.add(new JLabel("Course ID:"));
        frame.add(courseIdField);
        frame.add(enrollButton);

        frame.setVisible(true);
    }
}
