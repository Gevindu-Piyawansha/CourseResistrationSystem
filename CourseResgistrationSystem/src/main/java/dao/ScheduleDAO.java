/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBConnection;
import entity.Schedule;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Admin
 */

public class ScheduleDAO {
    public List<Schedule> getSchedulesByStudentId(int studentId) {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT id, student_id, course_name, day_of_week, start_time, end_time, location " +
                     "FROM schedules WHERE student_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int sId = rs.getInt("student_id");
                String courseName = rs.getString("course_name");
                String dayOfWeek = rs.getString("day_of_week");
                // You can retrieve TIME columns as String using getString().
                String startTime = rs.getString("start_time");
                String endTime = rs.getString("end_time");
                String location = rs.getString("location");
                schedules.add(new Schedule(id, sId, courseName, dayOfWeek, startTime, endTime, location));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return schedules;
    }
}

