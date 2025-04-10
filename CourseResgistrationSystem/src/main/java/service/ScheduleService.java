/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ScheduleDAO;
import entity.Schedule;
import java.util.List;
/**
 *
 * @author Admin
 */


public class ScheduleService {
    private ScheduleDAO scheduleDAO = new ScheduleDAO();

    public List<Schedule> getStudentSchedule(int studentId) {
        return scheduleDAO.getSchedulesByStudentId(studentId);
    }
}
