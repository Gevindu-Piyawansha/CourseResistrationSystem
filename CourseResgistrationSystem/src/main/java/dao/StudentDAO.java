/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBConnection;
import entity.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */


public class StudentDAO {

    // Inserts a new student record into the students table.
    public void addStudent(Student student) {
        // Do not insert the id column (assumes it is auto-generated).
        String sql = "INSERT INTO students (first_name, last_name, dob, program, email, enrollment_year) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, student.getFirstName());
            pst.setString(2, student.getLastName());
            // Convert String to SQL Date (expects format "YYYY-MM-DD")
            pst.setDate(3, Date.valueOf(student.getDob()));
            
            pst.setString(4, student.getProgram());
            pst.setString(5, student.getEmail());
            pst.setInt(6, student.getEnrollmentYear());

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        // Set the generated id to the student object.
                        student.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Retrieves all student records from the students table.
    public List<Student> getAllStudents() {
    List<Student> students = new ArrayList<>();
    String sql = "SELECT id, first_name, last_name, dob, program, email, enrollment_year FROM students";
    try (Connection con = DBConnection.getConnection();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            java.sql.Date dobDate = rs.getDate("dob"); // Local variable for DOB.
            // Check if dobDate is null before converting to string.
            String dob = (dobDate == null) ? "" : dobDate.toString();
            String program = rs.getString("program");
            String email = rs.getString("email");
            int enrollmentYear = rs.getInt("enrollment_year");

            Student student = new Student(id, firstName, lastName, dob, program, email, enrollmentYear);
            students.add(student);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return students;
}


    // Updates an existing student record.
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, dob = ?, program = ?, email = ?, enrollment_year = ? " +
                     "WHERE id = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, student.getFirstName());
            pst.setString(2, student.getLastName());
            pst.setDate(3, Date.valueOf(student.getDob()));
            pst.setString(4, student.getProgram());
            pst.setString(5, student.getEmail());
            pst.setInt(6, student.getEnrollmentYear());
            pst.setInt(7, student.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Deletes a student record by id.
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Existing methods (add, update, delete, getAllStudents, etc.)

    // New method to retrieve a student by id.
    public Student getStudentById(int id) {
        String sql = "SELECT id, first_name, last_name, dob, program, email, enrollment_year FROM students WHERE id = ?";
        Student student = null;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int studentId = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    // Handle possible NULL date (if necessary)
                    java.sql.Date dobDate = rs.getDate("dob");
                    String dob = (dobDate == null) ? "" : dobDate.toString();
                    String program = rs.getString("program");
                    String email = rs.getString("email");
                    int enrollmentYear = rs.getInt("enrollment_year");
                    
                    student = new Student(studentId, firstName, lastName, dob, program, email, enrollmentYear);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return student;
    }
}

