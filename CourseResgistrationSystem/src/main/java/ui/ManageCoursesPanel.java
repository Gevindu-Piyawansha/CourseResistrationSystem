/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.CourseDAO;
import entity.Course;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Admin
 */



public class ManageCoursesPanel extends JPanel {
    private CourseDAO courseDAO = new CourseDAO();
    private JTable table;
    private DefaultTableModel tableModel;

    public ManageCoursesPanel() {
        setLayout(new BorderLayout());
        
        // Define table columns
        String[] columnNames = {"ID", "Course Code", "Course Name", "Credits"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load data into table
        refreshTable();

        // Button to add a new course
        JButton addCourseBtn = new JButton("Add Course");
        add(addCourseBtn, BorderLayout.SOUTH);
        addCourseBtn.addActionListener(e -> {
            String courseCode = JOptionPane.showInputDialog("Enter course code:");
            if (courseCode == null || courseCode.trim().isEmpty()) return;
            
            String courseName = JOptionPane.showInputDialog("Enter course name:");
            if (courseName == null || courseName.trim().isEmpty()) return;
            
            String creditsStr = JOptionPane.showInputDialog("Enter credits:");
            int credits = 0;
            try {
                credits = Integer.parseInt(creditsStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid credits value.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Create a new course. Here we pass an empty description.
            Course newCourse = new Course(0, courseCode, courseName, "", credits);
            
            // Call addCourse method (returns void)
            courseDAO.addCourse(newCourse);
            JOptionPane.showMessageDialog(this, "Course added successfully!");
            refreshTable();
        });
    }
    
    // Method to refresh table data
    private void refreshTable() {
        // Clear existing rows
        tableModel.setRowCount(0);
        // Get all courses and add rows to the table model
        List<Course> courses = courseDAO.getAllCourses();
        for (Course c : courses) {
            Object[] row = { c.getId(), c.getCourseCode(), c.getCourseName(), c.getCredits() };
            tableModel.addRow(row);
        }
    }
}
