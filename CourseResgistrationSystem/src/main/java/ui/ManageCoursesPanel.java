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
import service.CourseService;
/**
 *
 * @author Admin
 */


public class ManageCoursesPanel extends JPanel {
    private CourseService courseService;
    private JTable coursesTable;
    private DefaultTableModel tableModel;
    
    public ManageCoursesPanel() {
        courseService = new CourseService();
        setLayout(new BorderLayout());
        
        // Create table model with columns: id, course_code, course_name, description, credit
        tableModel = new DefaultTableModel(new Object[]{"id", "course_code", "course_name", "description", "credits"}, 0);
        coursesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(coursesTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel for action buttons
        JPanel buttonsPanel = new JPanel();
        JButton addButton = new JButton("Add Course");
        JButton editButton = new JButton("Edit Course");
        JButton deleteButton = new JButton("Delete Course");
        JButton refreshButton = new JButton("Refresh");
        
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(refreshButton);
        add(buttonsPanel, BorderLayout.SOUTH);
        
        // Button action listeners
        addButton.addActionListener(e -> addCourse());
        editButton.addActionListener(e -> editCourse());
        deleteButton.addActionListener(e -> deleteCourse());
        refreshButton.addActionListener(e -> loadCourses());
        
        // Load courses at startup
        loadCourses();
    }
    
    private void loadCourses() {
        List<Course> courses = courseService.getAllCourses();
        tableModel.setRowCount(0); // clear existing rows
        for (Course c : courses) {
            tableModel.addRow(new Object[]{
                c.getId(),
                c.getCourseCode(),
                c.getCourseName(),
                c.getDescription(),
                c.getCredits()
            });
        }
    }
    
    private void addCourse() {
        // Form fields for new course input
        JTextField courseIDField = new JTextField();
        JTextField courseCodeField = new JTextField();
        JTextField courseNameField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField creditField = new JTextField();
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.add(new JLabel("Course ID:"));
        formPanel.add(courseIDField);
        formPanel.add(new JLabel("Course Code:"));
        formPanel.add(courseCodeField);
        formPanel.add(new JLabel("Course Name:"));
        formPanel.add(courseNameField);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(descriptionField);
        formPanel.add(new JLabel("Credit:"));
        formPanel.add(creditField);
        
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Add Course", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
            try {
                int courseID = Integer.parseInt(courseIDField.getText());
                String courseCode = courseCodeField.getText();
                String courseName = courseNameField.getText();
                String description = descriptionField.getText();
                int credit = Integer.parseInt(creditField.getText());
                
                Course newCourse = new Course(courseID, courseCode, courseName, description, credit);
                boolean success = courseService.addCourse(newCourse);
                if(success) {
                    JOptionPane.showMessageDialog(this, "Course added successfully!");
                    loadCourses();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add course.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void editCourse() {
        int selectedRow = coursesTable.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a course to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Get current course data from the table
        int courseId = (int) tableModel.getValueAt(selectedRow, 1);
        String currentCourseCode = (String) tableModel.getValueAt(selectedRow, 2);
        String currentCourseName = (String) tableModel.getValueAt(selectedRow, 3);
        String currentDescription = (String) tableModel.getValueAt(selectedRow, 4);
        int currentCredits = (int) tableModel.getValueAt(selectedRow, 5);
        
        // Form pre-filled with current course data
        JTextField courseCodeField = new JTextField(currentCourseCode);
        JTextField courseNameField = new JTextField(currentCourseName);
        JTextField descriptionField = new JTextField(currentDescription);
        JTextField creditsField = new JTextField(String.valueOf(currentCredits));
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Course Code:"));
        formPanel.add(courseCodeField);
        formPanel.add(new JLabel("Course Name:"));
        formPanel.add(courseNameField);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(descriptionField);
        formPanel.add(new JLabel("Credits:"));
        formPanel.add(creditsField);
        
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Edit Course", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION) {
            try {
                String courseCode = courseCodeField.getText();
                String courseName = courseNameField.getText();
                String description = descriptionField.getText();
                int credits = Integer.parseInt(creditsField.getText());
                
                Course updatedCourse = new Course(courseId, courseCode, courseName, description, credits);
                updatedCourse.setId(courseId);
                boolean success = courseService.updateCourse(updatedCourse);
                if(success) {
                    JOptionPane.showMessageDialog(this, "Course updated successfully!");
                    loadCourses();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update course.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteCourse() {
        int selectedRow = coursesTable.getSelectedRow();
        if(selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a course to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int courseId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected course?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION) {
            boolean success = courseService.deleteCourse(courseId);
            if(success) {
                JOptionPane.showMessageDialog(this, "Course deleted successfully!");
                loadCourses();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete course.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
