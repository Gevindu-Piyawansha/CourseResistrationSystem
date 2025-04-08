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
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        JFrame frame = new JFrame("Faculty Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        
        JLabel headerLabel = new JLabel("Welcome, Faculty!");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(headerLabel, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        
        JButton viewCoursesButton = new JButton("View Assigned Courses");
        viewCoursesButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        viewCoursesButton.setBackground(new Color(0, 120, 215));
        viewCoursesButton.setForeground(Color.WHITE);
        viewCoursesButton.setFocusPainted(false);
        
        JButton viewStudentsButton = new JButton("View Student Progress");
        viewStudentsButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        viewStudentsButton.setBackground(new Color(0, 120, 215));
        viewStudentsButton.setForeground(Color.WHITE);
        viewStudentsButton.setFocusPainted(false);
        
        buttonPanel.add(viewCoursesButton);
        buttonPanel.add(viewStudentsButton);
        
        gbc.gridy = 1;
        mainPanel.add(buttonPanel, gbc);
        
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(FacultyDashboardUI::showDashboard);
    }
}
