/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import entity.User;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author Admin
 */

public class AdminDashboardUI {

    // Method that accepts a User object to customize the dashboard (for example, by greeting the admin)
    public static void showDashboard(User user) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        JFrame frame = new JFrame("Admin Dashboard - " + user.getUsername());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        
        // Set the custom background image in a BackgroundPanel
        BackgroundPanel backgroundPanel = new BackgroundPanel(loadImage("/images/adminui.jpg"));
        backgroundPanel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundPanel);
        
        // Create a transparent side menu panel with the admin options
        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Create buttons with icons for each admin option
        JButton manageCoursesBtn = createButton("Manage Courses", "path/to/course_icon.png");
        JButton manageStudentsBtn = createButton("Manage Students", "path/to/student_icon.png");
        JButton generateReportsBtn = createButton("Generate Reports", "path/to/report_icon.png");
        
        menuPanel.add(manageCoursesBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        menuPanel.add(manageStudentsBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        menuPanel.add(generateReportsBtn);
        
        backgroundPanel.add(menuPanel, BorderLayout.WEST);
        
        // Create a header label with an optional icon and customized welcome message
        JLabel header = new JLabel("Admin Dashboard - Welcome " + user.getUsername(), SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(Color.WHITE);
        header.setIcon(new ImageIcon("path/to/dashboard_icon.png"));
        header.setHorizontalTextPosition(SwingConstants.RIGHT);
        header.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        backgroundPanel.add(header, BorderLayout.NORTH);
        
        // Add action listeners to each button to open dialogs with the corresponding panels.
        manageCoursesBtn.addActionListener(e -> {
            ManageCoursesPanel coursesPanel = new ManageCoursesPanel();
            JDialog dialog = createDialog(frame, "Manage Courses", coursesPanel);
            dialog.setVisible(true);
        });
        
        manageStudentsBtn.addActionListener(e -> {
            ManageStudentsPanel studentsPanel = new ManageStudentsPanel();
            JDialog dialog = createDialog(frame, "Manage Students", studentsPanel);
            dialog.setVisible(true);
        });
        
        generateReportsBtn.addActionListener(e -> {
            GenerateReportsPanel reportsPanel = new GenerateReportsPanel();
            JDialog dialog = createDialog(frame, "Generate Reports", reportsPanel);
            dialog.setVisible(true);
        });
        
        frame.setVisible(true);
    }
    
    // Overloaded method if you want to show the dashboard without passing a User object.
    // This creates a default admin user.
    public static void showDashboard() {
        User defaultAdmin = new User(0, "admin", "admin", "admin", "admin@example.com");
        showDashboard(defaultAdmin);
    }
    
    // Utility method to load an image from the resources folder
    private static Image loadImage(String path) {
        java.net.URL imgURL = AdminDashboardUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            // If image is not found, return a tiny blank image
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
    }
    
    // Utility method to create buttons with text and an icon
    private static JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        try {
            ImageIcon icon = new ImageIcon(iconPath);
            Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.err.println("Icon not found: " + iconPath);
        }
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button.setBackground(new Color(255, 255, 255, 200));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        return button;
    }
    
    // Utility method to create a modal dialog for various panels
    private static JDialog createDialog(JFrame owner, String title, JPanel content) {
        JDialog dialog = new JDialog(owner, title, true);
        dialog.getContentPane().add(content);
        dialog.pack();
        dialog.setLocationRelativeTo(owner);
        return dialog;
    }
    
    // Custom JPanel that paints a background image across its area
    static class BackgroundPanel extends JPanel {
        private final Image image;
        public BackgroundPanel(Image image) {
            this.image = image;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    public static void main(String[] args) {
       showDashboard();
    }
}

    

