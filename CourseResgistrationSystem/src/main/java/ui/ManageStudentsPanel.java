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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
        setPreferredSize(new Dimension(800, 400));

        // Updated column names to include DOB, Program, and Enrollment Year.
        String[] columnNames = {"ID", "First Name", "Last Name", "DOB", "Program", "Email", "Enrollment Year"};
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
            Object[] row = {
                s.getId(),
                s.getFirstName(),
                s.getLastName(),
                s.getDob(),
                s.getProgram(),
                s.getEmail(),
                s.getEnrollmentYear()
            };
            tableModel.addRow(row);
        }
    }

    private void addStudent() {
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField dobField = new JTextField();  // This field takes the DOB input.
        JTextField programField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField yearField = new JTextField();

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.add(new JLabel("First Name:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("DOB (YYYY-MM-DD):"));
        formPanel.add(dobField);
        formPanel.add(new JLabel("Program:"));
        formPanel.add(programField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Enrollment Year:"));
        formPanel.add(yearField);

        int result = JOptionPane.showConfirmDialog(this, formPanel, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String dobInput = dobField.getText().trim();
            String program = programField.getText().trim();
            String email = emailField.getText().trim();
            String yearStr = yearField.getText().trim();

            if (firstName.isEmpty() || lastName.isEmpty() || dobInput.isEmpty()
                    || program.isEmpty() || email.isEmpty() || yearStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                // Validate and parse the date to ensure it's in the correct format.
                LocalDate dobDate = LocalDate.parse(dobInput);  // Throws exception if format is not 'YYYY-MM-DD'
                // Convert to String in standard format (this step is optional if the input is already fine)
                String dobFormatted = dobDate.toString();
                int enrollmentYear = Integer.parseInt(yearStr);
                // Create the student with the properly formatted DOB.
                Student newStudent = new Student(0, firstName, lastName, dobFormatted, program, email, enrollmentYear);
                // Add student to DB (StudentDAO.addStudent internally does Date.valueOf(newStudent.getDob()))
                new StudentDAO().addStudent(newStudent);
                JOptionPane.showMessageDialog(this, "Student added successfully!");
                refreshTable();
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Please enter the date in YYYY-MM-DD format.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enrollment Year must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
        String currentDob = (String) tableModel.getValueAt(selectedRow, 3);
        String currentProgram = (String) tableModel.getValueAt(selectedRow, 4);
        String currentEmail = (String) tableModel.getValueAt(selectedRow, 5);
        int currentYear = (int) tableModel.getValueAt(selectedRow, 6);

        JTextField firstNameField = new JTextField(currentFirstName);
        JTextField lastNameField = new JTextField(currentLastName);
        JTextField dobField = new JTextField(currentDob);
        JTextField programField = new JTextField(currentProgram);
        JTextField emailField = new JTextField(currentEmail);
        JTextField yearField = new JTextField(String.valueOf(currentYear));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.add(new JLabel("First Name:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("DOB (YYYY-MM-DD):"));
        formPanel.add(dobField);
        formPanel.add(new JLabel("Program:"));
        formPanel.add(programField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Enrollment Year:"));
        formPanel.add(yearField);

        int result = JOptionPane.showConfirmDialog(this, formPanel, "Edit Student", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String newFirstName = firstNameField.getText().trim();
            String newLastName = lastNameField.getText().trim();
            String newDob = dobField.getText().trim();
            String newProgram = programField.getText().trim();
            String newEmail = emailField.getText().trim();
            String yearStr = yearField.getText().trim();
            if (newFirstName.isEmpty() || newLastName.isEmpty() || newDob.isEmpty()
                    || newProgram.isEmpty() || newEmail.isEmpty() || yearStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int newEnrollmentYear = Integer.parseInt(yearStr);
                Student updatedStudent = new Student(studentId, newFirstName, newLastName, newDob, newProgram, newEmail, newEnrollmentYear);
                studentDAO.updateStudent(updatedStudent);
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
                refreshTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enrollment Year must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Manage Students UI Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 500);
            frame.add(new ManageStudentsPanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
