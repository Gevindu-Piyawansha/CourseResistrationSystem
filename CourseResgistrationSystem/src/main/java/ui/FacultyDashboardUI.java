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





public class FacultyDashboardUI {
   public static void showDashboard() {
        JFrame frame = new JFrame("Faculty Dashboard");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Welcome, Faculty!"));

        JButton viewCoursesButton = new JButton("View Assigned Courses");
        JButton viewStudentsButton = new JButton("View Student Progress");

        panel.add(viewCoursesButton);
        panel.add(viewStudentsButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
