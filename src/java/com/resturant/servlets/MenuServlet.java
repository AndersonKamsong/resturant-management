/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.resturant.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author anderson
 */
@WebServlet(name = "MenuServlet", urlPatterns = {"/menu"})
public class MenuServlet extends HttpServlet {

    private MenuService menuService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.menuService = new MenuService();
        System.out.println("MenuServlet initialized with MenuService.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet called.");

        List<MenuItem> menuItems = menuService.getAllMenuItems();
        System.out.println("Fetched " + menuItems.size() + " menu items.");

        request.setAttribute("menuItems", menuItems);
        request.getRequestDispatcher("/menu.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost called.");

        String action = request.getParameter("action");
        System.out.println("Action received: " + action);

        if ("add".equals(action)) {
            System.out.println("Processing 'add' action.");

            // Extract data from request
            String categoryIdParam = request.getParameter("categoryId");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String priceParam = request.getParameter("price");
            String availableParam = request.getParameter("available");
            String imagePath = request.getParameter("imagePath");

            // Debugging the input values
            System.out.println("Received categoryId: " + categoryIdParam);
            System.out.println("Received name: " + name);
            System.out.println("Received description: " + description);
            System.out.println("Received price: " + priceParam);
            System.out.println("Received available: " + availableParam);
            System.out.println("Received imagePath: " + imagePath);

            // Create MenuItem object and set values
            MenuItem item = new MenuItem();
            item.setCategoryId(Integer.parseInt(categoryIdParam));
            item.setName(name);
            item.setDescription(description);
            item.setPrice(Double.parseDouble(priceParam));
            item.setAvailable(availableParam != null);
            item.setImagePath(imagePath);

            System.out.println("MenuItem created: " + item);

            // Attempt to add the item to the service
            boolean isAdded = menuService.addMenuItem(item);
            if (isAdded) {
                System.out.println("Menu item added successfully.");
                request.setAttribute("message", "Menu item added successfully!");
            } else {
                System.out.println("Failed to add menu item.");
                request.setAttribute("error", "Failed to add menu item");
            }
        } else {
            System.out.println("No valid action specified.");
        }

        // Redirect back to doGet for displaying updated menu
        doGet(request, response);
    }
}

