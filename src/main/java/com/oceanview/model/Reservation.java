package com.oceanview.model;

import java.util.Date;

public class Reservation {
    private int id;
    private String reservationNumber;
    private Guest guest;
    private Room room;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;
    private double totalAmount;

    public Reservation() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getReservationNumber() { return reservationNumber; }
    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public Date getCheckInDate() { return checkInDate; }
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
