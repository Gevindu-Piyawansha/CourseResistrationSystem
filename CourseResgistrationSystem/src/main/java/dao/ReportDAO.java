/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
/**
 *
 * @author Admin
 */



public class ReportDAO {

    // Get enrollment count per course
    public void generateCourseEnrollmentReport() {
        String sql = "SELECT c.course_id, c.title, COUNT(e.student_id) AS enrolled_students, c.max_capacity " +
                     "FROM courses c LEFT JOIN enrollments e ON c.course_id = e.course_id " +
                     "GROUP BY c.course_id, c.title, c.max_capacity";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            System.out.println("Course Enrollment Report:");
            System.out.println("------------------------------------------------");
            while (rs.next()) {
                System.out.println("Course: " + rs.getString("title") +
                        " | Enrolled: " + rs.getInt("enrolled_students") +
                        " / " + rs.getInt("max_capacity"));
            }
            System.out.println("------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get vacant seats in courses
    public void generateVacantSeatsReport() {
        String sql = "SELECT c.course_id, c.title, c.max_capacity - COUNT(e.student_id) AS vacant_seats " +
                     "FROM courses c LEFT JOIN enrollments e ON c.course_id = e.course_id " +
                     "GROUP BY c.course_id, c.title, c.max_capacity " +
                     "HAVING vacant_seats > 0";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            System.out.println("Courses with Vacant Seats:");
            System.out.println("------------------------------------------------");
            while (rs.next()) {
                System.out.println("Course: " + rs.getString("title") +
                        " | Vacant Seats: " + rs.getInt("vacant_seats"));
            }
            System.out.println("------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get student progress report (grades per student)
    public void generateStudentProgressReport(int studentId) {
        String sql = "SELECT c.title, e.grade FROM enrollments e " +
                     "JOIN courses c ON e.course_id = c.course_id " +
                     "WHERE e.student_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, studentId);
            try (ResultSet rs = pst.executeQuery()) {
                System.out.println("Student Progress Report:");
                System.out.println("------------------------------------------------");
                while (rs.next()) {
                    System.out.println("Course: " + rs.getString("title") +
                            " | Grade: " + rs.getString("grade"));
                }
                System.out.println("------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getEnrollmentsReport() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
