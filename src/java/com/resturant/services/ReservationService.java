/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.resturant.services;

/**
 *
 * @author anderson
 */
import com.resturant.DAO.ReservationDAO;
import com.resturant.models.Reservation;
import java.sql.SQLException;
import java.util.List;

public class ReservationService {
    private final ReservationDAO reservationDAO;
    
    public ReservationService() {
        this.reservationDAO = new ReservationDAO();
    }
    
    public List<Reservation> getTodayReservations() {
        try {
            return reservationDAO.getTodayReservations();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean updateReservationStatus(int reservationId, String status) {
        try {
            return reservationDAO.updateReservationStatus(reservationId, status);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
