/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.resturant.DAO;

/**
 *
 * @author anderson
 */
import com.resturant.config.DBUtil;
import com.resturant.models.MenuItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO {
    public List<MenuItem> getAllMenuItems() throws SQLException {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";
        
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                MenuItem item = new MenuItem();
                item.setItemId(rs.getInt("item_id"));
                item.setCategoryId(rs.getInt("category_id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setAvailable(rs.getBoolean("available"));
                item.setImagePath(rs.getString("image_path"));
                items.add(item);
            }
        }
        return items;
    }
    
    public void addMenuItem(MenuItem item) throws SQLException {
        String sql = "INSERT INTO menu_items (category_id, name, description, price, available, image_path) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, item.getCategoryId());
            pstmt.setString(2, item.getName());
            pstmt.setString(3, item.getDescription());
            pstmt.setDouble(4, item.getPrice());
            pstmt.setBoolean(5, item.isAvailable());
            pstmt.setString(6, item.getImagePath());
            
            pstmt.executeUpdate();
        }
    }
    
    // Other CRUD operations
}
