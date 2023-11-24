package com.busticketbooking.busticketbooking.controllers;




import com.busticketbooking.busticketbooking.Utils.ConvertUtil;
import com.busticketbooking.busticketbooking.dao.BookingDao;
import com.busticketbooking.busticketbooking.dao.TripDao;
import com.busticketbooking.busticketbooking.dao.impl.BookingDaoImpl;
import com.busticketbooking.busticketbooking.dao.impl.TripDaoImpl;
import com.busticketbooking.busticketbooking.models.Booking;
import com.busticketbooking.busticketbooking.models.Trip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SearchTripServlet", value = "/searchTrip")
public class SearchTrip extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String fromLocation = request.getParameter("fromlocation").trim();
            String toLocation = request.getParameter("tolocation").trim();
            String dateGoStr = request.getParameter("datego");

            Date dateGo = ConvertUtil.convertStringToDate(dateGoStr);

            TripDao tripDao = new TripDaoImpl();
            List<Trip> trips = tripDao.getTripsBySearch(fromLocation, toLocation, dateGo);

            BookingDao bookingDao = new BookingDaoImpl();
            for (int i = 0; i < trips.size(); i++) {
                List<Booking> bookings = bookingDao.getAllBookingByTripId(trips.get(i).getTripId());
                List<Integer> listSeatAlreadyBooking = ConvertUtil.getAllSeatNumbers(bookings);
                int seatLeft = trips.get(i).getQuantity() - listSeatAlreadyBooking.size();
                trips.get(i).setSeatingInformation(String.valueOf(seatLeft));
            }
            request.setAttribute("trips", trips);
            request.setAttribute("fromlocation", fromLocation);
            request.setAttribute("tolocation", toLocation);
            request.setAttribute("datego", dateGo);
            request.getRequestDispatcher("searchresult.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchTrip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
