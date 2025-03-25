/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import dao.ReportDAO;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
/**
 *
 * @author Admin
 */




public class GenerateReportsPanel extends JPanel {
    private ReportDAO reportDAO = new ReportDAO();

    public GenerateReportsPanel() {
        setLayout(new BorderLayout());
        
        // Get report data: key = course name, value = number of enrollments
        Map<String, Integer> reportData = reportDAO.getEnrollmentsReport();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : reportData.entrySet()) {
            sb.append("Course: ").append(entry.getKey())
              .append(" - Enrollments: ").append(entry.getValue()).append("\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
}
