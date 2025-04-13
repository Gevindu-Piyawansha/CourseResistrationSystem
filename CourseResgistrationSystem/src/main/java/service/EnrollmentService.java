/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.EnrollmentDAO;
import dao.CourseDAO;
import entity.Course;
import java.util.List;

/**
 *
 * @author Admin
 */

public class EnrollmentService {
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private CourseDAO courseDAO = new CourseDAO();
    
    // Register a student for a selected course.
    public boolean registerStudentForCourse(int studentId, int courseId) {
        return enrollmentDAO.registerStudentForCourse(studentId, courseId);
    }
    
    // Retrieve all courses for which the student is enrolled.
    public List<Course> getEnrolledCoursesForStudent(int studentId) {
        return enrollmentDAO.getEnrolledCoursesForStudent(studentId);
    }
}
