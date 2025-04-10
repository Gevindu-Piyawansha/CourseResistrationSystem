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

public class ProfilePanel extends JPanel {
    public ProfilePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        String profileInfo = "<html><body style='padding:20px; font-family:Segoe UI; font-size:14px;'>" +
                             "<h1>Student Profile</h1>" +
                             "<p><strong>Name:</strong> John Doe</p>" +
                             "<p><strong>DOB:</strong> 2000-01-01</p>" +
                             "<p><strong>Program:</strong> Computer Science</p>" +
                             "<p><strong>Year:</strong> 2022</p>" +
                             "<p><strong>Contact:</strong> johndoe@example.com</p>" +
                             "</body></html>";
        JLabel profileLabel = new JLabel(profileInfo);
        add(profileLabel, BorderLayout.CENTER);
    }
}
