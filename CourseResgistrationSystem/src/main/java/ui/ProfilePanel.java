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

/**
 * Stylish Profile Panel UI for displaying student information
 */
public class ProfilePanel extends JPanel {
    private Student student;

    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Color BACKGROUND_COLOR = new Color(250, 250, 250);
    private static final Color PANEL_COLOR = new Color(255, 255, 255);
    private static final Color BORDER_COLOR = new Color(220, 220, 220);

    public ProfilePanel(Student student) {
        this.student = student;
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(PANEL_COLOR);
        contentPanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
        contentPanel.setPreferredSize(new Dimension(600, 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel title = new JLabel("👤 Profile Information");
        title.setFont(HEADER_FONT);
        gbc.gridwidth = 2;
        contentPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        contentPanel.add(createLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(createValue(student.getFirstName() + " " + student.getLastName()), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(createLabel("Email:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(createValue(student.getEmail()), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(createLabel("Program:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(createValue(student.getProgram()), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(createLabel("Enrollment Year:"), gbc);
        gbc.gridx = 1;
        contentPanel.add(createValue(String.valueOf(student.getEnrollmentYear())), gbc);

        // Add more fields here if needed...

        add(contentPanel, BorderLayout.CENTER);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL_FONT);
        label.setForeground(Color.DARK_GRAY);
        return label;
    }

    private JLabel createValue(String text) {
        JLabel value = new JLabel(text);
        value.setFont(LABEL_FONT);
        value.setForeground(new Color(60, 63, 65));
        return value;
    }
}
