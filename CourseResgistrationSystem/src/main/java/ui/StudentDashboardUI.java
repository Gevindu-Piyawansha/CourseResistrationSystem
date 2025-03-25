/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import javax.swing.*;

/**
 *
 * @author Admin
 */



public class StudentDashboardUI {
    public static void showDashboard() {
        JFrame frame = new JFrame("Student Dashboard");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome, Student!"));

        JButton registerCoursesButton = new JButton("Register for Courses");
        JButton viewScheduleButton = new JButton("View Schedule");

        panel.add(registerCoursesButton);
        panel.add(viewScheduleButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
