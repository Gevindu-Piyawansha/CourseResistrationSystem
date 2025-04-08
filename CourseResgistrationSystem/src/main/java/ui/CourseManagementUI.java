/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.CourseDAO;
import entity.Course;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Admin
 */


public class CourseManagementUI {

    public static void showUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        JFrame frame = new JFrame("Course Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);  // Set a neutral background color
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField titleField = new JTextField(20);
        titleField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel creditHoursLabel = new JLabel("Credit Hours:");
        creditHoursLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField creditHoursField = new JTextField(5);
        creditHoursField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField departmentField = new JTextField(20);
        departmentField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton addButton = new JButton("Add Course");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addButton.setBackground(new Color(0, 120, 215));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(titleLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(creditHoursLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(creditHoursField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(departmentLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(departmentField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(addButton, gbc);

        addButton.addActionListener(e -> {
            try {
                int creditHours = Integer.parseInt(creditHoursField.getText());
                Course course = new Course(0, titleField.getText(), departmentField.getText(), "", creditHours);
                new CourseDAO().addCourse(course);
                JOptionPane.showMessageDialog(frame, "Course Added!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for Credit Hours", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CourseManagementUI::showUI);
    }
}
