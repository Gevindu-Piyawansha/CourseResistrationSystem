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

    public void GenerateReportsPanel() {
        setLayout(new BorderLayout());
        
        // Get the enrollment report string from the DAO.
        String reportContent = reportDAO.getCourseEnrollmentReportString();
        
        // Create a text area to display the report.
        JTextArea textArea = new JTextArea(reportContent, 50, 80);
        textArea.setEditable(false);
        // Set a monospaced font for proper alignment.
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Generate Reports UI Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new GenerateReportsPanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
}
}