package com.busticketbooking.busticketbooking.controllers;

import com.busticketbooking.busticketbooking.Utils.AttrWrapper;
import com.busticketbooking.busticketbooking.Utils.ConvertUtil;
import com.busticketbooking.busticketbooking.Utils.EmailService;
import com.busticketbooking.busticketbooking.dao.BookingDao;
import com.busticketbooking.busticketbooking.dao.PaymentDao;
import com.busticketbooking.busticketbooking.dao.TripDao;
import com.busticketbooking.busticketbooking.dao.UserDao;
import com.busticketbooking.busticketbooking.dao.impl.BookingDaoImpl;
import com.busticketbooking.busticketbooking.dao.impl.PaymentDaoImpl;
import com.busticketbooking.busticketbooking.dao.impl.TripDaoImpl;
import com.busticketbooking.busticketbooking.dao.impl.UserDaoImpl;
import com.busticketbooking.busticketbooking.endpoint.UserBookingEndpoint;
import com.busticketbooking.busticketbooking.models.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ManageBooking", urlPatterns = "/booking-manage")
public class ManageBooking extends HttpServlet {
    private String host;
    private String port;
    private String user;
    private String pass;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Account a = (Account) request.getSession().getAttribute("acc");
            if(a == null){
                response.sendRedirect("index.jsp");
            }
            if(!a.getRole().equals("admin")){
                response.sendRedirect("index.jsp");
            }
            BookingDao bookingDao = new BookingDaoImpl();
            request.setAttribute("bookings", bookingDao.getAllBooking());
            request.getRequestDispatcher("booking-manage.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageBooking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Account a = (Account) request.getSession().getAttribute("acc");
            if(a == null){
                response.sendRedirect("index.jsp");
            }
            assert a != null;
            if(!a.getRole().equals("admin")){
                response.sendRedirect("index.jsp");
            }
            int bookingId = ConvertUtil.convert(request.getParameter("bookingId"));
            BookingDao bookingDao = new BookingDaoImpl();
            Booking booking = bookingDao.getById(bookingId);
            if(booking == null){
                response.getWriter().write("Error while submit data");
                return;
            }
            booking.setBookingStatus("confirmed");
            bookingDao.update(booking);
            PaymentDao paymentDao = new PaymentDaoImpl();
            Payment payment = new Payment();
            payment.setPaymentId(paymentDao.getLastestId() + 1);
            payment.setBookingId(booking.getBookingId());
            payment.setAmount(booking.getPrice() * (1- booking.getDiscount()));
            payment.setPaymentDate(ConvertUtil.getDateNow());
            payment.setUserId(booking.getUserId());
            paymentDao.insertPayment(payment);
            UserBookingEndpoint.sendBookingUpdate(String.valueOf(bookingId), "Thanh toán thành công, vui lòng kiểm tra mail. Nếu có thắc mắc vui lòng liên hệ hotline 19001580");
            Booking customerBooking = bookingDao.getLastestBooking(bookingId);
            sendTicketMail(customerBooking);
            request.getSession().removeAttribute("booking");
            response.sendRedirect("booking-manage");

        } catch (Exception ex) {
            Logger.getLogger(ManageBooking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendTicketMail(Booking booking) throws AddressException, MessagingException {
        StringBuilder content = new StringBuilder("<html><body><p>Cảm ơn bạn đã đặt vé tại DN Bus, nếu có bất kỳ thắc mắc nào có thể liên hệ với hotline 19001580</p></br>");
        List<String> allSeatNumber = ConvertUtil.castStringListToList(booking.getSeatNumber());
        for (String seatNumber: allSeatNumber) {
            content.append(ConvertUtil.htmlTicketTemplate(booking, seatNumber, booking.getTrip().getRoute().getFare() * (1 - booking.getDiscount())));
        }
        String subject = "Đặt vé xe tại DB Bus";
        EmailService.sendEmail(host, port, user, pass, booking.getUser().getEmail(), subject, content.toString());
    }
}
