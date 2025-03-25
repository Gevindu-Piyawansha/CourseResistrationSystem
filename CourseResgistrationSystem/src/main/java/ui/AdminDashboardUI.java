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
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome, Admin!"));

        JButton manageCoursesButton = new JButton("Manage Courses");
        JButton manageStudentsButton = new JButton("Manage Students");
        JButton generateReportsButton = new JButton("Generate Reports");

        panel.add(manageCoursesButton);
        panel.add(manageStudentsButton);
        panel.add(generateReportsButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
