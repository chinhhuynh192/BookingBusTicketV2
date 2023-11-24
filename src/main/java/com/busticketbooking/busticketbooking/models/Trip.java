package com.busticketbooking.busticketbooking.models;

import java.sql.Date;
import java.sql.Time;

public class Trip {
    private int tripId;
    private Date date;
    private Time time;
    private int quantity;
    private String seatingInformation;
    private String passengerList;
    private String tripStatus;
    private int routeId;
    private Route route;
    private int vehicleId;
    private Vehicle vehicle;

    public Trip() {
    }

    public Trip(int tripId, Date date, Time time, int quantity, String seatingInformation, String passengerList, String tripStatus, int routeId, Route route, int vehicleId, Vehicle vehicle) {
        this.tripId = tripId;
        this.date = date;
        this.time = time;
        this.quantity = quantity;
        this.seatingInformation = seatingInformation;
        this.passengerList = passengerList;
        this.tripStatus = tripStatus;
        this.routeId = routeId;
        this.route = route;
        this.vehicleId = vehicleId;
        this.vehicle = vehicle;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSeatingInformation() {
        return seatingInformation;
    }

    public void setSeatingInformation(String seatingInformation) {
        this.seatingInformation = seatingInformation;
    }

    public String getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(String passengerList) {
        this.passengerList = passengerList;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
