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




public class FacultyDashboardUI {
    public static void showDashboard() {
        JFrame frame = new JFrame("Faculty Dashboard");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Welcome, Faculty!");
        frame.add(label);

        frame.setVisible(true);
    }
}
