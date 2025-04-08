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
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setPreferredSize(new Dimension(600, 400));
        
        String[] columnNames = {"id", "first_name", "last_name", "email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton addStudentBtn = createButton("Add Student");
        JButton editStudentBtn = createButton("Edit Student");
        JButton deleteStudentBtn = createButton("Delete Student");
        JButton refreshBtn = createButton("Refresh");
        
        buttonsPanel.add(addStudentBtn);
        buttonsPanel.add(editStudentBtn);
        buttonsPanel.add(deleteStudentBtn);
        buttonsPanel.add(refreshBtn);
        add(buttonsPanel, BorderLayout.SOUTH);
        
        addStudentBtn.addActionListener(e -> addStudent());
        editStudentBtn.addActionListener(e -> editStudent());
        deleteStudentBtn.addActionListener(e -> deleteStudent());
        refreshBtn.addActionListener(e -> refreshTable());
        
        refreshTable();
    }
    
    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(0, 120, 215));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Student> students = studentDAO.getAllStudents();
        for (Student s : students) {
            Object[] row = { s.getId(), s.getFirstName(), s.getLastName(), s.getEmail() };
            tableModel.addRow(row);
        }
    }
    
    private void addStudent() {
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField emailField = new JTextField();
        
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.add(new JLabel("First Name:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String email = emailField.getText().trim();
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Student newStudent = new Student(0, 0, firstName, lastName, email);
            studentDAO.addStudent(newStudent);
            JOptionPane.showMessageDialog(this, "Student added successfully!");
            refreshTable();
        }
    }
    
    private void editStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a student to edit.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int studentId = (int) tableModel.getValueAt(selectedRow, 0);
        String currentFirstName = (String) tableModel.getValueAt(selectedRow, 1);
        String currentLastName = (String) tableModel.getValueAt(selectedRow, 2);
        String currentEmail = (String) tableModel.getValueAt(selectedRow, 3);
        
        JTextField firstNameField = new JTextField(currentFirstName);
        JTextField lastNameField = new JTextField(currentLastName);
        JTextField emailField = new JTextField(currentEmail);
        
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.add(new JLabel("First Name:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Edit Student", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String newFirstName = firstNameField.getText().trim();
            String newLastName = lastNameField.getText().trim();
            String newEmail = emailField.getText().trim();
            if (newFirstName.isEmpty() || newLastName.isEmpty() || newEmail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Student updatedStudent = new Student(studentId, 0, newFirstName, newLastName, newEmail);
            studentDAO.updateStudent(updatedStudent);
            JOptionPane.showMessageDialog(this, "Student updated successfully!");
            refreshTable();
        }
    }
    
    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int studentId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            studentDAO.deleteStudent(studentId);
            JOptionPane.showMessageDialog(this, "Student deleted successfully!");
            refreshTable();
        }
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Manage Students UI Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.add(new ManageStudentsPanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
