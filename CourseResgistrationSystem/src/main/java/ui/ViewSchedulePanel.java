/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import entity.Course;
import entity.Student;
import service.EnrollmentService;

/**
 *
 * @author Admin
 */

public class ViewSchedulePanel extends JPanel {
    private Student student;
    private EnrollmentService enrollmentService;
    private JTable scheduleTable;
    private JScrollPane scrollPane;
    
    public ViewSchedulePanel(Student student) {
        this.student = student;
        enrollmentService = new EnrollmentService();
        
        setLayout(new BorderLayout());
        
        JLabel heading = new JLabel("Your Registered Courses", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        add(heading, BorderLayout.NORTH);
        
        // Retrieve the courses in which the student is enrolled.
        List<Course> courses = enrollmentService.getEnrolledCoursesForStudent(student.getId());
        
        String[] columnNames = {"Course Code", "Course Name", "Credits"};
        Object[][] data = new Object[courses.size()][3];
        
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            data[i][0] = c.getCourseCode();
            data[i][1] = c.getCourseName();
            data[i][2] = c.getCredits();
        }
        
        scheduleTable = new JTable(data, columnNames);
        scrollPane = new JScrollPane(scheduleTable);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    // Optional: Refresh the schedule after new enrollments.
    public void refreshSchedule() {
        List<Course> courses = enrollmentService.getEnrolledCoursesForStudent(student.getId());
        String[] columnNames = {"Course Code", "Course Name", "Credits"};
        Object[][] data = new Object[courses.size()][3];
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            data[i][0] = c.getCourseCode();
            data[i][1] = c.getCourseName();
            data[i][2] = c.getCredits();
        }
        scheduleTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
    // Main method to run the panel independently.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create a dummy student for testing. 
            // Adjust the parameters to match your Student entity's constructor.
            // Here, we assume a constructor: Student(int id, int studentId, String firstName, String lastName, String dob, String program, String email, int enrollmentYear)
            Student dummyStudent = new Student(1, 1, "John", "Doe", "2000-01-01", "Computer Science", "john@example.com", 2022);
            
            JFrame frame = new JFrame("View Schedule Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new ViewSchedulePanel(dummyStudent));
            frame.setVisible(true);
        });
    }
}
