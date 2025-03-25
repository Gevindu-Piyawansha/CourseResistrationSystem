/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBConnection;
import entity.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */


public class CourseDAO {

    // Save a new course to the database
    public boolean saveCourse(Course course) {
        String sql = "INSERT INTO courses (title, credit_hours, department, prerequisites, max_capacity) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, course.getTitle());
            pst.setInt(2, course.getCreditHours());
            pst.setString(3, course.getDepartment());
            pst.setString(4, course.getPrerequisites());
            pst.setInt(5, course.getMaxCapacity());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all courses from the database
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try (Connection con = DBConnection.getConnection(); 
             Statement stmt = con.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("credit_hours"), rs.getString("title"),
                        rs.getInt("max_capacity"),
                        rs.getString("department"),
                        rs.getString("prerequisites"), 50);
                course.setId(rs.getInt("id"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // Update course details
    public boolean updateCourse(Course course) {
        String sql = "UPDATE courses SET title = ?, credit_hours = ?, department = ?, prerequisites = ?, max_capacity = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, course.getTitle());
            pst.setInt(2, course.getCreditHours());
            pst.setString(3, course.getDepartment());
            pst.setString(4, course.getPrerequisites());
            pst.setInt(5, course.getMaxCapacity());
            pst.setInt(6, course.getId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a course from the database
    public boolean deleteCourse(int courseId) {
        String sql = "DELETE FROM courses WHERE id = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, courseId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Course getCourseById(int courseId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void addCourse(Course course) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
