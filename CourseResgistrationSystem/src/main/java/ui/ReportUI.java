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
        JFrame frame = new JFrame("Reports Dashboard");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1));

        JButton enrollmentReportBtn = new JButton("Course Enrollment Report");
        JButton vacantSeatsReportBtn = new JButton("Vacant Seats Report");
        JButton studentProgressReportBtn = new JButton("Student Progress Report");

        enrollmentReportBtn.addActionListener(e -> reportDAO.generateCourseEnrollmentReport());
        vacantSeatsReportBtn.addActionListener(e -> reportDAO.generateVacantSeatsReport());
        
        // Get student ID input before generating report
        studentProgressReportBtn.addActionListener(e -> {
            String studentId = JOptionPane.showInputDialog(frame, "Enter Student ID:");
            if (studentId != null && !studentId.trim().isEmpty()) {
                reportDAO.generateStudentProgressReport(Integer.parseInt(studentId));
            }
        });

        frame.add(enrollmentReportBtn);
        frame.add(vacantSeatsReportBtn);
        frame.add(studentProgressReportBtn);

        frame.setVisible(true);
    }
}
