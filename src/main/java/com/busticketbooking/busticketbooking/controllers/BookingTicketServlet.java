package com.busticketbooking.busticketbooking.controllers;

import com.busticketbooking.busticketbooking.Utils.AttrWrapper;
import com.busticketbooking.busticketbooking.Utils.ConvertUtil;
import com.busticketbooking.busticketbooking.dao.BookingDao;
import com.busticketbooking.busticketbooking.dao.TripDao;
import com.busticketbooking.busticketbooking.dao.UserDao;
import com.busticketbooking.busticketbooking.dao.impl.BookingDaoImpl;
import com.busticketbooking.busticketbooking.dao.impl.TripDaoImpl;
import com.busticketbooking.busticketbooking.dao.impl.UserDaoImpl;
import com.busticketbooking.busticketbooking.endpoint.AdminBookingEndpoint;
import com.busticketbooking.busticketbooking.models.Account;
import com.busticketbooking.busticketbooking.models.Booking;
import com.busticketbooking.busticketbooking.models.Trip;
import com.busticketbooking.busticketbooking.models.User;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "bookingTicket", value = "/booking-ticket")
public class BookingTicketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String tripIdParameter = request.getParameter("tripId");
            int tripId = ConvertUtil.convert(tripIdParameter);
            TripDao tripDao = new TripDaoImpl();
            Trip trip = tripDao.getTripById(tripId);
            BookingDao bookingDao = new BookingDaoImpl();
            List<Booking> bookingList = bookingDao.getAllBookingByTripId(tripId);
            request.setAttribute("trip", trip);
            request.setAttribute("bookingList", bookingList);
            request.getRequestDispatcher("booking.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BookingTicketServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html; charset=UTF-8");
            StringBuilder jsonBuffer = new StringBuilder();
            String line = null;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
            Account acc = (Account) request.getSession().getAttribute("acc");
            if(acc == null){
                response.setStatus(400);
                response.getWriter().write("Bạn cần đăng nhập trước khi đặt ghế!!");
                return;
            }
            // Parse JSON data
            JSONObject jsonObject = new JSONObject(jsonBuffer.toString());
            int tripId = jsonObject.getInt("tripId");
            JSONArray seatNumbersArray = jsonObject.getJSONArray("seatNumbers");
            JSONObject userJson = jsonObject.getJSONObject("user");
            User user = new User();
            user.setName(userJson.getString("name"));
            user.setEmail(userJson.getString("email"));
            user.setPhone(userJson.getString("phone"));
            UserDao userDao = new UserDaoImpl();
            User userFromDb = userDao.selectByMailAndPhone(user.getEmail(), user.getPhone());
            if (userFromDb == null) {
                int lastUserId = userDao.selectLastId();
                user.setUserId(lastUserId + 1);
                userDao.insertUser(user);
            } else {
                user.setUserId(userFromDb.getUserId());
            }
            BookingDao bookingDao = new BookingDaoImpl();
            TripDao tripDao = new TripDaoImpl();
            Trip trip = tripDao.getTripById(tripId);
            java.sql.Date sqlDateNow = new java.sql.Date(System.currentTimeMillis());
            // Process tripId and seat numbers
            List<String> listSeatNumber = new ArrayList<String>();
            for (int i = 0; i < seatNumbersArray.length(); i++) {
                listSeatNumber.add(seatNumbersArray.getString(i));
            }
            String seatNumber = String.join(",", listSeatNumber);
            List<Booking> bookingsExists = bookingDao.getAllBookingHaveSeatNumber(tripId, seatNumber);
            List<String> bookedSeatNumbers = ConvertUtil.getAllSeatNumbers(bookingsExists).stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
            if (!bookingsExists.isEmpty()) {
                List<String> conflictedSeats = listSeatNumber.stream()
                        .filter(bookedSeatNumbers::contains).sorted(String::compareTo)
                        .collect(Collectors.toList());
                String joinedSeatNumbers = String.join(", ", conflictedSeats);
                response.setStatus(400);
                response.getWriter().write("ghế " + joinedSeatNumbers +" đã được đặt bởi khách hàng khác!!");
                return;
            }
            Booking booking = new Booking();
            booking.setUserId(user.getUserId());
            booking.setBookingId(bookingDao.getLastId() + 1);
            booking.setTripId(tripId);
            booking.setSeatNumber(seatNumber);
            booking.setDateBooking(sqlDateNow);
            booking.setDiscount(0.05f);
            booking.setPrice(trip.getRoute().getFare() * listSeatNumber.size());
            booking.setBookingStatus("WaitingPayment");
            bookingDao.insert(booking);
            HttpSession session = request.getSession();
            Booking newestBooking = bookingDao.getLastestBooking(booking.getBookingId());
            AdminBookingEndpoint.broadcastNewBooking(new Gson().toJson(newestBooking));
            session.setAttribute("booking", new AttrWrapper<>(new Gson().toJson(booking), 30 * 60 * 1000));
            session.setMaxInactiveInterval(30 * 60);
            JSONObject jobj = new JSONObject();
            String urlToRedirect = "payment?bookingId=" + booking.getBookingId();
            jobj.put("url", urlToRedirect);
            response.getWriter().write(jobj.toString());
        } catch (Exception ex) {
            Logger.getLogger(BookingTicketServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
