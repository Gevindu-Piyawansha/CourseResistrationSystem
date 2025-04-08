/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */

public class Course {
    private int id;
    private String courseCode;
    private String courseName;
    private String description;
    private int credits;
//    private String schedule;
    
 
    // Constructor 
    public Course(int id, String courseCode, String courseName, String description, int credits) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.credits = credits;
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getCredits() {
        return credits;
    }
    public void setCredits(int credit) {
        this.credits = credit;
    }
//     public String getSchedule() {
//        return schedule;
//    }
//    public void setSchedule(String schedule) {
//        this.schedule = schedule;
//    }
}
