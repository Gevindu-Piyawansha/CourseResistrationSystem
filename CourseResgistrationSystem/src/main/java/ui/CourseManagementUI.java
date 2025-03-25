/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.CourseDAO;
import entity.Course;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Admin
 */
public class CourseManagementUI {

    public static void showUI() {
        JFrame frame = new JFrame("Course Management");
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JTextField titleField = new JTextField(20);
        JTextField creditHoursField = new JTextField(5);
        JTextField departmentField = new JTextField(20);
        JButton addButton = new JButton("Add Course");

        frame.add(new JLabel("Title:"));
        frame.add(titleField);
        frame.add(new JLabel("Credit Hours:"));
        frame.add(creditHoursField);
        frame.add(new JLabel("Department:"));
        frame.add(departmentField);
        frame.add(addButton);

        addButton.addActionListener(e -> {
            Course course = new Course(0, titleField.getText(), Integer.parseInt(creditHoursField.getText()), departmentField.getText(), "", 50);
            new CourseDAO().addCourse(course);
            JOptionPane.showMessageDialog(frame, "Course Added!");
        });

        frame.setVisible(true);
    }
}
