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


/**
 * Modern and professional Student Dashboard UI
 */
public class StudentDashboardUI extends JFrame {
    private Student student;
    private JTabbedPane tabbedPane;

    // Define a modern font and color scheme
    private static final Font DEFAULT_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color TAB_COLOR = new Color(230, 230, 250);

    public StudentDashboardUI(Student student) {
        this.student = student;
        setTitle("🎓 Student Dashboard - " + student.getFirstName() + " " + student.getLastName());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

        initializeComponents();
    }

    private void initializeComponents() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(DEFAULT_FONT);
        tabbedPane.setBackground(TAB_COLOR);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create and add tabs
        tabbedPane.addTab("📚 Course Registration", new CourseRegistrationPanel(student));
        tabbedPane.addTab("🗓 View Schedule", new ViewSchedulePanel(student));
        tabbedPane.addTab("👤 Profile", new ProfilePanel(student));

        // Add to the frame
        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void showDashboard(Student student) {
        SwingUtilities.invokeLater(() -> {
            StudentDashboardUI dashboard = new StudentDashboardUI(student);
            dashboard.setVisible(true);
        });
    }

    public static void main(String[] args) {
        // Simulate login
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername("student1");

        if (user != null && user.getRole().equalsIgnoreCase("student")) {
            StudentDAO studentDAO = new StudentDAO();
            Student student = studentDAO.getStudentById(user.getId());

            if (student != null) {
                showDashboard(student);
            } else {
                JOptionPane.showMessageDialog(null, "Student details not found for: " + user.getUsername(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Student user not found or not valid.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
