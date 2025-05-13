/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.resturant.services;

/**
 *
 * @author anderson
 */
import com.resturant.DAO.UserDAO;
import com.resturant.models.User;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;
    
    public UserService() {
        this.userDAO = new UserDAO();
    }
    
    public boolean registerUser(User user) {
        try {
            // Set default role to 'client' for registration
            user.setRole("client");
            return userDAO.createUser(user);
        } catch (SQLException e) {
            return false;
        }
    }
    
    public User authenticate(String username, String password) {
        try {
            User user = userDAO.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            return null;
        }
    }
    
    public boolean createStaffUser(User user) {
        try {
            // Only admin/manager can create staff users
            return userDAO.createUser(user);
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean updateUser(User user) {
        try {
            return userDAO.updateUser(user);
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean deleteUser(int userId) {
        try {
            return userDAO.deleteUser(userId);
        } catch (SQLException e) {
            return false;
        }
    }
}
