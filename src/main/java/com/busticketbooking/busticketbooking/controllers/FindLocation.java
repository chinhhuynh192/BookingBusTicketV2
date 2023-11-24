package com.busticketbooking.busticketbooking.controllers;




import com.busticketbooking.busticketbooking.Utils.ConvertUtil;
import com.busticketbooking.busticketbooking.dao.BookingDao;
import com.busticketbooking.busticketbooking.dao.RouteDao;
import com.busticketbooking.busticketbooking.dao.TripDao;
import com.busticketbooking.busticketbooking.dao.impl.BookingDaoImpl;
import com.busticketbooking.busticketbooking.dao.impl.RouteDaoImpl;
import com.busticketbooking.busticketbooking.dao.impl.TripDaoImpl;
import com.busticketbooking.busticketbooking.models.Booking;
import com.busticketbooking.busticketbooking.models.Trip;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "FindLocationServlet", value = "/findlocation")
public class FindLocation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String fromLocation = "";
            String toLocation = "";
            if(request.getParameter("fromLocation") !=null){
                fromLocation = request.getParameter("fromLocation").trim();
            }
            if(request.getParameter("toLocation") != null ){
                toLocation = request.getParameter("toLocation").trim();
            }

            RouteDao routeDao = new RouteDaoImpl();
            response.setContentType("application/json");
            OutputStream outputStream= response.getOutputStream();
            if(!fromLocation.equals("")){
                List<String> result = routeDao.getOrigin(fromLocation);
                Gson jsonObject = new Gson();
                outputStream.write(jsonObject.toJson(result).getBytes());
            }
            if(!toLocation.equals("")){
                List<String> result = routeDao.getDestination(toLocation);
                Gson jsonObject = new Gson();
                outputStream.write(jsonObject.toJson(result).getBytes());
            }
        } catch (Exception ex) {
            Logger.getLogger(SearchTrip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
