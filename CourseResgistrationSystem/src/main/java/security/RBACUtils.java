/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package security;

/**
 *
 * @author Admin
 */


public class RBACUtils {

    public static boolean hasAdminAccess(String role) {
        return "admin".equals(role);
    }

    public static boolean hasFacultyAccess(String role) {
        return "faculty".equals(role) || hasAdminAccess(role);
    }

    public static boolean hasStudentAccess(String role) {
        return "student".equals(role) || hasFacultyAccess(role);
    }
}
