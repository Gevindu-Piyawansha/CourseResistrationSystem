/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.EnrollmentDAO;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Admin
 */



public class EnrollmentUI {

    public static void showUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        JFrame frame = new JFrame("Enrollment Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 250);
        frame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField studentIdField = new JTextField(5);
        studentIdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel courseIdLabel = new JLabel("Course ID:");
        courseIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JTextField courseIdField = new JTextField(5);
        courseIdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JButton enrollButton = new JButton("Enroll");
        enrollButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        enrollButton.setBackground(new Color(0, 120, 215));
        enrollButton.setForeground(Color.WHITE);
        enrollButton.setFocusPainted(false);
        
        JButton dropButton = new JButton("Drop");
        dropButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        dropButton.setBackground(new Color(220, 53, 69));
        dropButton.setForeground(Color.WHITE);
        dropButton.setFocusPainted(false);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(studentIdLabel, gbc);
        
        gbc.gridx = 1;
        mainPanel.add(studentIdField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(courseIdLabel, gbc);
        
        gbc.gridx = 1;
        mainPanel.add(courseIdField, gbc);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(enrollButton);
        buttonPanel.add(dropButton);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);
        
        enrollButton.addActionListener(e -> {
            try {
                int studentId = Integer.parseInt(studentIdField.getText());
                int courseId = Integer.parseInt(courseIdField.getText());
                new EnrollmentDAO().enrollStudent(studentId, courseId);
                JOptionPane.showMessageDialog(frame, "Student Enrolled!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numeric IDs.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        dropButton.addActionListener(e -> {
            try {
                int studentId = Integer.parseInt(studentIdField.getText());
                int courseId = Integer.parseInt(courseIdField.getText());
                new EnrollmentDAO().dropCourse(studentId, courseId);
                JOptionPane.showMessageDialog(frame, "Course Dropped!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numeric IDs.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(EnrollmentUI::showUI);
    }
}
