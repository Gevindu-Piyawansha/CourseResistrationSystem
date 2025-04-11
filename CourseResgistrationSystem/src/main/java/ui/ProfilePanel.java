/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import service.StudentService;
import entity.Student;
import javax.swing.*;
import java.awt.*;


/**
 *
 * @author Admin
 */

public class ProfilePanel extends JPanel {
    private StudentService studentService = new StudentService();

    public ProfilePanel(int studentId) {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Retrieve the student profile using the service.
        Student student = studentService.getStudentProfile(studentId);
        
        if (student == null) {
            add(new JLabel("Student not found."), BorderLayout.CENTER);
            return;
        }
        
        // Create labels with student details.
        JPanel infoPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        infoPanel.setBackground(Color.WHITE);
        
        JLabel idLabel = new JLabel("Student ID: " + student.getId());
        JLabel nameLabel = new JLabel("Name: " + student.getFirstName() + " " + student.getLastName());
        JLabel dobLabel = new JLabel("Date of Birth: " + student.getDob());
        JLabel programLabel = new JLabel("Program: " + student.getProgram());
        JLabel emailLabel = new JLabel("Email: " + student.getEmail());
        JLabel yearLabel = new JLabel("Enrollment Year: " + student.getEnrollmentYear());
        
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);
        idLabel.setFont(labelFont);
        nameLabel.setFont(labelFont);
        dobLabel.setFont(labelFont);
        programLabel.setFont(labelFont);
        emailLabel.setFont(labelFont);
        yearLabel.setFont(labelFont);
        
        infoPanel.add(idLabel);
        infoPanel.add(nameLabel);
        infoPanel.add(dobLabel);
        infoPanel.add(programLabel);
        infoPanel.add(emailLabel);
        infoPanel.add(yearLabel);
        
        // Optionally, add a header
        JLabel header = new JLabel("Student Profile", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        add(header, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        
    }
}
