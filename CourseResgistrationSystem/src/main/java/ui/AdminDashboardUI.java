/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Admin
 */


public class AdminDashboardUI {
    public static void showDashboard() {
        JFrame frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Menu panel with buttons for each function
        JPanel menuPanel = new JPanel();
        JButton manageCoursesBtn = new JButton("Manage Courses");
        JButton manageStudentsBtn = new JButton("Manage Students");
        JButton generateReportsBtn = new JButton("Generate Reports");

        menuPanel.add(manageCoursesBtn);
        menuPanel.add(manageStudentsBtn);
        menuPanel.add(generateReportsBtn);
        frame.add(menuPanel, BorderLayout.NORTH);

        // Button actions open a dialog with the respective panel
        manageCoursesBtn.addActionListener(e -> {
            ManageCoursesPanel coursesPanel = new ManageCoursesPanel();
            JOptionPane.showMessageDialog(frame, coursesPanel, "Manage Courses", JOptionPane.PLAIN_MESSAGE);
        });

        manageStudentsBtn.addActionListener(e -> {
            ManageStudentsPanel studentsPanel = new ManageStudentsPanel();
            JOptionPane.showMessageDialog(frame, studentsPanel, "Manage Students", JOptionPane.PLAIN_MESSAGE);
        });

        generateReportsBtn.addActionListener(e -> {
            GenerateReportsPanel reportsPanel = new GenerateReportsPanel();
            JOptionPane.showMessageDialog(frame, reportsPanel, "Generate Reports", JOptionPane.PLAIN_MESSAGE);
        });

        frame.setVisible(true);
    }
}

