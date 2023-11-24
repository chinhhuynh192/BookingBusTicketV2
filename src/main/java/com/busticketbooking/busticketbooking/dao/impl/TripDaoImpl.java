package com.busticketbooking.busticketbooking.dao.impl;

import com.busticketbooking.busticketbooking.dao.TripDao;
import com.busticketbooking.busticketbooking.models.Route;
import com.busticketbooking.busticketbooking.models.Trip;
import com.busticketbooking.busticketbooking.models.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripDaoImpl implements TripDao {
    @Override
    public Trip getTripById(int id) throws SQLException {
        String sqlQuery = "SELECT \n" +
                "    Trip.trip_id, Trip.route_id AS routeId, Trip.vehicle_id AS vehicleId, Trip.date, Trip.time, Trip.quantity, Trip.seating_information, Trip.passenger_list, Trip.trip_status,\n" +
                "    Route.origin, Route.destination, Route.distance, Route.duration, Route.fare, Route.vehicle_type, Route.route_status,\n" +
                "    Vehicle.vehicle_type AS vehicleType, Vehicle.image_vehicle, Vehicle.license_plate, Vehicle.capacity, Vehicle.vehicle_status\n" +
                "FROM Trip \n" +
                "INNER JOIN Route ON Trip.route_id = Route.route_id\n" +
                "INNER JOIN Vehicle ON Trip.vehicle_id = Vehicle.vehicle_id\n" +
                "WHERE Trip.trip_id = ?\n";
        try(Connection connection = DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()){
                return null;
            }
            int tripId = rs.getInt(1);
            int routeId = rs.getInt(2);
            int vehicleId = rs.getInt(3);
            Date date = rs.getDate(4);
            Time time = rs.getTime(5);
            int quantity = rs.getInt(6);
            String seatingInformation = rs.getString(7);
            String passengerList = rs.getString(8);
            String tripStatus = rs.getString(9);
            String origin = rs.getString(10);
            String destination = rs.getString(11);
            float distance = rs.getFloat(12);
            float duration = rs.getFloat(13);
            float fare = rs.getFloat(14);
            String vehicleType = rs.getString(15);
            String routeStatus = rs.getString(16);
            String imageVehicle = rs.getString(18);
            String licensePlate = rs.getString(19);
            int capacity = rs.getInt(20);
            String vehicleStatus = rs.getString(21);
            Vehicle vehicle = new Vehicle(vehicleId, vehicleType, imageVehicle, licensePlate, capacity, vehicleStatus);
            Route route = new Route(routeId, origin, destination, distance, duration, fare, vehicleType, routeStatus);
            return new Trip(tripId, date, time, quantity, seatingInformation, passengerList, tripStatus, routeId, route, vehicleId, vehicle);
        }


    }

    @Override
    public List<Trip> getTripsBySearch(String fromLocation, String toLocation, Date dateGo) throws SQLException {
        List<Trip> trips = new ArrayList<>();
        String sqlQuery = "SELECT \n" +
                "    r.route_id, r.origin, r.destination, r.distance, r.duration, r.fare, r.vehicle_type as route_vehicle_type, r.route_status,\n" +
                "    t.trip_id, t.route_id as trip_route_id, t.date, t.time, t.quantity, t.seating_information, t.passenger_list, t.trip_status, t.vehicle_id as trip_vehicle_id,\n" +
                "    v.vehicle_id as vehicle_vehicle_id, v.vehicle_type as vehicle_vehicle_type, v.image_vehicle, v.license_plate, v.capacity, v.vehicle_status\n" +
                "FROM \n" +
                "    Route r\n" +
                "JOIN \n" +
                "    Trip t ON r.route_id = t.route_id\n" +
                "JOIN \n" +
                "    Vehicle v ON t.vehicle_id = v.vehicle_id\n" +
                "WHERE r.Origin = ? AND r.destination = ? AND t.date = ? AND v.vehicle_status = ? AND t.trip_status = ? AND r.route_status = ?";
        try(Connection connection = DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, fromLocation);
            statement.setString(2, toLocation);
            statement.setDate(3, dateGo);
            statement.setString(4, "confirmed");
            statement.setString(5, "confirmed");
            statement.setString(6, "confirmed");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Route route = new Route();
                route.setRouteId(rs.getInt(1));
                route.setOrigin(rs.getString(2));
                route.setDestination(rs.getString(3));
                route.setDistance(rs.getFloat(4));
                route.setDuration(rs.getFloat(5));
                route.setFare(rs.getFloat(6));
                route.setVehicleType(rs.getString(7));
                route.setRouteStatus(rs.getString(8));
                Trip trip = new Trip();
                trip.setTripId(rs.getInt(9));
                trip.setDate(rs.getDate(11));
                trip.setTime(rs.getTime(12));
                trip.setQuantity(rs.getInt(13));
                trip.setSeatingInformation(rs.getString(14));
                trip.setPassengerList(rs.getString(15));
                trip.setTripStatus(rs.getString(16));
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getInt(18));
                vehicle.setVehicleType(rs.getString(19));
                vehicle.setImageVehicle(rs.getString(20));
                vehicle.setLicensePlate(rs.getString(21));
                vehicle.setCapacity(rs.getInt(22));
                vehicle.setVehicleStatus(rs.getString(23));
                route.setVehicle(vehicle);
                trip.setRoute(route);
                trips.add(trip);
            }
        }
        return trips;
    }
}
