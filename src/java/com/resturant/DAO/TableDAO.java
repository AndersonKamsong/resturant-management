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
import com.resturant.models.Table;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDAO {

    public boolean addTable(Table table) {
        String sql = "INSERT INTO tables (table_number, capacity, status, location_description) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, table.getTableNumber());
            stmt.setInt(2, table.getCapacity());
            stmt.setString(3, table.getStatus());
            stmt.setString(4, table.getLocationDescription());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateTable(Table table) {
        String sql = "UPDATE tables SET table_number=?, capacity=?, status=?, location_description=? WHERE table_id=?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, table.getTableNumber());
            stmt.setInt(2, table.getCapacity());
            stmt.setString(3, table.getStatus());
            stmt.setString(4, table.getLocationDescription());
            stmt.setInt(5, table.getTableId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteTable(int tableId) {
        String sql = "DELETE FROM tables WHERE table_id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tableId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public Table getTableById(int tableId) {
        String sql = "SELECT * FROM tables WHERE table_id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tableId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Table(
                                                rs.getString("table_number"),
                        rs.getInt("table_id"),
                        rs.getString("status"),
                        rs.getString("location_description")
                );
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Table> getAllTables() {
        List<Table> list = new ArrayList<>();
        String sql = "SELECT * FROM tables";
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new Table(
                                                rs.getString("table_number"),
                        rs.getInt("table_id"),
                        rs.getString("status"),
                        rs.getString("location_description")
                ));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public boolean updateTableStatus(int tableId, String newStatus) {
        String sql = "UPDATE tables SET status = ? WHERE table_id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, tableId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Table> getTablesByStatus(String status) {
        List<Table> list = new ArrayList<>();
        String sql = "SELECT * FROM tables WHERE status = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Table(
                                                rs.getString("table_number"),
                        rs.getInt("table_id"),
                        rs.getString("status"),
                        rs.getString("location_description")
                ));
            }
        } catch (SQLException e) {
        }
        return list;
    }

}
