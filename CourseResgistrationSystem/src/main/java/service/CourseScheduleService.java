/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CourseScheduleDAO;
import entity.CourseSchedule;
import java.util.List;


/**
 *
 * @author Admin
 */

public class CourseScheduleService {
    private CourseScheduleDAO scheduleDAO = new CourseScheduleDAO();

    public List<CourseSchedule> getSchedulesForStudent(int studentId) {
        return scheduleDAO.getCourseSchedulesForStudent(studentId);
    }
}
