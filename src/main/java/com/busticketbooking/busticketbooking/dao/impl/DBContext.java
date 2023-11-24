package com.busticketbooking.busticketbooking.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=BusTicketBooking;trustServerCertificate=true;";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "123";

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        DBContext db= new DBContext();
        System.out.println(db.getConnection());
    }
}

