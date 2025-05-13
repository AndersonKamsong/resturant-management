/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.resturant.services;

/**
 *
 * @author anderson
 */
import com.resturant.DAO.TableDAO;
import com.resturant.models.Table;
import java.sql.SQLException;
import java.util.List;

public class TableService {
    private final TableDAO tableDAO;
    
    public TableService() {
        this.tableDAO = new TableDAO();
    }
    
    public List<Table> getAllTables() {
        try {
            return tableDAO.getAllTables();
        } catch (SQLException e) {
            return null;
        }
    }
    
    public boolean updateTableStatus(int tableId, String status) {
        try {
            return tableDAO.updateTableStatus(tableId, status);
        } catch (SQLException e) {
            return false;
        }
    }
}
