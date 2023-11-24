/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.busticketbooking.busticketbooking.controllers;

import com.busticketbooking.busticketbooking.dao.UserDao;
import com.busticketbooking.busticketbooking.dao.impl.DAO;
import com.busticketbooking.busticketbooking.dao.impl.UserDaoImpl;
import com.busticketbooking.busticketbooking.models.Account;
import com.busticketbooking.busticketbooking.models.User;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@WebServlet(name = "EditProfile", urlPatterns = {"/profile"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class EditProfile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Account account = (Account) request.getSession().getAttribute("acc");
            if (account == null) {
                response.sendRedirect("login.jsp");
            }
            UserDao userDao = new UserDaoImpl();
            User user = userDao.selectById(account.getId());
            request.setAttribute("user", user);
            
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } catch (Exception e) {
            
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Account account = (Account) request.getSession().getAttribute("acc");
            if (account == null) {
                response.sendRedirect("login.jsp");
            }
            String phone = request.getParameter("phone");            
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            Part image = request.getPart("image");
            String name = request.getParameter("name");
            
            String avatar = account.getImage();
            String originalFilename = image.getSubmittedFileName();
            int index = originalFilename.lastIndexOf(".");
            String ext = originalFilename.substring(index + 1);
            String filename = "user-" + account.getId() + "." + ext;

            if (!filename.endsWith(".")) {
                String appPath = getServletContext().getRealPath("");
                File rootDir = new File(appPath).getParentFile().getParentFile();
                avatar = "\\assets\\images\\profile\\" + filename;

                String uploadPath = rootDir.getAbsolutePath() + "\\src\\main\\webapp" + avatar;

                FileOutputStream fos = new FileOutputStream(uploadPath);
                InputStream is = image.getInputStream();

                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();
            }
            
            UserDao userDao = new UserDaoImpl();
            User user = userDao.selectById(account.getId());
            user.setName(name);
            user.setAddress(address);
            user.setPhone(phone);
            user.setGender(gender);
            user.setImage(avatar);
            userDao.updateUser(user);
            
            account.setName(name);
            account.setAddress(address);
            account.setPhone(phone);
            account.setGender(gender);
            account.setImage(avatar);
            
            response.sendRedirect("profile");            
            
        } catch (Exception e) {
            
        }
    }
}
