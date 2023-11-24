package com.busticketbooking.busticketbooking.dao;

import com.busticketbooking.busticketbooking.models.Trip;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface TripDao {

    public Trip getTripById(int id) throws SQLException;
    public List<Trip> getTripsBySearch(String fromLocation, String toLocation, Date dateGo) throws SQLException;

}
