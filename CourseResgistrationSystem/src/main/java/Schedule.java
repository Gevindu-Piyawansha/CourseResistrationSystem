/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
/**
 *
 * @author Admin
 */


public class Schedule {
    private int id;
    private String courseName;
    private String dayOfWeek;
    private String startTime;  // Stored as a string in format "HH:MM:SS"
    private String endTime;
    private String location;

    public Schedule(int id, int studentId, String courseName, String dayOfWeek, String startTime, String endTime, String location) {
        this.id = id;
        
        this.courseName = courseName;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    // Getters and setters

    public int getId() {
        return id;
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

    // Optionally, add setters if necessary.
}

