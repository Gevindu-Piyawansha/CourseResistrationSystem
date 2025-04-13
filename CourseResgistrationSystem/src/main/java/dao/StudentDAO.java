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
        // Include student_id (which references users(id)) in the insert statement.
        String sql = "INSERT INTO students (student_id, first_name, last_name, dob, program, email, enrollment_year) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set the student_id with the value from the user id.
            pst.setInt(1, student.getStudentId());
            pst.setString(2, student.getFirstName());
            pst.setString(3, student.getLastName());
            // Convert String to SQL Date (expects format "YYYY-MM-DD")
            pst.setDate(4, Date.valueOf(student.getDob()));
            pst.setString(5, student.getProgram());
            pst.setString(6, student.getEmail());
            pst.setInt(7, student.getEnrollmentYear());

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        // Set the auto-generated id to the student object.
                        student.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public List<Student> getAllStudents() {
    List<Student> students = new ArrayList<>();
    // Include student_id in the SELECT so we can capture the foreign key from users table.
    String sql = "SELECT id, student_id, first_name, last_name, dob, program, email, enrollment_year FROM students";
    try (Connection con = DBConnection.getConnection();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            int id = rs.getInt("id");
            int studentId = rs.getInt("student_id"); // The foreign key linking to users(id)
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            java.sql.Date dobDate = rs.getDate("dob");
            // Convert DOB to String with proper null check.
            String dob = (dobDate == null) ? "" : dobDate.toString();
            String program = rs.getString("program");
            String email = rs.getString("email");
            int enrollmentYear = rs.getInt("enrollment_year");

            // Create a Student object. 
            // Make sure you have a corresponding constructor in your Student entity that accepts these parameters.
            Student student = new Student(id, studentId, firstName, lastName, dob, program, email, enrollmentYear);
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


    // Other methods (getAllStudents, updateStudent, deleteStudent, etc.)
    
    // Retrieval method updated to use student_id (foreign key) for matching.
    public Student getStudentById(int userId) {
        String sql = "SELECT id, student_id, first_name, last_name, dob, program, email, enrollment_year " +
                     "FROM students WHERE student_id = ?";
        Student student = null;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setInt(1, userId);  // Use the user id as the student_id value.
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setStudentId(rs.getInt("student_id"));
                    student.setFirstName(rs.getString("first_name"));
                    student.setLastName(rs.getString("last_name"));
                    java.sql.Date dobDate = rs.getDate("dob");
                    String dob = (dobDate == null) ? "" : dobDate.toString();
                    student.setDob(dob);
                    student.setProgram(rs.getString("program"));
                    student.setEmail(rs.getString("email"));
                    student.setEnrollmentYear(rs.getInt("enrollment_year"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return student;
    }
}


