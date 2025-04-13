/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import dao.CourseDAO;
import entity.Course;

import java.util.List;
/**
 *
 * @author Admin
 */


public class CourseService {
    private CourseDAO courseDAO = new CourseDAO();
    
    public boolean addCourse(Course course) {
        return courseDAO.addCourse(course);
    }
    
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }
    
    public boolean updateCourse(Course course) {
        return courseDAO.updateCourse(course);
    }
    
    public boolean deleteCourse(int courseId) {
        return courseDAO.deleteCourse(courseId);
    }
    
    public Course getCourseById(int courseId) {
        return courseDAO.getCourseById(courseId);
    }
    public boolean registerCourse(int studentId, Course course) {
        // For now, this method simply prints a message and returns true.
        System.out.println("Registering student id " + studentId + " for course " + course.getCourseName());
        return true;
    }
}
