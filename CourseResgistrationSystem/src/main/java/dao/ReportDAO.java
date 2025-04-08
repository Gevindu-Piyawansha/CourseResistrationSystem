/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBConnection;
import java.sql.*;
/**
 *
 * @author Admin
 */


public class ReportDAO {
    // Course Enrollment Report returns course code, name, and the enrolled count.
    public String getCourseEnrollmentReportString() {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT c.course_code, c.course_name, COUNT(e.student_id) AS enrolled " +
                     "FROM courses c " +
                     "LEFT JOIN enrollments e ON c.id = e.course_id " +
                     "GROUP BY c.course_code, c.course_name";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            sb.append("Course Enrollment Report:\n");
            sb.append("------------------------------------------------\n");
            while (rs.next()) {
                sb.append("Course: ").append(rs.getString("course_code"))
                  .append(" - ").append(rs.getString("course_name"))
                  .append(" | Enrolled: ").append(rs.getInt("enrolled"))
                  .append("\n");
            }
            sb.append("------------------------------------------------\n");
        } catch (SQLException e) {
            e.printStackTrace();
            sb.append("Error generating report: ").append(e.getMessage());
        }
        return sb.toString();
    }
    
    // Vacant Seats Report – returns a placeholder message if max_capacity is missing.
    public String getVacantSeatsReportString() {
        return "Vacant seats report not available — 'max_capacity' column missing in courses table.";
    }
    
    // Student Progress Report – returns courses and enrollment status for a given student.
    public String getStudentProgressReportString(int studentId) {
        StringBuilder sb = new StringBuilder();
        String sql = "SELECT c.course_code, c.course_name, e.status " +
                     "FROM enrollments e " +
                     "JOIN courses c ON e.course_id = c.id " +
                     "WHERE e.student_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, studentId);
            try (ResultSet rs = pst.executeQuery()) {
                sb.append("Student Enrollment Status:\n");
                sb.append("------------------------------------------------\n");
                while (rs.next()) {
                    sb.append("Course: ").append(rs.getString("course_code"))
                      .append(" - ").append(rs.getString("course_name"))
                      .append(" | Status: ").append(rs.getString("status"))
                      .append("\n");
                }
                sb.append("------------------------------------------------\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            sb.append("Error generating report: ").append(e.getMessage());
        }
        return sb.toString();
    }
}
