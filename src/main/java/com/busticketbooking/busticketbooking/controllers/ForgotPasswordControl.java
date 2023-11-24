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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Admin
 */
@WebServlet(name = "ForgotPasswordControl", urlPatterns = {"/forgot-password"})
public class ForgotPasswordControl extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        try {
            UserDao userDao = new UserDaoImpl();
            String email = request.getParameter("email").trim();
            User user = userDao.selectByMail(email);
            if (user == null) {
                request.setAttribute("mess", "Email không tồn tại");
                request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                String hashcode = ConvertUtil.getHashCode();
                session.setAttribute("hashcode", hashcode);
                sendVerifyMail(getUrl(request), user.getEmail(), hashcode);
                request.setAttribute("mess", "Yêu cầu cấp lại password thành công");
                request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("mess", "Có lỗi trong quá trình xác thực, vui lòng liên hệ admin để được hỗ trợ");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    private String getUrl(HttpServletRequest request) {
        String urlString = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        return urlString;
    }

    public void sendVerifyMail(String url, String email, String hashcode) throws AddressException, MessagingException {
        String content = "<html><body><p>Vui lòng nhấn vào đường link để được cấp lại mật khẩu tài khoản, đường link chỉ có hiệu lực trong 30p</p></br>" +
                "<p>Link xác thực: " + url + "/reset-password?email=" + email + "&code=" + hashcode + "</p></body></html>";
        String subject = "Cấp lại mật khẩu tại DN Bus";
        EmailService.sendEmail(host, port, user, pass, email, subject, content);
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

}
