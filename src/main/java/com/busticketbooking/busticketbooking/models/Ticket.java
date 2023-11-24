/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busticketbooking.busticketbooking.models;

/**
 *
 * @author Admin
 */
public class Ticket {
 
    private int tripId;
    private int routeId;
    private String date;
    private String time;
    private int quantity;
    private String seating;
    private String passenger;
    private String status;
    private int vehicleId;
    
    public Ticket(){}

    public Ticket(int tripId, int routeId, String date, String time, int quantity, String seating, String passenger, String status, int vehicleId) {
        this.tripId = tripId;
        this.routeId = routeId;
        this.date = date;
        this.time = time;
        this.quantity = quantity;
        this.seating = seating;
        this.passenger = passenger;
        this.status = status;
        this.vehicleId = vehicleId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSeating() {
        return seating;
    }

    public void setSeating(String seating) {
        this.seating = seating;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String toString() {
        return "Trip{" + "tripId=" + tripId + ", routeId=" + routeId + ", date=" + date + ", time=" + time + ", quantity=" + quantity + ", seating=" + seating + ", passenger=" + passenger + ", status=" + status + ", vehicleId=" + vehicleId + '}';
    }

}


