package com.busticketbooking.busticketbooking.models;

import java.sql.Date;

public class Booking {
    private int bookingId;
    private int userId;
    private int tripId;
    private Date dateBooking;
    private String seatNumber;
    private float price;
    private float discount;
    private String bookingStatus;

    private User user;

    private Trip trip;
    public Booking() {
    }

    public Booking(int bookingId, int userId, int tripId, Date dateBooking, String seatNumber, float price, float discount, String bookingStatus) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.tripId = tripId;
        this.dateBooking = dateBooking;
        this.seatNumber = seatNumber;
        this.price = price;
        this.discount = discount;
        this.bookingStatus = bookingStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public Date getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(Date dateBooking) {
        this.dateBooking = dateBooking;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
