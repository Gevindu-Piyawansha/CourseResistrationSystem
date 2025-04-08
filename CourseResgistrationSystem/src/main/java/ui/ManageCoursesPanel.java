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
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        tableModel = new DefaultTableModel(new Object[]{"id", "course_code", "course_name", "description", "credits"}, 0);
        coursesTable = new JTable(tableModel);
        coursesTable.setFillsViewportHeight(true);
        coursesTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        coursesTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(coursesTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton addButton = createButton("Add Course");
        JButton editButton = createButton("Edit Course");
        JButton deleteButton = createButton("Delete Course");
        JButton refreshButton = createButton("Refresh");
        
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(refreshButton);
        add(buttonsPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addCourse());
        editButton.addActionListener(e -> editCourse());
        deleteButton.addActionListener(e -> deleteCourse());
        refreshButton.addActionListener(e -> loadCourses());

        loadCourses();
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(0, 120, 215));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    private void loadCourses() {
        List<Course> courses = courseService.getAllCourses();
        tableModel.setRowCount(0);
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
        JTextField courseIDField = new JTextField();
        JTextField courseCodeField = new JTextField();
        JTextField courseNameField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField creditField = new JTextField();
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
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
        if (result == JOptionPane.OK_OPTION) {
            try {
                int courseID = Integer.parseInt(courseIDField.getText());
                String courseCode = courseCodeField.getText();
                String courseName = courseNameField.getText();
                String description = descriptionField.getText();
                int credit = Integer.parseInt(creditField.getText());
                Course newCourse = new Course(courseID, courseCode, courseName, description, credit);
                boolean success = courseService.addCourse(newCourse);
                JOptionPane.showMessageDialog(this, success ? "Course added successfully!" : "Failed to add course.", 
                        success ? "Success" : "Error", success ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
                if (success) loadCourses();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editCourse() {
        int selectedRow = coursesTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a course to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int courseId = (int) tableModel.getValueAt(selectedRow, 0);
        String currentCourseCode = (String) tableModel.getValueAt(selectedRow, 1);
        String currentCourseName = (String) tableModel.getValueAt(selectedRow, 2);
        String currentDescription = (String) tableModel.getValueAt(selectedRow, 3);
        int currentCredits = (int) tableModel.getValueAt(selectedRow, 4);
        
        JTextField courseCodeField = new JTextField(currentCourseCode);
        JTextField courseNameField = new JTextField(currentCourseName);
        JTextField descriptionField = new JTextField(currentDescription);
        JTextField creditsField = new JTextField(String.valueOf(currentCredits));
        
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.add(new JLabel("Course Code:"));
        formPanel.add(courseCodeField);
        formPanel.add(new JLabel("Course Name:"));
        formPanel.add(courseNameField);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(descriptionField);
        formPanel.add(new JLabel("Credits:"));
        formPanel.add(creditsField);
        
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Edit Course", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String courseCode = courseCodeField.getText();
                String courseName = courseNameField.getText();
                String description = descriptionField.getText();
                int credits = Integer.parseInt(creditsField.getText());
                Course updatedCourse = new Course(courseId, courseCode, courseName, description, credits);
                boolean success = courseService.updateCourse(updatedCourse);
                JOptionPane.showMessageDialog(this, success ? "Course updated successfully!" : "Failed to update course.", 
                        success ? "Success" : "Error", success ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
                if (success) loadCourses();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteCourse() {
        int selectedRow = coursesTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a course to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int courseId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected course?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = courseService.deleteCourse(courseId);
            JOptionPane.showMessageDialog(this, success ? "Course deleted successfully!" : "Failed to delete course.", 
                    success ? "Success" : "Error", success ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
            if (success) loadCourses();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Manage Courses UI Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new ManageCoursesPanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
