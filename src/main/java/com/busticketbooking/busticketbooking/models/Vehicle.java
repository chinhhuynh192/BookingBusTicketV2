package com.busticketbooking.busticketbooking.models;

public class Vehicle {
    private int vehicleId;
    private String vehicleType;
    private  String imageVehicle;
    private String licensePlate;
    private int capacity;
    private String vehicleStatus;

    public Vehicle() {
    }

    public Vehicle(int vehicleId, String vehicleType, String imageVehicle, String licensePlate, int capacity, String vehicleStatus) {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.imageVehicle = imageVehicle;
        this.licensePlate = licensePlate;
        this.capacity = capacity;
        this.vehicleStatus = vehicleStatus;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getImageVehicle() {
        return imageVehicle;
    }

    public void setImageVehicle(String imageVehicle) {
        this.imageVehicle = imageVehicle;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }
}
