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

    // Inserts a new student record into the students table
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (first_name, last_name, email) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, student.getFirstName());
            pst.setString(2, student.getLastName());
            pst.setString(3, student.getEmail());

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        // Set the generated id to the student object
                        student.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Retrieves all student records from the students table
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name, email FROM students";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                // Using 0 for the userId field if it's not applicable
                Student student = new Student(id, 0, firstName, lastName, email);
                students.add(student);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return students;
    }

  

    // Updates an existing student record
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, student.getFirstName());
            pst.setString(2, student.getLastName());
            pst.setString(3, student.getEmail());
            pst.setInt(4, student.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Deletes a student record by id
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
}
