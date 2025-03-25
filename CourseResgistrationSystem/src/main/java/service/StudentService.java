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
        if (student.getName().isEmpty() || student.getDob() == null) {
            return false;
        }
        return studentDAO.saveStudent(student);
    }
}
