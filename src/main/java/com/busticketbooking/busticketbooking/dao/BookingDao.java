package com.busticketbooking.busticketbooking.dao;

import com.busticketbooking.busticketbooking.models.Booking;

import java.sql.SQLException;
import java.util.List;

public interface BookingDao {
    public List<Booking> getAllBookingByTripId(int tripId) throws SQLException;

    public List<Booking> getAllBookingHaveSeatNumber(int tripId, String seatNumberProvide) throws SQLException;
    public boolean insert(Booking booking) throws SQLException;

    public Booking getById(int bookingId) throws SQLException;
    public int getLastId() throws SQLException;

    public boolean update(Booking booking) throws SQLException;

    public List<Booking> getAllBooking() throws SQLException;

    public Booking getLastestBooking(int bookingId) throws SQLException;
    
    public Booking updateStatus(int bookingId) throws SQLException;
}
