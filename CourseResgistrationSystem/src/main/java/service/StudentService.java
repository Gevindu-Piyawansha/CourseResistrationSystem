/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.StudentDAO;
import entity.Student;
/**
 *
 * @author Admin
 */

public class StudentService {
    private StudentDAO studentDAO = new StudentDAO();

    public boolean registerStudent(Student student) {
        // Validate that essential fields are provided
        if (student.getFirstName() == null || student.getFirstName().isEmpty() ||
            student.getLastName() == null || student.getLastName().isEmpty() ||
            student.getEmail() == null || student.getEmail().isEmpty()) {
            return false;
        }
        try {
            // Use the updated addStudent method from StudentDAO
            studentDAO.addStudent(student);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
