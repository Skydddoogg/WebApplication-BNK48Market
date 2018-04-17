/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import etc.ResponseCode;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.User;
import model.UserPayment;
import repository.UserPaymentRepo;
import tool.NumberFormat;
import tool.Uploader;

/**
 *
 * @author toppy
 */
@WebServlet(name = "UserEditProfileController", urlPatterns = {"/user/edit-profile/submit"})
@MultipartConfig
public class UserEditProfileController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Part profilePicture = null;
        String password = null;
        String firstname = null;
        String lastname = null;
        String sex = null;
        String phone = null;
        String email = null;
        String facebookId = null;
        String twitterId = null;
        String lineId = null;
        String[] paymentsStr = null;

        String profilePictureDir = (String) getServletContext().getAttribute("profilePictureDir");

        profilePicture = request.getPart("profile_picture");
        password = request.getParameter("password");
        firstname = request.getParameter("firstname");
        lastname = request.getParameter("lastname");
        sex = request.getParameter("sex");
        phone = request.getParameter("phone");
        email = request.getParameter("email");
        facebookId = request.getParameter("facebook_id");
        twitterId = request.getParameter("twitter_id");
        lineId = request.getParameter("line_id");
        paymentsStr = request.getParameterValues("payment");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (profilePicture != null && profilePicture.getSize() > 0) {
            user.changeProfilePicture(profilePicture, profilePictureDir);
        }
        if (password != null && !password.isEmpty()) {
            user.setPassword(password);
        }

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setSex(sex);
        user.setPhone(phone);
        user.setEmail(email);
        user.setFacebook_id(facebookId);
        user.setTwitter_id(twitterId);
        user.setLine_id(lineId);
        user.save();

        // Payment
        // Clear all old options for this user
        UserPaymentRepo userPaymentRepo = new UserPaymentRepo();
        userPaymentRepo.clearAllPaymentByUserId(user.getId());
        // Update Payment
        if (paymentsStr != null) {
            for (String paymentStr : paymentsStr) {
                int payment_id = NumberFormat.toNumeric(paymentStr);
                UserPayment userPayment = new UserPayment(user.getId(), payment_id);
                userPayment.insert();
            }
        }
        
        session.setAttribute("success", ResponseCode.SUCCESS_201);
        response.sendRedirect(request.getContextPath() + "/user/edit-profile");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
