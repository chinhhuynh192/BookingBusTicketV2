package com.busticketbooking.busticketbooking.dao.impl;

import com.busticketbooking.busticketbooking.dao.RouteDao;
import com.busticketbooking.busticketbooking.models.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    @Override
    public Route getRouteById(int id) throws SQLException {
        String sqlQuery = "SELECT [route_id]\n" +
                "      ,[origin]\n" +
                "      ,[destination]\n" +
                "      ,[distance]\n" +
                "      ,[duration]\n" +
                "      ,[fare]\n" +
                "      ,[vehicle_type]\n" +
                "      ,[route_status]\n" +
                "  FROM [dbo].[Route]\n" +
                "  WHERE [route_id] = ?";
        try(Connection connection = DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(!rs.next()){
                return null;
            }
            int routeId = rs.getInt(1);
            String origin = rs.getString(2);
            String destination = rs.getString(3);
            float distance = rs.getFloat(4);
            float duration = rs.getFloat(5);
            float fare = rs.getFloat(6);
            String vehicleType = rs.getString(7);
            String routeStatus = rs.getString(8);
            return new Route(routeId, origin, destination, distance, duration, fare, vehicleType, routeStatus);
        }
    }

    @Override
    public List<String> getOrigin(String fromLocation) throws SQLException {
        List<String> listOrigin = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT  [origin]\n" +
                "  FROM [BusTicketBooking].[dbo].[Route]\n" +
                "  WHERE [origin] LIKE '%" + fromLocation + "%'";
        try(Connection connection = DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                listOrigin.add(rs.getString(1));
            }
        }
        return  listOrigin;
    }

    @Override
    public List<String> getDestination(String toLocation) throws SQLException {
        List<String> listDestination = new ArrayList<>();
        String sqlQuery = "SELECT DISTINCT [destination]\n" +
                "  FROM [BusTicketBooking].[dbo].[Route]\n" +
                "  WHERE [destination] LIKE '%" + toLocation + "%'";
        try(Connection connection = DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                listDestination.add(rs.getString(1));
            }
        }
        return  listDestination;
    }
}
