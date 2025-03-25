/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.EnrollmentDAO;
import dao.CourseDAO;
import dao.StudentDAO;
import entity.Enrollment;
import entity.Course;
/**
 *
 * @author Admin
 */



public class EnrollmentService {
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    private CourseDAO courseDAO = new CourseDAO();
    private StudentDAO studentDAO = new StudentDAO();

    // Register a student for a course
    public boolean enrollStudent(int studentId, int courseId) {
        // Check if student is already enrolled in the course
        if (enrollmentDAO.isEnrolled(studentId, courseId)) {
            return false;  // Student is already enrolled
        }

        // Check if the course has available seats
        if (!enrollmentDAO.hasAvailableSeats(courseId)) {
            return false;  // No available seats
        }

        // Check prerequisites
        Course course = courseDAO.getCourseById(courseId);
        // Assume we have some logic to check if prerequisites are met (not implemented here)

        // Enroll the student
        Enrollment enrollment = new Enrollment(studentId, courseId);
        return enrollmentDAO.saveEnrollment(enrollment);
    }
}
