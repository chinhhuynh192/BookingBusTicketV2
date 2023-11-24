package com.busticketbooking.busticketbooking.models;

public class Route {
    private int routeId;
    private String origin;
    private String destination;
    private float distance;
    private float duration;
    private float fare;
    private String vehicleType;
    private String routeStatus;
    private Vehicle vehicle;
    public Route() {
    }

    public Route(int routeId, String origin, String destination, float distance, float duration, float fare, String vehicleType, String routeStatus, Vehicle vehicle) {
        this.routeId = routeId;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.fare = fare;
        this.vehicleType = vehicleType;
        this.routeStatus = routeStatus;
        this.vehicle = vehicle;
    }
    public Route(int routeId, String origin, String destination, float distance, float duration, float fare, String vehicleType, String routeStatus) {
        this.routeId = routeId;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.fare = fare;
        this.vehicleType = vehicleType;
        this.routeStatus = routeStatus;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRouteStatus() {
        return routeStatus;
    }

    public void setRouteStatus(String routeStatus) {
        this.routeStatus = routeStatus;
    }
}
