/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.StudentDAO;
import entity.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Admin
 */
public class StudentManagementUI {

    public static void showUI() {
        JFrame frame = new JFrame("Student Management");
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JTextField nameField = new JTextField(20);
        JTextField dobField = new JTextField(10);
        JTextField programField = new JTextField(20);
        JTextField yearField = new JTextField(5);
        JButton addButton = new JButton("Add Student");

        frame.add(new JLabel("Name:"));
        frame.add(nameField);
        frame.add(new JLabel("DOB (YYYY-MM-DD):"));
        frame.add(dobField);
        frame.add(new JLabel("Program:"));
        frame.add(programField);
        frame.add(new JLabel("Year:"));
        frame.add(yearField);
        frame.add(addButton);

        addButton.addActionListener(e -> {
            Student student = new Student(0, nameField.getText(), dobField.getText(), programField.getText(), Integer.parseInt(yearField.getText()), "");
            new StudentDAO().addStudent(student);
            JOptionPane.showMessageDialog(frame, "Student Added!");
        });

        frame.setVisible(true);
    }
}
