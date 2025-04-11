/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.StudentDAO;
import entity.Student;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
/**
 *
 * @author Admin
 */



public class StudentDashboardUI {

    private int studentId;
    private Student loggedInStudent;  // The complete student profile

    // Constructor accepts the student's id.
    public StudentDashboardUI(int studentId) {
        this.studentId = studentId;
        // Retrieve the student from the database.
        loggedInStudent = new StudentDAO().getStudentById(studentId);
    }
    
    public void showDashboard() {
        // Set Nimbus look and feel.
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        JFrame frame = new JFrame("Student Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        
        // Create a background panel with an image.
        BackgroundPanel backgroundPanel = new BackgroundPanel(loadImage("/images/studentui.jpg"));
        backgroundPanel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundPanel);
        
        // Header panel at the top.
        JLabel header = new JLabel("Student Dashboard", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.setForeground(Color.WHITE);
        header.setBorder(new EmptyBorder(20, 20, 20, 20));
        backgroundPanel.add(header, BorderLayout.NORTH);
        
        // Create a personalized welcome message using the student's first name.
        String welcomeText = "Welcome, " + (loggedInStudent != null ? loggedInStudent.getFirstName() : "Student") + "!";
        JLabel welcomeLabel = new JLabel(welcomeText, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        backgroundPanel.add(welcomeLabel, BorderLayout.CENTER);
        
        // Sidebar menu panel.
        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JButton courseRegButton = createButton("Course Registration", "path/to/course_icon.png");
        JButton viewScheduleButton = createButton("View Schedule", "path/to/schedule_icon.png");
        JButton profileButton = createButton("Profile", "path/to/profile_icon.png");
        
        menuPanel.add(courseRegButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        menuPanel.add(viewScheduleButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        menuPanel.add(profileButton);
        backgroundPanel.add(menuPanel, BorderLayout.WEST);
        
        // Button actions.
        courseRegButton.addActionListener(e -> {
            // Open the Course Registration Panel.
            CourseRegistrationPanel regPanel = new CourseRegistrationPanel();
            JDialog dialog = createDialog(frame, "Course Registration", regPanel);
            dialog.setVisible(true);
        });
        
        viewScheduleButton.addActionListener(e -> {
            // Open the View Schedule Panel, passing the logged-in studentId.
            ViewSchedulePanel schedulePanel = new ViewSchedulePanel(studentId);
            JDialog dialog = createDialog(frame, "View Schedule", schedulePanel);
            dialog.setVisible(true);
        });
        
        profileButton.addActionListener(e -> {
            // Open the Profile Panel for the logged-in student.
            ProfilePanel profilePanel = new ProfilePanel(studentId);
            JDialog dialog = createDialog(frame, "Profile", profilePanel);
            dialog.setVisible(true);
        });
        
        frame.setVisible(true);
    }
    
    // Helper method to load an image from the classpath.
    private static Image loadImage(String path) {
        java.net.URL imgURL = StudentDashboardUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
    }
    
    // Helper method to create a styled button with an icon.
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
    
    // Helper method to create a modal dialog with the provided content panel.
    private static JDialog createDialog(JFrame owner, String title, JPanel content) {
        JDialog dialog = new JDialog(owner, title, true);
        dialog.getContentPane().add(content);
        dialog.pack();
        dialog.setLocationRelativeTo(owner);
        return dialog;
    }
    
    // Custom BackgroundPanel class that scales a background image.
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
    SwingUtilities.invokeLater(() -> {
        int sampleStudentId = 2; // Replace with an actual student ID that exists in your database
        StudentDashboardUI dashboard = new StudentDashboardUI(sampleStudentId);
        dashboard.showDashboard();
    });
}

}
