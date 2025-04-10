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

public class ViewSchedulePanel extends JPanel {
    public ViewSchedulePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        JLabel label = new JLabel("Your schedule will be displayed here.", SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(label, BorderLayout.CENTER);
    }
}
