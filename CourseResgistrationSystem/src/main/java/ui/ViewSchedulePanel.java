/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import service.CourseScheduleService;
import entity.CourseSchedule;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Admin
 */


public class ViewSchedulePanel extends JPanel {
    private CourseScheduleService scheduleService = new CourseScheduleService();
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewSchedulePanel(int studentId) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"Course Code", "Course Name", "Day", "Start Time", "End Time", "Location"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        refreshTable(studentId);
    }

    private void refreshTable(int studentId) {
        tableModel.setRowCount(0);
        List<CourseSchedule> scheduleList = scheduleService.getSchedulesForStudent(studentId);
        for (CourseSchedule cs : scheduleList) {
            Object[] row = {
                cs.getCourseCode(),
                cs.getCourseName(),
                cs.getDayOfWeek(),
                cs.getStartTime(),
                cs.getEndTime(),
                cs.getLocation()
            };
            tableModel.addRow(row);
        }
    }
}
