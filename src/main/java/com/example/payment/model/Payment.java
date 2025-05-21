package com.example.payment.model;

import java.io.Serializable;

public class Payment implements Serializable {
    private String id;
    private String studentName;
    private double amount;
    private String date;
    private String status;

    public Payment(String id, String studentName, double amount, String date, String status) {
        this.id = id;
        this.studentName = studentName;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getStudentName() { return studentName; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    public String getStatus() { return status; }

    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setDate(String date) { this.date = date; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return id + "," + studentName + "," + amount + "," + date + "," + status;
    }

    public static Payment fromString(String str) {
        String[] parts = str.split(",");
        return new Payment(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3], parts[4]);
    }
}