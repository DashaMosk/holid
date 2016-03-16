package com.epam.entity;

import java.io.Serializable;

public class Booking implements Serializable {

    private Integer roomNumber;
    private RoomCategory category;
    private String phone;
    private String comment;

    public Booking() {
    }

    public Booking(Integer roomNumber, RoomCategory category, String phone, String comment) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.phone = phone;
        this.comment = comment;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public void setCategory(RoomCategory category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Booking booking = (Booking) o;

        if (roomNumber != null ? !roomNumber.equals(booking.roomNumber) : booking.roomNumber != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return roomNumber != null ? roomNumber.hashCode() : 0;
    }
}
