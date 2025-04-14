/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String email;
    private boolean mustChangePassword; // New flag for default credentials

    // No-argument constructor
    public User() {}

    // Full constructor (all fields)
    public User(int id, String username, String password, String role, String email, boolean mustChangePassword) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.mustChangePassword = mustChangePassword;
    }
    
    // Overloaded constructor with 4 parameters.
    public User(int id, String username, String password, String role) {
        this(id, username, password, role, "", false);
    }
    
    // Overloaded constructor with 4 parameters.
    // It sets mustChangePassword to true if the user is a student.
    public User(String username, String password, String role, String email) {
        // For a student, we assume default credentials should trigger a password update.
        this(0, username, password, role, email, role.equalsIgnoreCase("student"));
    }
    
    // Overloaded constructor with 5 parameters that assumes mustChangePassword is false.
    public User(int id, String username, String password, String role, String email) {
        this(id, username, password, role, email, false);
    }
    
    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isMustChangePassword() {
        return mustChangePassword;
    }
    public void setMustChangePassword(boolean mustChangePassword) {
        this.mustChangePassword = mustChangePassword;
    }
    
    @Override
    public String toString() {
        return username;  // Adjust for a better string representation as needed.
    }
}
