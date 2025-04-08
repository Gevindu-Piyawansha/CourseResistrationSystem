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


public class FacultyStudentProgressPanel extends JPanel {
    public FacultyStudentProgressPanel() {
        setLayout(new BorderLayout());
        // Sample data – replace with DAO call to fetch student progress data.
        String[] columns = {"Student ID", "Name", "Course", "Progress"};
        Object[][] data = {
            {"S001", "Alice", "CS101", "85%"},
            {"S002", "Bob", "CS101", "90%"}
        };
        JTable table = new JTable(data, columns);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Set a preferred size for the dialog.
        setPreferredSize(new Dimension(500, 300));
    }
}
