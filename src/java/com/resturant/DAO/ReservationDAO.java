/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.resturant.DAO;

/**
 *
 * @author anderson
 */
import com.resturant.models.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationDAO {
    private static final Logger LOGGER = Logger.getLogger(ReservationDAO.class.getName());
    private final Connection connection;

    public ReservationDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (table_id, customer_name, customer_phone, customer_email, reservation_time, party_size, status, special_requests) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getTableId());
            stmt.setString(2, reservation.getCustomerName());
            stmt.setString(3, reservation.getCustomerPhone());
            stmt.setString(4, reservation.getCustomerEmail());
            stmt.setTimestamp(5, reservation.getReservationTime());
            stmt.setInt(6, reservation.getPartySize());
            stmt.setString(7, reservation.getStatus());
            stmt.setString(8, reservation.getSpecialRequests());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding reservation", e);
            return false;
        }
    }

    public Reservation getReservationById(int id) {
        String sql = "SELECT * FROM reservations WHERE reservation_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractReservationFromResultSet(rs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving reservation by ID", e);
        }
        return null;
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(extractReservationFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all reservations", e);
        }
        return list;
    }

    public boolean updateReservation(Reservation reservation) {
        String sql = "UPDATE reservations SET table_id=?, customer_name=?, customer_phone=?, customer_email=?, reservation_time=?, party_size=?, status=?, special_requests=? WHERE reservation_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservation.getTableId());
            stmt.setString(2, reservation.getCustomerName());
            stmt.setString(3, reservation.getCustomerPhone());
            stmt.setString(4, reservation.getCustomerEmail());
            stmt.setTimestamp(5, reservation.getReservationTime());
            stmt.setInt(6, reservation.getPartySize());
            stmt.setString(7, reservation.getStatus());
            stmt.setString(8, reservation.getSpecialRequests());
            stmt.setInt(9, reservation.getReservationId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating reservation", e);
            return false;
        }
    }

    public boolean deleteReservation(int id) {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting reservation", e);
            return false;
        }
    }

    private Reservation extractReservationFromResultSet(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setReservationId(rs.getInt("reservation_id"));
        reservation.setTableId(rs.getInt("table_id"));
        reservation.setCustomerName(rs.getString("customer_name"));
        reservation.setCustomerPhone(rs.getString("customer_phone"));
        reservation.setCustomerEmail(rs.getString("customer_email"));
        reservation.setReservationTime(rs.getTimestamp("reservation_time"));
        reservation.setPartySize(rs.getInt("party_size"));
        reservation.setStatus(rs.getString("status"));
        reservation.setSpecialRequests(rs.getString("special_requests"));
        return reservation;
    }
}

