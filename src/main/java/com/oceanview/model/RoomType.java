package com.oceanview.model;

public class RoomType {
    private int id;
    private String typeName;
    private double pricePerNight;
    private String description;

    public RoomType() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }
}
