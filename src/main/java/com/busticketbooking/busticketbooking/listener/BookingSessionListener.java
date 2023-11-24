package com.busticketbooking.busticketbooking.listener;

import com.busticketbooking.busticketbooking.Utils.AttrWrapper;
import com.busticketbooking.busticketbooking.Utils.ConvertUtil;
import com.busticketbooking.busticketbooking.controllers.BookingTicketServlet;
import com.busticketbooking.busticketbooking.dao.BookingDao;
import com.busticketbooking.busticketbooking.dao.impl.BookingDaoImpl;
import com.busticketbooking.busticketbooking.endpoint.AdminBookingEndpoint;
import com.busticketbooking.busticketbooking.models.Booking;
import com.google.gson.Gson;

import javax.servlet.http.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingSessionListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String attributeName = event.getName();
        if(attributeName.equals("booking")){
            AttrWrapper<?> attr = (AttrWrapper<?>) event.getValue();
            System.out.println("Attribute added : " + attributeName + " at " + attr.getCreatedTime());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        String attributeName = event.getName();
        if(attributeName.equals("booking")){
            AttrWrapper<String> attr = (AttrWrapper<String>) event.getValue();
            Booking booking = new Gson().fromJson(attr.getValue(), Booking.class);
            booking.setBookingStatus("Cancel");
            BookingDao bookingDao = new BookingDaoImpl();
            try {
                Booking bookingFromDb = bookingDao.getById(booking.getBookingId());
                if(!bookingFromDb.getBookingStatus().equals("confirmed")){
                    bookingDao.update(booking);
                    AdminBookingEndpoint.broadcastNewBooking(new Gson().toJson(bookingFromDb));
                }
            } catch (Exception ex){
                Logger.getLogger(BookingTicketServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        String attributeName = event.getName();
        Object attributeValue = event.getValue();
        System.out.println("Attribute replaced : " + attributeName + " : " + attributeValue);
    }
}
