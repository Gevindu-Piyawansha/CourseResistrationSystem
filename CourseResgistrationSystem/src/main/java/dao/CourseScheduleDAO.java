/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBConnection;
import entity.CourseSchedule;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */

public class CourseScheduleDAO {

    public List<CourseSchedule> getCourseSchedulesForStudent(int studentId) {
        List<CourseSchedule> scheduleList = new ArrayList<>();
        
        String sql = "SELECT e.student_id, c.course_code, c.course_name, " +
                     "s.day_of_week, s.start_time, s.end_time, s.location " +
                     "FROM enrollments e " +
                     "JOIN courses c ON e.course_code = c.course_code " +
                     "JOIN schedules s ON c.course_code = s.course_code " +
                     "WHERE e.student_id = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                int studId = rs.getInt("student_id");
                String courseCode = rs.getString("course_code");
                String courseName = rs.getString("course_name");
                String day = rs.getString("day_of_week");
                String startTime = rs.getString("start_time");
                String endTime = rs.getString("end_time");
                String location = rs.getString("location");

                CourseSchedule cs = new CourseSchedule(studId, courseCode, courseName, day, startTime, endTime, location);
                scheduleList.add(cs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return scheduleList;
    }
}
