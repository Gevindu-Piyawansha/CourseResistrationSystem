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
/**
 *
 * @author Admin
 */


public class StudentDashboardUI extends JFrame {
    private Student student;
    private JTabbedPane tabbedPane;

    // Constructor accepting a Student object.
    public StudentDashboardUI(Student student) {
        this.student = student; // assign the provided Student
        setTitle("Student Dashboard - " + student.getFirstName() + " " + student.getLastName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        // Assuming these panels have been updated to accept a Student object
        CourseRegistrationPanel registrationPanel = new CourseRegistrationPanel(student);
        tabbedPane.addTab("Course Registration", registrationPanel);

        ViewSchedulePanel schedulePanel = new ViewSchedulePanel(student);
        tabbedPane.addTab("View Schedule", schedulePanel);

        ProfilePanel profilePanel = new ProfilePanel(student);
        tabbedPane.addTab("Profile", profilePanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    // Static method to show the dashboard with the given Student object.
    public static void showDashboard(Student student) {
        SwingUtilities.invokeLater(() -> {
            new StudentDashboardUI(student).setVisible(true);
        });
    }

    // Main method for testing the student dashboard.
    public static void main(String[] args) {
        // Retrieve a student user from the users table using UserDAO.
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername("student1"); // Adjust username as needed.
        
        if (user != null && user.getRole().equalsIgnoreCase("student")) {
            // Use StudentDAO to get the corresponding Student record.
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.getStudentById(user.getId());
            if (student != null) {
                showDashboard(student);
            } else {
                System.err.println("No student details found for user " + user.getUsername());
            }
        } else {
            System.err.println("Student user not found or user is not a student.");
        }
    }
}
