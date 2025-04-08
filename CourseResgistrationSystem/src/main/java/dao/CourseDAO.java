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
        String sql = "INSERT INTO courses (id, course_code, course_name, description, credits) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, course.getId());
            pst.setString(2, course.getCourseCode());
            pst.setString(3, course.getCourseName());
            pst.setString(4, course.getDescription());
            pst.setInt(5, course.getCredits());
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        course.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Alias for saveCourse
    public boolean addCourse(Course course) {
        return saveCourse(course);
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
                    rs.getInt("id"),
                    rs.getString("course_code"),
                    rs.getString("course_name"),
                    rs.getString("description"),
                    rs.getInt("credits")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    
    // Retrieve a course by its ID
    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM courses WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, courseId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()){
                    return new Course(
                        rs.getInt("id"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        rs.getString("description"),
                        rs.getInt("credits")
                    );
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Update course details
    public boolean updateCourse(Course course) {
        String sql = "UPDATE courses SET course_code = ?, course_name = ?, description = ?, credits = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, course.getCourseCode());
            pst.setString(2, course.getCourseName());
            pst.setString(3, course.getDescription());
            pst.setInt(4, course.getCredits());
            pst.setInt(5, course.getId());
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
}
