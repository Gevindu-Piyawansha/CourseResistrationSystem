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


public class GenerateReportsPanel extends JPanel {
    private final ReportDAO reportDAO = new ReportDAO();

    // Proper constructor (not a void method).
    public GenerateReportsPanel() {
        // This sets the panel's preferred size, so the dialog isn't huge by default.
        setPreferredSize(new Dimension(600, 450));
        setLayout(new BorderLayout());

        // Get your enrollment report string from the DAO.
        String reportContent = reportDAO.getCourseEnrollmentReportString();

        // Create a text area to display the report.
        JTextArea textArea = new JTextArea(reportContent, 16, 50);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Wrap the text area in a scroll pane, then add it to the panel.
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }
       // Main method to run this panel independently in a frame
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Generate Reports");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Add this panel to the frame's content pane
            frame.getContentPane().add(new GenerateReportsPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
