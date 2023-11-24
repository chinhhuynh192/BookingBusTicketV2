package com.busticketbooking.busticketbooking.controllers;

import com.busticketbooking.busticketbooking.Utils.ConvertUtil;
import com.busticketbooking.busticketbooking.Utils.EmailService;
import com.busticketbooking.busticketbooking.dao.UserDao;
import com.busticketbooking.busticketbooking.dao.impl.UserDaoImpl;
import com.busticketbooking.busticketbooking.models.User;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ResetPasswordControl", urlPatterns = {"/reset-password"})
public class ResetPasswordControl extends HttpServlet {
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
        try {
            response.setContentType("text/html;charset=UTF-8");
            if (request.getSession().getAttribute("hashcode") == null) {
                response.getWriter().write("Mã code đã hết hiệu lực, vui lòng yêu cầu xác thực lại");
                return;
            }
            String hashCode = (String) request.getSession().getAttribute("hashcode");
            String hashCodePara = request.getParameter("code").trim();
            String emailPara = request.getParameter("email").trim();
            UserDao userDao = new UserDaoImpl();
            User user = userDao.selectByMail(emailPara);
            if(user == null){
                response.getWriter().write("Người dùng không hợp lệ");
                return;
            }
            if(!hashCode.equals(hashCodePara)){
                response.getWriter().write("Mã xác thực không đúng, vui lòng xác thực lại");
                return;
            }
            request.setAttribute("email", emailPara);
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        try{
            response.setContentType("text/html;charset=UTF-8");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmNewPassword");
            String email = request.getParameter("email");
            if(!newPassword.equals(confirmPassword)){
                response.getWriter().write("Mật khẩu phải trùng với mật khẩu nhập lại");
                return;
            }
            UserDao userDao = new UserDaoImpl();
            User user = userDao.selectByMail(email);
            if(user == null){
                response.getWriter().write("Người dùng không hợp lệ");
                return;
            }
            user.setPassword(newPassword);
            userDao.updateUser(user);
            request.getSession().setAttribute("msgActive", "Cập nhật mật khẩu thành công");
            response.sendRedirect("login.jsp");

        }
        catch (Exception e){

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

}
