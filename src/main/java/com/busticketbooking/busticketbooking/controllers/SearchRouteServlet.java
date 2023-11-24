/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.busticketbooking.busticketbooking.controllers;



import com.busticketbooking.busticketbooking.models.Route;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nuocm
 */
@WebServlet(name = "SearchRouteServlet", urlPatterns = {"/search"})
public class SearchRouteServlet extends HttpServlet {
 private final String SERVER = "DESKTOP-C05LOHE\\MSSQLSERVER02";
    private final String DB_NAME = "BusTicketBooking";
    private final String USERNAME = "sa";
    private final String PASSWORD = "sa";
    private final String URL = "jdbc:sqlserver://" + SERVER + ":1433;database=" + DB_NAME + ";encrypt=true;trustServerCertificate=true;";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String searchQuery = request.getParameter("search");
            if (searchQuery != null && !searchQuery.isEmpty()) {
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Route WHERE origin LIKE ? OR origin LIKE ? OR origin LIKE ?");
statement.setString(1, "%" + searchQuery + "%");
statement.setString(2, "%" + searchQuery + " %");
statement.setString(3, "%" + " " + searchQuery + "%");
                ResultSet resultSet = statement.executeQuery();

                List<Route> workers = new ArrayList<>();
                while (resultSet.next()) {
                    Route route = new Route();
                route.setRouteId(resultSet.getInt("route_id"));
                route.setOrigin(resultSet.getString("origin"));
                route.setDestination(resultSet.getString("destination"));
                route.setDistance(resultSet.getFloat("distance"));
                route.setDuration(resultSet.getFloat("duration"));
                route.setFare(resultSet.getFloat("fare"));
                route.setVehicleType(resultSet.getString("vehicle_type"));
                route.setRouteStatus(resultSet.getString("route_status"));

                workers.add(route);
                }
                 resultSet.close();
            statement.close();
            connection.close();

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");

               
            PrintWriter out = response.getWriter();

            out.println("<table style='width: 100%; border-collapse: collapse;'>");
out.println("<tr><th style='background-color: #333; color: #fff; font-weight: bold; padding: 10px; text-align: left;'>Route ID</th><th style='background-color: #333; color: #fff; font-weight: bold; padding: 10px; text-align: left;'>Origin</th><th style='background-color: #333; color: #fff; font-weight: bold; padding: 10px; text-align: left;'>Destination</th><th style='background-color: #333; color: #fff; font-weight: bold; padding: 10px; text-align: left;'>Distance</th><th style='background-color: #333; color: #fff; font-weight: bold; padding: 10px; text-align: left;'>Duration</th><th style='background-color: #333; color: #fff; font-weight: bold; padding: 10px; text-align: left;'>Fare</th><th style='background-color: #333; color: #fff; font-weight: bold; padding: 10px; text-align: left;'>Vehicle Type</th><th style='background-color: #333; color: #fff; font-weight: bold; padding: 10px; text-align: left;'>Route Status</th></tr>");

for (Route route : workers) {
    out.println("<tr>");
    out.println("<td style='border: 1px solid #ccc; padding: 10px;'>" + route.getRouteId() + "</td>");
    out.println("<td style='border: 1px solid #ccc; padding: 10px;'>" + route.getOrigin() + "</td>");
    out.println("<td style='border: 1px solid #ccc; padding: 10px;'>" + route.getDestination() + "</td>");
    out.println("<td style='border: 1px solid #ccc; padding: 10px;'>" + route.getDistance() + "</td>");
    out.println("<td style='border: 1px solid #ccc; padding: 10px;'>" + route.getDuration() + "</td>");
    out.println("<td style='border: 1px solid #ccc; padding: 10px;'>" + route.getFare() + "</td>");
    out.println("<td style='border: 1px solid #ccc; padding: 10px;'>" + route.getVehicleType() + "</td>");
    out.println("<td style='border: 1px solid #ccc; padding: 10px;'>" + route.getRouteStatus() + "</td>");
    out.println("</tr>");
}
out.println("</table>");

            out.close();
        } 
    } catch (SQLException ex) {
         Logger.getLogger(SearchRouteServlet.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(SearchRouteServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}
