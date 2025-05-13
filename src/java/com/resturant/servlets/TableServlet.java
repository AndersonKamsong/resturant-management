/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.resturant.servlets;

import com.resturant.DAO.TableDAO;
import com.resturant.models.Table;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author anderson
 */
@WebServlet(name = "TableServlet", urlPatterns = {"/tables"})
public class TableServlet extends HttpServlet {
    private TableDAO tableDAO;

    @Override
    public void init() throws ServletException {
        tableDAO = new TableDAO(); // Initialize DAO
    }

    // Handling the GET request to fetch tables
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // If no specific action, list all tables
            listTables(request, response);
        } else {
            switch (action) {
                case "view" -> {
                    // Get table by ID
                    int tableId = Integer.parseInt(request.getParameter("table_id"));
                    viewTable(request, response, tableId);
                }
                case "status" -> {
                    // Get tables by status
                    String status = request.getParameter("status");
                    listTablesByStatus(request, response, status);
                }
                default -> listTables(request, response);
            }
        }
    }

    // Handling the POST request to create or update tables
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add" -> addTable(request, response);
                case "update" -> updateTable(request, response);
                case "delete" -> deleteTable(request, response);
                case "updateStatus" -> updateTableStatus(request, response);
            }
        }
    }

    // Add a new table
    private void addTable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tableNumber = request.getParameter("table_number");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        String status = request.getParameter("status");
        String locationDescription = request.getParameter("location_description");

        Table table = new Table(tableNumber, capacity, status, locationDescription);
        if (tableDAO.addTable(table)) {
            response.sendRedirect("tables?action=list");
        } else {
            response.getWriter().write("Error adding table.");
        }
    }

    // Update an existing table
    private void updateTable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int tableId = Integer.parseInt(request.getParameter("table_id"));
        String tableNumber = request.getParameter("table_number");
        String status = request.getParameter("status");
        String locationDescription = request.getParameter("location_description");

        Table table = new Table(tableNumber, tableId, status, locationDescription);
        if (tableDAO.updateTable(table)) {
            response.sendRedirect("tables?action=list");
        } else {
            response.getWriter().write("Error updating table.");
        }
    }

    // Delete a table
    private void deleteTable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int tableId = Integer.parseInt(request.getParameter("table_id"));
        if (tableDAO.deleteTable(tableId)) {
            response.sendRedirect("tables?action=list");
        } else {
            response.getWriter().write("Error deleting table.");
        }
    }

    // Update table status
    private void updateTableStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int tableId = Integer.parseInt(request.getParameter("table_id"));
        String newStatus = request.getParameter("new_status");
        if (tableDAO.updateTableStatus(tableId, newStatus)) {
            response.sendRedirect("tables?action=list");
        } else {
            response.getWriter().write("Error updating table status.");
        }
    }

    // View details of a specific table
    private void viewTable(HttpServletRequest request, HttpServletResponse response, int tableId) throws ServletException, IOException {
        Table table = tableDAO.getTableById(tableId);
        if (table != null) {
            request.setAttribute("table", table);
            request.getRequestDispatcher("/tableDetails.jsp").forward(request, response);
        } else {
            response.getWriter().write("Table not found.");
        }
    }

    // List all tables
    private void listTables(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Table> tables = tableDAO.getAllTables();
        request.setAttribute("tables", tables);
        request.getRequestDispatcher("/manage-tables.jsp").forward(request, response);
    }

    // List tables by their status
    private void listTablesByStatus(HttpServletRequest request, HttpServletResponse response, String status) throws ServletException, IOException {
        List<Table> tables = tableDAO.getTablesByStatus(status);
        request.setAttribute("tables", tables);
        request.getRequestDispatcher("/manage-tables.jsp").forward(request, response);
    }
}
