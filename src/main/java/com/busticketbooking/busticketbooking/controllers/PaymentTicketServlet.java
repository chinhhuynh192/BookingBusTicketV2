package com.busticketbooking.busticketbooking.controllers;

import com.busticketbooking.busticketbooking.Utils.AttrWrapper;
import com.busticketbooking.busticketbooking.Utils.ConvertUtil;
import com.busticketbooking.busticketbooking.dao.BookingDao;
import com.busticketbooking.busticketbooking.dao.TripDao;
import com.busticketbooking.busticketbooking.dao.UserDao;
import com.busticketbooking.busticketbooking.dao.impl.BookingDaoImpl;
import com.busticketbooking.busticketbooking.dao.impl.TripDaoImpl;
import com.busticketbooking.busticketbooking.dao.impl.UserDaoImpl;
import com.busticketbooking.busticketbooking.models.Booking;
import com.busticketbooking.busticketbooking.models.Trip;
import com.busticketbooking.busticketbooking.models.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "paymentTicket", value = "/payment")
public class PaymentTicketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String bookingIdPara = request.getParameter("bookingId");
            int bookingId = ConvertUtil.convert(bookingIdPara);
            BookingDao bookingDao = new BookingDaoImpl();
            Booking booking = bookingDao.getById(bookingId);
            if(booking == null){
                response.getWriter().write("Không tìm thấy chuyến đi cần đặt");
                return;
            }
            if(ConvertUtil.getAttr("booking", request.getSession(false)) == null){
                response.getWriter().write("Hết hạn đặt chỗ, vui lòng đặt lại");
                return;
            }
            else{
                AttrWrapper<?> attr = (AttrWrapper<?>) request.getSession(false).getAttribute("booking");
                long now = System.currentTimeMillis();
                long createdTime = attr.getCreatedTime();
                long timeLeft = attr.getTimeoutMs() - (now - createdTime);
                request.setAttribute("timeLeft", timeLeft);
            }
            request.setAttribute("booking", booking);
            UserDao userDao = new UserDaoImpl();
            User user = userDao.selectById(booking.getUserId());
            TripDao tripDao = new TripDaoImpl();
            Trip trip = tripDao.getTripById(booking.getTripId());
            request.setAttribute("trip", trip);
            request.setAttribute("user", user);
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(BookingTicketServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
