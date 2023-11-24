package com.busticketbooking.busticketbooking.controllers;

import com.busticketbooking.busticketbooking.dao.UserDao;
import com.busticketbooking.busticketbooking.dao.impl.UserDaoImpl;
import com.busticketbooking.busticketbooking.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ActiveAccount", urlPatterns = "/verify")
public class ActiveAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {

            String email = request.getParameter("email");
            String hash = request.getParameter("code");
            UserDao userDao = new UserDaoImpl();
            User user = userDao.selectByMail(email);
            if(user == null){
                response.getWriter().write("Invalid email");
                return;
            }
            if(!user.getCode().equals(hash)){
                response.getWriter().write("Invalid code");
                return;
            }
            user.setVerify(true);
            userDao.updateUser(user);
            request.getSession().setAttribute("msgActive", "Xác thực thành công, vui lòng đăng nhập để sử dụng dịch vụ!" );
            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            System.out.println("Error at ActivateAccount.java: " + e);
            response.sendRedirect("index.jsp");
        }
    }

}
