package com.oceanview.model;

public class Room {
    private int id;
    private String roomNumber;
    private RoomType roomType;
    private String status;
    private int floor;

    public Room() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() { return roomType; }
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }
}
