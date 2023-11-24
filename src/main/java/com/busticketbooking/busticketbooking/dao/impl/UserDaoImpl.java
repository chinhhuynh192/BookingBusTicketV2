package com.busticketbooking.busticketbooking.dao.impl;

import com.busticketbooking.busticketbooking.dao.UserDao;
import com.busticketbooking.busticketbooking.models.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean insertUser(User user) throws SQLException {
        String sqlQuery = "INSERT INTO [dbo].[Users]\n" +
                "           ([user_id]\n" +
                "           ,[phone]\n" +
                "           ,[email]\n" +
                "           ,[name])\n" +
                "     VALUES\n" +
                "           (?\n" +
                "           ,?\n" +
                "           ,?\n" +
                "           ,?)";
        try(Connection connection = DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getPhone());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getName());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean insertUserWithHashCode(User user) throws SQLException {
        String sqlQuery = "INSERT INTO [dbo].[Users]\n" +
                "           ([user_id]\n" +
                "           ,[phone]\n" +
                "           ,[email]\n" +
                "           ,[name],[is_verify], [code], [password])\n" +
                "     VALUES\n" +
                "           (?\n" +
                "           ,?\n" +
                "           ,?\n" +
                "           ,?, ?, ?, ?)";
        try(Connection connection = DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getPhone());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getName());
            statement.setBoolean(5, user.isVerify());
            statement.setString(6, user.getCode());
            statement.setString(7, user.getPassword());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public User selectById(int userId) throws SQLException{
        String sqlQuery = "SELECT [user_id]\n" +
                "      ,[phone]\n" +
                "      ,[password]\n" +
                "      ,[email]\n" +
                "      ,[gender]\n" +
                "      ,[image]\n" +
                "      ,[address]\n" +
                "      ,[role]\n" +
                "      ,[name], [is_verify]\n" +
                "  FROM [dbo].[Users]\n" +
                "  WHERE [user_id] = ?";
        try(Connection connection = DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setPhone(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setGender(rs.getString(5));
                user.setImage(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setRole(rs.getString(8));
                user.setName(rs.getString(9));
                user.setVerify(rs.getBoolean(10));
                return user;
            }
        }
        return null;
    }

    @Override
    public User selectByMailAndPhone(String mail, String phone) throws SQLException {
        String sqlQuery = "SELECT [user_id]\n" +
                "      ,[phone]\n" +
                "      ,[password]\n" +
                "      ,[email]\n" +
                "      ,[gender]\n" +
                "      ,[image]\n" +
                "      ,[address]\n" +
                "      ,[role]\n" +
                "      ,[name], [is_verify]\n" +
                "  FROM [dbo].[Users]\n" +
                "  WHERE [email] = ? AND [phone] = ?";
        try(Connection connection = DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, mail);
            statement.setString(2, phone);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setPhone(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setGender(rs.getString(5));
                user.setImage(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setRole(rs.getString(8));
                user.setName(rs.getString(9));
                user.setVerify(rs.getBoolean(10));
                return user;
            }
        }
        return null;
    }

    @Override
    public int selectLastId() throws SQLException{
        String sqlQuery = "SELECT TOP(1) [user_id]\n" +
                "  FROM [dbo].[Users]\n" +
                "  ORDER BY [user_id] DESC";
        try(Connection connection = DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }
        return 0;
    }

    @Override
    public User selectByMail(String mail) throws SQLException {
        String sqlQuery = "SELECT [user_id]\n" +
                "      ,[phone]\n" +
                "      ,[password]\n" +
                "      ,[email]\n" +
                "      ,[gender]\n" +
                "      ,[image]\n" +
                "      ,[address]\n" +
                "      ,[role]\n" +
                "      ,[name], [is_verify], [code]\n" +
                "  FROM [dbo].[Users]\n" +
                "  WHERE [email] = ?";
        try(Connection connection = DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, mail);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setPhone(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setGender(rs.getString(5));
                user.setImage(rs.getString(6));
                user.setAddress(rs.getString(7));
                user.setRole(rs.getString(8));
                user.setName(rs.getString(9));
                user.setVerify(rs.getBoolean(10));
                user.setCode(rs.getString(11));
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        String sqlQuery = "UPDATE [dbo].[Users]\n" +
                "   SET [phone] = ?\n" +
                "      ,[password] = ?\n" +
                "      ,[gender] = ?\n" +
                "      ,[image] = ?\n" +
                "      ,[address] = ?\n" +
                "      ,[role] = ?\n" +
                "      ,[name] = ?\n" +
                "      ,[is_verify] = ?\n" +
                " WHERE [user_id] = ?";
        try(Connection connection =  DBContext.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, String.valueOf(user.getPhone()));
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getGender());
            statement.setString(4, user.getImage());
            statement.setString(5, user.getAddress());
            statement.setString(6, user.getRole());
            statement.setString(7, user.getName());
            statement.setBoolean(8, user.isVerify());
            statement.setInt(9, user.getUserId());
            return statement.executeUpdate() > 0;
        }
    }
    
}
