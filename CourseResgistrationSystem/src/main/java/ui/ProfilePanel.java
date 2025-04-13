/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import entity.Student;
import javax.swing.*;
import java.awt.*;

import entity.User;



/**
 *
 * @author Admin
 */

public class ProfilePanel extends JPanel {
    private Student student;
    
    public ProfilePanel(Student student) {
        this.student = student;
        setLayout(new GridLayout(0, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("Profile Information", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title);
        
        add(new JLabel("Name: " + student.getFirstName() + " " + student.getLastName()));
        add(new JLabel("Email: " + student.getEmail()));
        add(new JLabel("Program: " + student.getProgram()));
        add(new JLabel("Enrollment Year: " + student.getEnrollmentYear()));
        // Add any other profile information as needed.
    }
}
