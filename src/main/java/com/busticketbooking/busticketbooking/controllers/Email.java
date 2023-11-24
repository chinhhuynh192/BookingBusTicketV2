/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.busticketbooking.busticketbooking.controllers;

import com.busticketbooking.busticketbooking.Utils.ConvertUtil;
import com.busticketbooking.busticketbooking.Utils.EmailService;
import com.busticketbooking.busticketbooking.dao.BookingDao;
import com.busticketbooking.busticketbooking.dao.PaymentDao;
import com.busticketbooking.busticketbooking.dao.impl.BookingDaoImpl;
import com.busticketbooking.busticketbooking.dao.impl.PaymentDaoImpl;
import com.busticketbooking.busticketbooking.endpoint.UserBookingEndpoint;
import com.busticketbooking.busticketbooking.models.Booking;
import com.busticketbooking.busticketbooking.models.Payment;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Email", urlPatterns = {"/send-ticket"})
public class Email extends HttpServlet {

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

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Booking booking = (Booking)request.getSession().getAttribute("bookingPayment");
        
        if (booking != null) {
            try {
            booking.setBookingStatus("confirmed");
            BookingDao bookingDao = new BookingDaoImpl();
                bookingDao.update(booking);
            PaymentDao paymentDao = new PaymentDaoImpl();
            Payment payment = new Payment();
            payment.setPaymentId(paymentDao.getLastestId() + 1);
            payment.setBookingId(booking.getBookingId());
            payment.setAmount(booking.getPrice() * (1- booking.getDiscount()));
            payment.setPaymentDate(ConvertUtil.getDateNow());
            payment.setUserId(booking.getUserId());
            paymentDao.insertPayment(payment);
            UserBookingEndpoint.sendBookingUpdate(String.valueOf(booking.getBookingId()), "Thanh toán thành công, vui lòng kiểm tra mail. Nếu có thắc mắc vui lòng liên hệ hotline 19001580");
            Booking customerBooking = bookingDao.getLastestBooking(booking.getBookingId());
            sendTicketMail(customerBooking);
            } catch (Exception ex) {
                Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getSession().removeAttribute("bookingPayment");
             response.sendRedirect("/");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void sendTicketMail(Booking booking) throws AddressException, MessagingException {
        StringBuilder content = new StringBuilder("<html><body><p>Cảm ơn bạn đã đặt vé tại DN Bus, nếu có bất kỳ thắc mắc nào có thể liên hệ với hotline 19001580</p></br>");
        List<String> allSeatNumber = ConvertUtil.castStringListToList(booking.getSeatNumber());
        for (String seatNumber: allSeatNumber) {
            content.append(ConvertUtil.htmlTicketTemplate(booking, seatNumber, booking.getTrip().getRoute().getFare() * (1 - booking.getDiscount())));
        }
        String subject = "Đặt vé xe tại DB Bus";
        EmailService.sendEmail(host, port, user, pass, booking.getUser().getEmail(), subject, content.toString());
    }
}
