/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.resturant.servlets;

import com.resturant.models.User;
import com.resturant.services.UserService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author anderson
 */
@WebServlet(name = "ManageUsersServlet", urlPatterns = {"/manage-users"})
public class ManageUsersServlet extends HttpServlet {
    private UserService userService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        
        if (currentUser == null || (!currentUser.getRole().equals("admin") && !currentUser.getRole().equals("manager"))) {
            response.sendRedirect("login");
            return;
        }
        
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/manage-users.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (null == action) {
            response.sendRedirect("manage-users");
        } else switch (action) {
            case "create" -> createUser(request, response);
            case "update" -> updateUser(request, response);
            case "delete" -> deleteUser(request, response);
            default -> response.sendRedirect("manage-users");
        }
    }
    
    private void createUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setRole(request.getParameter("role"));
        user.setFullName(request.getParameter("fullName"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        
        if (userService.createStaffUser(user)) {
            request.setAttribute("success", "User created successfully");
        } else {
            request.setAttribute("error", "Failed to create user");
        }
        
        doGet(request, response);
    }
    
    private void updateUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        User user = new User();
        user.setUserId(Integer.parseInt(request.getParameter("userId")));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setRole(request.getParameter("role"));
        user.setFullName(request.getParameter("fullName"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        
        if (userService.updateUser(user)) {
            request.setAttribute("success", "User updated successfully");
        } else {
            request.setAttribute("error", "Failed to update user");
        }
        
        doGet(request, response);
    }
    
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        if (userService.deleteUser(userId)) {
            request.setAttribute("success", "User deleted successfully");
        } else {
            request.setAttribute("error", "Failed to delete user");
        }
        
        doGet(request, response);
    }
}