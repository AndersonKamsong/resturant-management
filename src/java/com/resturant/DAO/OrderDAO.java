/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.resturant.DAO;

/**
 *
 * @author anderson
 */
import com.resturant.models.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private final Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addOrder(Order order) {
        String sql = "INSERT INTO orders (table_id, user_id, status, order_time, total_amount, notes) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getTableId());
            stmt.setInt(2, order.getUserId());
            stmt.setString(3, order.getStatus());
            stmt.setTimestamp(4, order.getOrderTime());
            stmt.setDouble(5, order.getTotalAmount());
            stmt.setString(6, order.getNotes());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.warning("No rows affected while inserting the order.");
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getInt(1));
                } else {
                    LOGGER.warning("Order inserted but failed to retrieve generated order ID.");
                }
            }
            return true;
        } catch (SQLException e) {
            logSQLException("addOrder", e);
            return false;
        }
    }

    public Order getOrderById(int id) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractOrderFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            logSQLException("getOrderById", e);
        }
        return null;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orders.add(extractOrderFromResultSet(rs));
            }
        } catch (SQLException e) {
            logSQLException("getAllOrders", e);
        }
        return orders;
    }

    public boolean updateOrder(Order order) {
        String sql = "UPDATE orders SET table_id=?, user_id=?, status=?, order_time=?, total_amount=?, notes=? WHERE order_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getTableId());
            stmt.setInt(2, order.getUserId());
            stmt.setString(3, order.getStatus());
            stmt.setTimestamp(4, order.getOrderTime());
            stmt.setDouble(5, order.getTotalAmount());
            stmt.setString(6, order.getNotes());
            stmt.setInt(7, order.getOrderId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                LOGGER.log(Level.WARNING, "No order found with ID: {0}", order.getOrderId());
            }
            return rowsUpdated > 0;
        } catch (SQLException e) {
            logSQLException("updateOrder", e);
            return false;
        }
    }

    public boolean deleteOrder(int id) {
        String sql = "DELETE FROM orders WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted == 0) {
                LOGGER.log(Level.WARNING, "No order found to delete with ID: {0}", id);
            }
            return rowsDeleted > 0;
        } catch (SQLException e) {
            logSQLException("deleteOrder", e);
            return false;
        }
    }

    private Order extractOrderFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setTableId(rs.getInt("table_id"));
        order.setUserId(rs.getInt("user_id"));
        order.setStatus(rs.getString("status"));
        order.setOrderTime(rs.getTimestamp("order_time"));
        order.setTotalAmount(rs.getDouble("total_amount"));
        order.setNotes(rs.getString("notes"));
        // Consider fetching order items in future
        return order;
    }

    private void logSQLException(String operation, SQLException e) {
        LOGGER.log(Level.SEVERE, String.format("SQL error in %s: %s (SQLState: %s, ErrorCode: %d)",
                operation, e.getMessage(), e.getSQLState(), e.getErrorCode()), e);
    }
}

