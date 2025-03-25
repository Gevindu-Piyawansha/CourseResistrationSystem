/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Admin
 */


import dao.UserDAO;
import entity.User;

public class AuthService {
    private UserDAO userDAO = new UserDAO();
    private User loggedInUser = null;

    // Authenticate user and return role if valid
    public String login(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getHashedPassword().equals(password)) {
            this.loggedInUser = user;
            return user.getRole();
        }
        return null;
    }

    // Logout the user
    public void logout() {
        this.loggedInUser = null;
    }

    // Get current logged-in user
    public User getLoggedInUser() {
        return loggedInUser;
    }
}
