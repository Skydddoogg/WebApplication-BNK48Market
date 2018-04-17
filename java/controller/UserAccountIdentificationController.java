/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import appcontext.DBConnector;
import etc.ResponseCode;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.User;
import model.enums.UserStatus;
import tool.Uploader;

/**
 *
 * @author toppy
 */
@WebServlet(name = "UserAccountIdentificationController", urlPatterns = {"/user/account-identification/submit"})
@MultipartConfig
public class UserAccountIdentificationController extends HttpServlet {

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

        String national_id = request.getParameter("national_id");
        Part id_card_picture = request.getPart("id_card_picture");
        Part id_card_picture_selfie = request.getPart("id_card_picture_selfie");

        String idCardPictureDir = (String)getServletContext().getAttribute("idCardPictureDir");
        String idCardPictureSelfieDir = (String)getServletContext().getAttribute("idCardPictureSelfieDir");
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String filename;
        Uploader up;
        
        // Upload id_card_picture
        filename = user.getId() + "_" + timestamp.getTime();
        up = new Uploader(id_card_picture, idCardPictureDir, filename);
        String idCardPictureFilename = up.getFilename();
        
        // Upload id_card_picture_selfie
        filename = user.getId() + "_" + timestamp.getTime();
        up = new Uploader(id_card_picture_selfie, idCardPictureSelfieDir, filename);
        String idCardPictureSelfieFilename = up.getFilename();
        
        
        PreparedStatement pstmt = null;
        Connection conn = DBConnector.getConnection();
        String sql = null;
        
        user.setId_card_picture(idCardPictureFilename);
        user.setId_card_picture_selfie(idCardPictureSelfieFilename);
        user.setNational_id(national_id);
        user.setActivate_status(UserStatus.PENDING);
        
        user.save();
        
        session.setAttribute("success", ResponseCode.SUCCESS_202);
        response.sendRedirect(request.getContextPath() + "/feed");
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
