/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */


public class Enrollment {
    private int id;
    private int studentId;
    private String courseCode;
    
    public Enrollment() {}

    // Constructor with id, studentId, and courseCode.
    public Enrollment(int id, int studentId, String courseCode) {
        this.id = id;
        this.studentId = studentId;
        this.courseCode = courseCode;
    }
    
    // Constructor with studentId and courseCode.
    public Enrollment(int studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
