/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.StudentDAO;
import entity.Student;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Admin
 */


public class StudentManagementUI {

    public static void showUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        JFrame frame = new JFrame("Student Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Header
        JLabel header = new JLabel("Add Student");
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(header, gbc);
        
        // Reset grid width for form rows
        gbc.gridwidth = 1;
        
        // Name Field
        gbc.gridy = 1;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField(20);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(nameField, gbc);
        
        // DOB Field
        gbc.gridy = 2;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("DOB (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        JTextField dobField = new JTextField(10);
        dobField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(dobField, gbc);
        
        // Program Field
        gbc.gridy = 3;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Program:"), gbc);
        gbc.gridx = 1;
        JTextField programField = new JTextField(20);
        programField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(programField, gbc);
        
        // Year Field
        gbc.gridy = 4;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Year:"), gbc);
        gbc.gridx = 1;
        JTextField yearField = new JTextField(5);
        yearField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainPanel.add(yearField, gbc);
        
        // Add Button
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton addButton = new JButton("Add Student");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        addButton.setBackground(new Color(0, 120, 215));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        mainPanel.add(addButton, gbc);
        
        addButton.addActionListener(e -> {
            try {
                int year = Integer.parseInt(yearField.getText());
                Student student = new Student(0, year, nameField.getText(), dobField.getText(), programField.getText());
                new StudentDAO().addStudent(student);
                JOptionPane.showMessageDialog(frame, "Student Added!");
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Year", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementUI::showUI);
    }
}
