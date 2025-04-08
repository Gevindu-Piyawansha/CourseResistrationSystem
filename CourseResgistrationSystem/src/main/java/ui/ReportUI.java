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
    private static ReportDAO reportDAO = new ReportDAO();

    public static void showReportDashboard() {
        // Create the dashboard frame.
        JFrame frame = new JFrame("Reports Dashboard");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Use 3 rows, since we have 3 buttons.
        frame.setLayout(new GridLayout(3, 1));

        JButton enrollmentReportBtn = new JButton("Course Enrollment Report");
        JButton vacantSeatsReportBtn = new JButton("Vacant Seats Report");
        JButton studentProgressReportBtn = new JButton("Student Progress Report");

        // When clicked, each button gathers its report and displays it.
        enrollmentReportBtn.addActionListener(e -> {
            String report = reportDAO.getCourseEnrollmentReportString();
            JTextArea textArea = new JTextArea(report);
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(frame, new JScrollPane(textArea), "Course Enrollment Report", JOptionPane.INFORMATION_MESSAGE);
        });

        vacantSeatsReportBtn.addActionListener(e -> {
            String report = reportDAO.getVacantSeatsReportString();
            JTextArea textArea = new JTextArea(report);
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(frame, new JScrollPane(textArea), "Vacant Seats Report", JOptionPane.INFORMATION_MESSAGE);
        });

        studentProgressReportBtn.addActionListener(e -> {
            String studentIdStr = JOptionPane.showInputDialog(frame, "Enter Student ID:");
            if (studentIdStr != null && !studentIdStr.trim().isEmpty()) {
                try {
                    int studentId = Integer.parseInt(studentIdStr);
                    String report = reportDAO.getStudentProgressReportString(studentId);
                    JTextArea textArea = new JTextArea(report);
                    textArea.setEditable(false);
                    JOptionPane.showMessageDialog(frame, new JScrollPane(textArea), "Student Progress Report", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid Student ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add buttons to the frame.
        frame.add(enrollmentReportBtn);
        frame.add(vacantSeatsReportBtn);
        frame.add(studentProgressReportBtn);

        frame.setVisible(true);
    }
}
