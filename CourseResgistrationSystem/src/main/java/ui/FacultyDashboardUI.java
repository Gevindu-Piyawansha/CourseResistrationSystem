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
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Welcome, Faculty!");
        JButton reportButton = new JButton("Generate Reports");

        reportButton.addActionListener(e -> ReportUI.showReportDashboard());

        frame.add(label);
        frame.add(reportButton);

        frame.setVisible(true);
    }
}
