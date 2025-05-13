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
import java.util.List;

public class Order {
    private int orderId;
    private int tableId;
    private int userId;
    private String status;
    private Timestamp orderTime;
    private double totalAmount;
    private String notes;
    private List<OrderItem> orderItems;

    // Default constructor
    public Order() {}

    // Full constructor
    public Order(int orderId, int tableId, int userId, String status, Timestamp orderTime,
                 double totalAmount, String notes, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.userId = userId;
        this.status = status;
        this.orderTime = orderTime;
        this.totalAmount = totalAmount;
        this.notes = notes;
        this.orderItems = orderItems;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}

