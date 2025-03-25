/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.EnrollmentDAO;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Admin
 */

public class EnrollmentUI {

    public static void showUI() {
        JFrame frame = new JFrame("Enrollment Management");
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JTextField studentIdField = new JTextField(5);
        JTextField courseIdField = new JTextField(5);
        JButton enrollButton = new JButton("Enroll");
        JButton dropButton = new JButton("Drop");

        frame.add(new JLabel("Student ID:"));
        frame.add(studentIdField);
        frame.add(new JLabel("Course ID:"));
        frame.add(courseIdField);
        frame.add(enrollButton);
        frame.add(dropButton);

        enrollButton.addActionListener(e -> {
            new EnrollmentDAO().enrollStudent(Integer.parseInt(studentIdField.getText()), Integer.parseInt(courseIdField.getText()));
            JOptionPane.showMessageDialog(frame, "Student Enrolled!");
        });

        dropButton.addActionListener(e -> {
            new EnrollmentDAO().dropCourse(Integer.parseInt(studentIdField.getText()), Integer.parseInt(courseIdField.getText()));
            JOptionPane.showMessageDialog(frame, "Course Dropped!");
        });

        frame.setVisible(true);
    }
}
