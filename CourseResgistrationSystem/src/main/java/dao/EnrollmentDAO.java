/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBConnection;
import entity.Course;
import entity.Enrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */

/**
 * EnrollmentDAO for managing student enrollments in a table of the form:
 *    id           INT (auto-increment, PK)
 *    student_id   INT
 *    course_code  VARCHAR(20)
 */
public class EnrollmentDAO {

    /**
     * Registers a student for a course.
     * We receive an integer courseId, but the enrollments table uses course_code.
     * So we first retrieve the course code from the courses table based on courseId.
     */
    public boolean registerStudentForCourse(int studentId, int courseId) {
        String insertSql = "INSERT INTO enrollments (student_id, course_code) VALUES (?, ?)";
        
        try (Connection conn = DBConnection.getConnection()) {
            // 1) Lookup course_code using the courseId.
            String courseCode = getCourseCodeById(conn, courseId);
            if (courseCode == null) {
                System.err.println("No course found with id=" + courseId);
                return false;
            }

            // 2) Insert into enrollments with the found courseCode.
            try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                pstmt.setInt(1, studentId);
                pstmt.setString(2, courseCode);
                return pstmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Enroll a student to a course (same logic as registerStudentForCourse).
     */
    public boolean enrollStudent(int studentId, int courseId) {
        return registerStudentForCourse(studentId, courseId);
    }

    /**
     * Drops a course enrollment for a student.
     * We look up the course_code from the courses table, then delete by student_id and course_code.
     */
    public boolean dropCourse(int studentId, int courseId) {
        String deleteSql = "DELETE FROM enrollments WHERE student_id = ? AND course_code = ?";
        
        try (Connection conn = DBConnection.getConnection()) {
            String courseCode = getCourseCodeById(conn, courseId);
            if (courseCode == null) {
                System.err.println("No course found with id=" + courseId);
                return false;
            }
            
            try (PreparedStatement pstmt = conn.prepareStatement(deleteSql)) {
                pstmt.setInt(1, studentId);
                pstmt.setString(2, courseCode);
                return pstmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves the list of courses in which a student is enrolled.
     * Joins on course_code because that's what's stored in enrollments.
     */
    public List<Course> getEnrolledCoursesForStudent(int studentId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT c.id, c.course_code, c.course_name, c.description, c.credits " +
                     "FROM courses c " +
                     "JOIN enrollments e ON c.course_code = e.course_code " +
                     "WHERE e.student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course();
                    course.setId(rs.getInt("id"));
                    course.setCourseCode(rs.getString("course_code"));
                    course.setCourseName(rs.getString("course_name"));
                    course.setDescription(rs.getString("description"));
                    course.setCredits(rs.getInt("credits"));
                    courses.add(course);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    /**
     * Retrieves all enrollments for a specific student.
     * Because the enrollments table has (id, student_id, course_code), we store course_code in Enrollment.
     */
    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT id, student_id, course_code FROM enrollments WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, studentId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    // Make sure your Enrollment entity can store courseCode (e.g., a constructor or setter).
                    Enrollment enrollment = new Enrollment(
                            rs.getInt("id"), 
                            rs.getInt("student_id"), 
                            rs.getString("course_code")
                    );
                    enrollments.add(enrollment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    /**
     * Retrieves all enrollments for a specific course by courseId.
     * 1) Finds the course_code, 
     * 2) then selects from enrollments by that course_code.
     */
    public List<Enrollment> getEnrollmentsByCourse(int courseId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String selectSql = "SELECT id, student_id, course_code FROM enrollments WHERE course_code = ?";

        try (Connection conn = DBConnection.getConnection()) {
            String courseCode = getCourseCodeById(conn, courseId);
            if (courseCode == null) {
                System.err.println("No course found with id=" + courseId);
                return enrollments;
            }

            try (PreparedStatement pst = conn.prepareStatement(selectSql)) {
                pst.setString(1, courseCode);
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        Enrollment enrollment = new Enrollment(
                                rs.getInt("id"),
                                rs.getInt("student_id"),
                                rs.getString("course_code")
                        );
                        enrollments.add(enrollment);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    /**
     * Checks if a student is already enrolled in a course by ID.
     * 1) Convert courseId -> course_code
     * 2) Check if a record exists for (student_id, course_code).
     */
    public boolean isEnrolled(int studentId, int courseId) {
        String sql = "SELECT 1 FROM enrollments WHERE student_id = ? AND course_code = ?";

        try (Connection conn = DBConnection.getConnection()) {
            String courseCode = getCourseCodeById(conn, courseId);
            if (courseCode == null) {
                return false;
            }

            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, studentId);
                pst.setString(2, courseCode);
                try (ResultSet rs = pst.executeQuery()) {
                    return rs.next();  // If a row is found, the student is enrolled.
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks if a course has available slots. We:
     *  1) Convert courseId -> course_code
     *  2) Count how many enrollments exist for that course_code
     *  3) Compare with max_capacity from the courses table by courseId
     */
    public boolean hasAvailableSeats(int courseId) {
        String countSql = "SELECT COUNT(*) AS enrollment_count FROM enrollments WHERE course_code = ?";

        try (Connection conn = DBConnection.getConnection()) {
            String courseCode = getCourseCodeById(conn, courseId);
            if (courseCode == null) {
                return false;
            }

            try (PreparedStatement pst = conn.prepareStatement(countSql)) {
                pst.setString(1, courseCode);
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        int enrolledCount = rs.getInt("enrollment_count");

                        // Now check the max capacity from the courses table by ID.
                        String capacitySql = "SELECT max_capacity FROM courses WHERE id = ?";
                        try (PreparedStatement pst2 = conn.prepareStatement(capacitySql)) {
                            pst2.setInt(1, courseId);
                            try (ResultSet rs2 = pst2.executeQuery()) {
                                if (rs2.next()) {
                                    int capacity = rs2.getInt("max_capacity");
                                    return enrolledCount < capacity;
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Utility method: Retrieve the course_code from the courses table using the numeric course ID.
     * Returns null if no matching record is found.
     */
    private String getCourseCodeById(Connection conn, int courseId) throws SQLException {
        String sql = "SELECT course_code FROM courses WHERE id = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, courseId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("course_code");
                }
            }
        }
        return null;
    }
}
