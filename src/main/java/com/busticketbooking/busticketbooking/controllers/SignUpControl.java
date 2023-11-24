/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.busticketbooking.busticketbooking.controllers;


import com.busticketbooking.busticketbooking.Utils.ConvertUtil;
import com.busticketbooking.busticketbooking.Utils.EmailService;
import com.busticketbooking.busticketbooking.dao.UserDao;
import com.busticketbooking.busticketbooking.dao.impl.UserDaoImpl;
import com.busticketbooking.busticketbooking.models.User;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Admin
 */
@WebServlet(name = "SignUpControl", urlPatterns = {"/signup"})
public class SignUpControl extends HttpServlet {

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
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String repassword = request.getParameter("re-password");


            UserDao userDao = new UserDaoImpl();
            User userFromDb = userDao.selectByMail(email);
            if (userFromDb != null && userFromDb.isVerify()) {
                request.setAttribute("mess3", "Email đã được đăng ký và xác thực");
            } else if (userFromDb != null && !userFromDb.isVerify() && !ConvertUtil.isNullOrEmpty(userFromDb.getCode())) {
                userFromDb.setPassword(password);
                if (!userFromDb.getName().equals(name)) {
                    userFromDb.setName(name);
                    userFromDb.setPassword(password);
                }
                userDao.updateUser(userFromDb);
                request.setAttribute("mess3", "Email đã được đăng ký, vui lòng xác thực");
            } else if (userFromDb != null && !userFromDb.isVerify() && ConvertUtil.isNullOrEmpty(userFromDb.getCode())) {
                userFromDb.setPassword(password);
                if (!userFromDb.getName().equals(name)) {
                    userFromDb.setName(name);
                    userFromDb.setPassword(password);
                }
                String code = ConvertUtil.getHashCode();
                userFromDb.setCode(code);
                userDao.updateUser(userFromDb);
                sendVerifyMail(getUrl(request), email, code);
                request.setAttribute("msgActive", "Đăng ký thành công, vui lòng kiểm tra email để kích hoạt tài khoản");
            } else if (userFromDb == null) {

                User user = new User();
                user.setEmail(email);
                user.setName(name);
                user.setPassword(password);
                int lastUserId = userDao.selectLastId();
                user.setUserId(lastUserId + 1);
                user.setVerify(false);
                String code = ConvertUtil.getHashCode();
                user.setCode(code);
                userDao.insertUserWithHashCode(user);
                sendVerifyMail(getUrl(request), email, code);
                request.setAttribute("msgActive", "Đăng ký thành công, vui lòng kiểm tra email để kích hoạt tài khoản");
            }
            request.setAttribute("email", email);
            request.setAttribute("name", name);
            request.setAttribute("password", password);
            request.setAttribute("repassword", repassword);
            request.getRequestDispatcher("login.jsp").forward(request, response);


        } catch (Exception e) {
            Logger.getLogger(SignUpControl.class.getName()).log(Level.SEVERE, null, e);
        }
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


    private String getUrl(HttpServletRequest request){
        String urlString = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        return urlString;
    }
    public void sendVerifyMail(String url, String email, String hashcode) throws AddressException, MessagingException {
        String content = "<html><body><p>Vui lòng nhấn vào đường link để hoàn thành việc đăng ký tài khoản</p></br>" +
                "<p>Link xác thực: " + url + "/verify?email=" + email + "&code=" + hashcode+"</p></body></html>";
        String subject = "Xác thực đăng ký tài khoản tại DN Bus";
        EmailService.sendEmail(host, port, user, pass, email, subject, content);
    }
}
