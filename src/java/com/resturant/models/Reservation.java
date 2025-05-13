/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.resturant.models;

/**
 *
 * @author anderson
 */
import java.sql.Timestamp;

public class Reservation {
    private int reservationId;
    private int tableId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private Timestamp reservationTime;
    private int partySize;
    private String status;
    private String specialRequests;

    // Default constructor
    public Reservation() {}

    // Full constructor
    public Reservation(int reservationId, int tableId, String customerName, String customerPhone,
                       String customerEmail, Timestamp reservationTime, int partySize,
                       String status, String specialRequests) {
        this.reservationId = reservationId;
        this.tableId = tableId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.reservationTime = reservationTime;
        this.partySize = partySize;
        this.status = status;
        this.specialRequests = specialRequests;
    }

    // Getters and Setters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }
}

