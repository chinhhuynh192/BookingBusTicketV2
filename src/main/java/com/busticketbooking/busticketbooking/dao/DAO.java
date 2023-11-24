/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busticketbooking.busticketbooking.dao;

import com.busticketbooking.busticketbooking.dao.impl.DBContext;
import com.busticketbooking.busticketbooking.models.Account;
import com.busticketbooking.busticketbooking.models.Route;
import com.busticketbooking.busticketbooking.models.Ticket;
import com.busticketbooking.busticketbooking.models.Trip;
import com.busticketbooking.busticketbooking.models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

/**
 *
 * @author Admin
 */
public class DAO {
    Connection conn = null;
    PreparedStatement ps  = null;
    ResultSet rs = null;
    public Account checkLogin(String email, String password){
        String query = "SELECT * FROM Users WHERE email = ? AND password = ?";
        
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while(rs.next()){
              return new Account(rs.getInt(1),
                         rs.getString(2),
                         rs.getString(3),
                         rs.getString(4),
                         rs.getString(5),
                         rs.getString(6),
                         rs.getString(7),
                         rs.getString(8),
                         rs.getString(9),
                         rs.getInt(10), rs.getBoolean(11));
            }
        } catch (Exception e) {
        }
        return null;
    }
    public Account checkExistAccount(String email){
        String query = "SELECT * FROM Users WHERE email = ? ";
         try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while(rs.next()){
               return new Account(rs.getInt(1),
                         rs.getString(2),
                         rs.getString(3),
                         rs.getString(4),
                         rs.getString(5),
                         rs.getString(6),
                         rs.getString(7),
                         rs.getString(8),
                         rs.getString(9),
                         rs.getInt(10), rs.getBoolean(11));
                          
            }
        } catch (Exception e) {
        }
        return null;
    }
    public void signup(String name, String email, String password){
        String query = "INSERT INTO Users (user_id ,name, email, password) \n" +
"VALUES (CONCAT('0123', CAST(RAND()*10 AS INT)), ? , ? , ?);";
        try { 
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
  public void update(String phone, String name, String address, String gender, String image , String email) {
    String query = "UPDATE Users SET phone = ?, name = ?, address = ?, gender = ?, image = ? WHERE email = ?";
  
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, phone);
        ps.setString(2, name);
        ps.setString(3, address);
        ps.setString(4, gender);
        ps.setString(5, image);
        ps.setString(6, email);
        ps.executeUpdate();
    } catch (Exception e) {
         e.printStackTrace();
    }
}
 public void changePassword( String password, String email) {
    String query = "UPDATE Users SET Password = ? WHERE email = ?";
      
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, password);
        ps.setString(2, email);
        ps.executeUpdate();
      
    } catch (Exception e) {
        e.printStackTrace();
    }
}

  public Ticket checkExistTicket(String tripId){
        String query = "SELECT * FROM Trip WHERE trip_id = ? ";
         try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, tripId);
            rs = ps.executeQuery();
            while(rs.next()){
               return new Ticket(rs.getInt(1),
                       rs.getInt(2),
                       rs.getString(3),
                       rs.getString(4),
                       rs.getInt(5),
                       rs.getString(6),
                       rs.getString(7),
                       rs.getString(8),
                       rs.getInt(9));
            }
        } catch (Exception e) {
        }
        return null;
    }
  public List<Route> getAllRoute(){
        List<Route> list = new ArrayList<>();
        
        String query = "select * from Route";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
               list.add(new Route(rs.getInt(1),
                       rs.getString(2),
                       rs.getString(3),
                       rs.getFloat(4),
                       rs.getFloat(5),
                       rs.getFloat(6),
                       rs.getString(7),
                       rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }     
    public static void Route() {
        DAO dao = new DAO();
        List<Route> list = dao.getAllRoute();
        for (Route o : list){
            System.out.println(o);
        }
    }
    
    public List<Route> deleteByRouteID(int id ){
        List<Route> list = new ArrayList<>();
        
        String query = "DELETE FROM Route WHERE route_id=?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
               list.add(new Route(rs.getInt(1),
                       rs.getString(2),
                       rs.getString(3),
                       rs.getFloat(4),
                       rs.getFloat(5),
                       rs.getFloat(6),
                       rs.getString(7),
                       rs.getString(8)));
            }
        } catch (Exception e) {
        }
        return list;
    }     
     public List<Route> editByRouteID(int routeId,String origin, String destination, float distance , float duration, float fare, String vehicle, String status ){
        List<Route> list = new ArrayList<>();
        
        String query = "UPDATE Route SET origin = ? ,  destination = ? , distance = ? , duration = ? , fare = ?,  vehicle_type = ? , route_status= ? WHERE route_id = ?" ;
                        

       try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        ps.setString(1, origin);
        ps.setString(2, destination);
        ps.setFloat(3, distance);
        ps.setFloat(4, duration);
        ps.setFloat(5, fare);
        ps.setString(6, vehicle);
        ps.setString(7, status);
        ps.setInt(8, routeId);
        
        int rowsAffected = ps.executeUpdate();
        
        if (rowsAffected > 0) {
            // Nếu cập nhật thành công, truy vấn lại dữ liệu tuyến đường đã được chỉnh sửa
            String selectQuery = "SELECT * FROM Route WHERE route_id = ?";
            ps = conn.prepareStatement(selectQuery);
            ps.setInt(1, routeId);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new Route(rs.getInt("route"),
                                   rs.getString("origin"),
                                   rs.getString("destination"),
                                   rs.getFloat("distance"),
                                   rs.getFloat("duration"),
                                   rs.getFloat("fare"),
                                   rs.getString("vehicle_type"),
                                   rs.getString("route_status")));
            }
        }
    } catch (Exception e) {
        // Xử lý ngoại lệ (exception) ở đây
    }
    
    return list;
}
    public List<Vehicle> getAllVehicle(){
        List<Vehicle> list = new ArrayList<>();
        
        String query = "select * from Vehicle";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
               list.add(new Vehicle(rs.getInt(1),
                       rs.getString(2),
                       rs.getString(3),
                       rs.getString(4),
                       rs.getInt(5),
                       rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }     
    public static void Vehicle(){
        DAO dao = new DAO();
        List<Vehicle> list = dao.getAllVehicle();
        for (Vehicle o : list){
            System.out.println(o);
        }
    }
    
    public List<Ticket> getAllTrip(){
        List<Ticket> list = new ArrayList<>();
        
        String query = "select * from Trip";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
               list.add(new Ticket(rs.getInt(1),
                       rs.getInt(2),
                       rs.getString(3),
                       rs.getString(4),
                       rs.getInt(5),
                       rs.getString(6),
                       rs.getString(7),
                       rs.getString(8),
                       rs.getInt(9)));
                      
            }
        } catch (Exception e) {
        }
        return list;
    }     
    public static void Trip(){
        DAO dao = new DAO();
        List<Ticket> list = dao.getAllTrip();
        for (Ticket o : list){
            System.out.println(o);
        }
    }
    
}
