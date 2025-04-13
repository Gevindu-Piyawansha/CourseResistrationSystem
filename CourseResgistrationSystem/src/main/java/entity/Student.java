/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.time.LocalDate;
/**
 *
 * @author Admin
 */


public class Student {
    private int id;           // auto-generated primary key in the students table
    private int studentId;    // foreign key referencing users(id)
    private String firstName;
    private String lastName;
    private String dob;       // e.g., "YYYY-MM-DD"
    private String program;
    private String email;
    private int enrollmentYear;
    
    // Constructor for inserting a new student
    public Student(int studentId, String firstName, String lastName, String dob, 
                   String program, String email, int enrollmentYear) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.program = program;
        this.email = email;
        this.enrollmentYear = enrollmentYear;
    }
    
    // Constructor that includes the auto-generated id
    public Student(int id, int studentId, String firstName, String lastName, String dob, 
                   String program, String email, int enrollmentYear) {
        this.id = id;
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.program = program;
        this.email = email;
        this.enrollmentYear = enrollmentYear;
    }
    
    // Default constructor
    public Student() {}

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
    
    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public int getEnrollmentYear() { return enrollmentYear; }
    public void setEnrollmentYear(int enrollmentYear) { this.enrollmentYear = enrollmentYear; }
}
