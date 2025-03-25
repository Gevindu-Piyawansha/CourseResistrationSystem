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

        JLabel label = new JLabel("Welcome, Student!");
        frame.add(label);

        frame.setVisible(true);
    }
}
