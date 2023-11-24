package com.busticketbooking.busticketbooking.dao;

import com.busticketbooking.busticketbooking.models.Payment;

import java.sql.SQLException;

public interface PaymentDao {
    public boolean insertPayment(Payment payment) throws SQLException;

    public int getLastestId() throws SQLException;

}
