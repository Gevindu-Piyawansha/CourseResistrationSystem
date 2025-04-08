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


public class FacultyCoursesPanel extends JPanel {
    public FacultyCoursesPanel() {
        setLayout(new BorderLayout());
        // Sample data – replace with DAO call for faculty-assigned courses.
        String[] columns = {"Course Code", "Course Name", "Schedule"};
        Object[][] data = {
            {"CS101", "Introduction to Computer Science", "Mon/Wed/Fri 10:00-11:00"},
            {"CS202", "Data Structures", "Tue/Thu 13:00-14:30"}
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
