package com.oceanview.model;

public class Bill {
    private int id;
    private Reservation reservation;
    private int nights;
    private double roomCharge;
    private double taxAmount;
    private double totalAmount;
    private String paymentStatus;

    public Bill() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Reservation getReservation() { return reservation; }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public int getNights() { return nights; }
    public void setNights(int nights) { this.nights = nights; }

    public double getRoomCharge() { return roomCharge; }
    public void setRoomCharge(double roomCharge) {
        this.roomCharge = roomCharge;
    }

    public double getTaxAmount() { return taxAmount; }
    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
