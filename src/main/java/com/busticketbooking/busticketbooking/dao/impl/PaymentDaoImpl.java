package com.busticketbooking.busticketbooking.dao.impl;

import com.busticketbooking.busticketbooking.dao.PaymentDao;
import com.busticketbooking.busticketbooking.models.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public boolean insertPayment(Payment payment) throws SQLException {
        String sqlQuery  = "INSERT INTO [dbo].[Payment]\n" +
                "           ([payment_id]\n" +
                "           ,[booking_id]\n" +
                "           ,[amount]\n" +
                "           ,[payment_date]\n" +
                "           ,[user_id])\n" +
                "     VALUES\n" +
                "           (?\n" +
                "           ,?\n" +
                "           ,?\n" +
                "           ,?\n" +
                "           ,?)";
        try(Connection connection = DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, payment.getPaymentId());
            statement.setInt(2, payment.getBookingId());
            statement.setFloat(3, payment.getAmount());
            statement.setDate(4, payment.getPaymentDate());
            statement.setInt(5, payment.getUserId());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public int getLastestId() throws SQLException {
        String sqlQuery = "SELECT TOP (1) [payment_id]\n" +
                "  FROM [BusTicketBooking].[dbo].[Payment] ORDER BY [payment_id] DESC";
        try(Connection connection = DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }
        return 0;
    }
}
