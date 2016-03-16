package com.epam.entity;

// Room's category as enum type
public enum RoomCategory {

    STANDARD("Standard"),
    SUPERIOR("Superior"),
    DELUXE("Deluxe"),
    JUNIOR("Junior"),
    EXECUTIVE_SUITE("Executive Suite");

    private String label;

    private RoomCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
