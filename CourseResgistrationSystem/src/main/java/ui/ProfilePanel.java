/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;

import entity.User;



/**
 *
 * @author Admin
 */


public class ProfilePanel extends JPanel {
    private User user;
    
    public ProfilePanel(User user) {
        this.user = user;
        setLayout(new GridLayout(0, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("Profile Information", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title);
        
        add(new JLabel("Username: " + user.getUsername()));
        
        add(new JLabel("Email: " + user.getEmail()));
        // Add any other profile information you require.
    }
}
