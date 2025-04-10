/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */


public class CourseSchedule {
    private int studentId;
    private String courseCode;
    private String courseName;
    private String dayOfWeek;
    private String startTime;  // Expecting format "HH:MM:SS"
    private String endTime;
    private String location;

    public CourseSchedule(int studentId, String courseCode, String courseName, String dayOfWeek,
                          String startTime, String endTime, String location) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }
}
