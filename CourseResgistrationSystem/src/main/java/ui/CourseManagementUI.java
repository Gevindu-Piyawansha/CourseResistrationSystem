/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import service.CourseService;
import entity.Course;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Admin
 */



public class CourseManagementUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Course Management");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton addButton = new JButton("Add Course");
        JButton deleteButton = new JButton("Delete Course");
        JButton updateButton = new JButton("Update Course");

        addButton.addActionListener(e -> {
            // Open dialog or another window for adding a course
            // This can be expanded to collect data via inputs and call CourseService.addCourse()
        });

        deleteButton.addActionListener(e -> {
            // Open dialog to select a course and delete it using CourseService.deleteCourse()
        });

        updateButton.addActionListener(e -> {
            // Open dialog to select and update course
        });

        frame.add(addButton, BorderLayout.NORTH);
        frame.add(deleteButton, BorderLayout.CENTER);
        frame.add(updateButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
