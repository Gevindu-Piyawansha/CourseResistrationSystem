/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.StudentDAO;
import entity.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Admin
 */



public class ManageStudentsPanel extends JPanel {
    private StudentDAO studentDAO = new StudentDAO();
    private JTable table;
    private DefaultTableModel tableModel;

    public ManageStudentsPanel() {
        setLayout(new BorderLayout());
        
        // Define table columns
        String[] columnNames = {"ID", "First Name", "Last Name", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load data into table
        refreshTable();

        // Button to add a new student
        JButton addStudentBtn = new JButton("Add Student");
        add(addStudentBtn, BorderLayout.SOUTH);
        addStudentBtn.addActionListener(e -> {
            String firstName = JOptionPane.showInputDialog("Enter first name:");
            if (firstName == null || firstName.trim().isEmpty()) return;
            
            String lastName = JOptionPane.showInputDialog("Enter last name:");
            if (lastName == null || lastName.trim().isEmpty()) return;
            
            String email = JOptionPane.showInputDialog("Enter email:");
            if (email == null || email.trim().isEmpty()) return;
            
            // Create a new student (assuming user_id is not required or set to 0 for sample data)
            Student newStudent = new Student(0, 0, firstName, lastName, email);
            
            // Call addStudent method (which returns void)
            studentDAO.addStudent(newStudent);
            JOptionPane.showMessageDialog(this, "Student added successfully!");
            refreshTable();
        });
    }
    
    // Method to refresh the table data
    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Student> students = studentDAO.getAllStudents();
        for (Student s : students) {
            Object[] row = { s.getId(), s.getFirstName(), s.getLastName(), s.getEmail() };
            tableModel.addRow(row);
        }
    }
}
