package com.busticketbooking.busticketbooking.dao;

import com.busticketbooking.busticketbooking.models.Route;

import java.sql.SQLException;
import java.util.List;

public interface RouteDao {

    public Route getRouteById(int id) throws SQLException;
    public List<String> getOrigin(String fromLocation) throws SQLException;

    public List<String> getDestination(String toLocation) throws  SQLException;

}
