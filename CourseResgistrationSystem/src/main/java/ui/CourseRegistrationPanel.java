/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dao.CourseDAO;
import entity.Course;
import entity.User;
import service.EnrollmentService;

/**
 *
 * @author Admin
 */


public class CourseRegistrationPanel extends JPanel {
    private User user;
    private JComboBox<Course> courseComboBox;
    private JButton registerButton;
    private CourseDAO courseDAO;
    private EnrollmentService enrollmentService;
    
    public CourseRegistrationPanel(User user) {
        this.user = user;
        courseDAO = new CourseDAO();
        enrollmentService = new EnrollmentService();
        
        setLayout(new BorderLayout());
        
        // Retrieve all courses from MySQL via CourseDAO
        List<Course> courses = courseDAO.getAllCourses();
        courseComboBox = new JComboBox<>(courses.toArray(new Course[0]));
        courseComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Course) {
                    Course course = (Course) value;
                    setText(course.getCourseCode() + " - " + course.getCourseName());
                }
                return this;
            }
        });
        
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Course: "));
        topPanel.add(courseComboBox);
        
        registerButton = new JButton("Register");
        topPanel.add(registerButton);
        
        add(topPanel, BorderLayout.NORTH);
        
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Course selectedCourse = (Course) courseComboBox.getSelectedItem();
                if (selectedCourse != null) {
                    // Register the student for the course.
                    // This method should record in MySQL that the course belongs to the logged-in student.
                    boolean success = enrollmentService.registerStudentForCourse(user.getId(), selectedCourse.getId());
                    if(success) {
                        JOptionPane.showMessageDialog(CourseRegistrationPanel.this,
                                "Registration successful for course " + selectedCourse.getCourseCode());
                    } else {
                        JOptionPane.showMessageDialog(CourseRegistrationPanel.this,
                                "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
