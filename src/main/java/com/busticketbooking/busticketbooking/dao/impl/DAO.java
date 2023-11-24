/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.busticketbooking.busticketbooking.dao.impl;


import com.busticketbooking.busticketbooking.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
//    public static List<Account> list = new ArrayList<>();
//    public int updateAccount(Account account){
//        for (int i=0; i<list.size(); i++){
//            if(list.get(i).getPhone().equals(account.getPhone())){
//                list.set(i, account);
//                return i;
//            }
//        }
//        return -1;
//    }
//    public int save(Account account){
//        list.add(account);
//        return 1;
//    } 
//    public int delete(String phone){
//        Account account = checkExistAccount(phone);
//        if (account != null) {
//            list.remove(account);
//            return 1;
//        }
//        return 0;
//    }
}
