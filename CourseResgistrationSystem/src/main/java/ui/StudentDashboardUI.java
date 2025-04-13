/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;

import entity.User;
/**
 *
 * @author Admin
 */


public class StudentDashboardUI extends JFrame {
    private User user;
    private JTabbedPane tabbedPane;

    public StudentDashboardUI(User user) {
        this.user = user;
        setTitle("Student Dashboard - " + user.getUsername());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        // Course Registration Panel
        CourseRegistrationPanel registrationPanel = new CourseRegistrationPanel(user);
        tabbedPane.addTab("Course Registration", registrationPanel);

        // View Schedule Panel
        ViewSchedulePanel schedulePanel = new ViewSchedulePanel(user);
        tabbedPane.addTab("View Schedule", schedulePanel);

        // Profile Panel
        ProfilePanel profilePanel = new ProfilePanel(user);
        tabbedPane.addTab("Profile", profilePanel);

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void showDashboard(User user) {
        SwingUtilities.invokeLater(() -> {
            new StudentDashboardUI(user).setVisible(true);
        });
    }
  
   
}
