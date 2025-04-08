/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import dao.ReportDAO;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Admin
 */


public class ReportUI {
    private static final ReportDAO reportDAO = new ReportDAO();

    public static void showReportDashboard() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        JFrame frame = new JFrame("Reports Dashboard");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        
        JButton enrollmentReportBtn = createButton("Course Enrollment Report");
        JButton vacantSeatsReportBtn = createButton("Vacant Seats Report");
        JButton studentProgressReportBtn = createButton("Student Progress Report");
        
        gbc.gridy = 0;
        mainPanel.add(enrollmentReportBtn, gbc);
        gbc.gridy = 1;
        mainPanel.add(vacantSeatsReportBtn, gbc);
        gbc.gridy = 2;
        mainPanel.add(studentProgressReportBtn, gbc);
        
        enrollmentReportBtn.addActionListener(e -> {
            String report = reportDAO.getCourseEnrollmentReportString();
            showReportDialog(frame, "Course Enrollment Report", report);
        });
        
        vacantSeatsReportBtn.addActionListener(e -> {
            String report = reportDAO.getVacantSeatsReportString();
            showReportDialog(frame, "Vacant Seats Report", report);
        });
        
        studentProgressReportBtn.addActionListener(e -> {
            String studentIdStr = JOptionPane.showInputDialog(frame, "Enter Student ID:");
            if (studentIdStr != null && !studentIdStr.trim().isEmpty()) {
                try {
                    int studentId = Integer.parseInt(studentIdStr);
                    String report = reportDAO.getStudentProgressReportString(studentId);
                    showReportDialog(frame, "Student Progress Report", report);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid Student ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    private static JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(new Color(0, 120, 215));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }
    
    private static void showReportDialog(JFrame parent, String title, String report) {
        JTextArea textArea = new JTextArea(report);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        JOptionPane.showMessageDialog(parent, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> showReportDashboard());
    }
}
