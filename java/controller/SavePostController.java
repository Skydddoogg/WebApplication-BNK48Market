/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import appcontext.DBConnector;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import tool.NumberFormat;

/**
 *
 * @author toppy
 */
@WebServlet(name = "SavePostController", urlPatterns = {"/user/save-post"})
public class SavePostController extends HttpServlet {

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
        
        String action = request.getParameter("action");
        int post_id = NumberFormat.toNumeric(request.getParameter("post_id"));
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        if(post_id == 0){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        
        if(action.equals("unsave")){
            PreparedStatement pstmt = null;
            Connection conn = DBConnector.getConnection();
            String sql = null;

            try {
                sql = "DELETE FROM `USER_SAVE_POST` WHERE `user_id` = ? AND `post_id` = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, user.getId());
                pstmt.setInt(2, post_id);
                pstmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(SavePostController.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                try { pstmt.close(); } catch (Exception e) { /* ignored */ }
                try { conn.close(); } catch (Exception e) {  /* ignored */ }
            }
        }
        else if(action.equals("save")){
            PreparedStatement pstmt = null;
            Connection conn = DBConnector.getConnection();
            String sql = null;

            try {
                sql = "INSERT INTO `USER_SAVE_POST` (`user_id`, `post_id`) VALUES (?,?);";
                pstmt = conn.prepareStatement(sql);             
                pstmt.setInt(1, user.getId());
                pstmt.setInt(2, post_id);
                pstmt.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(SavePostController.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                try { pstmt.close(); } catch (Exception e) { /* ignored */ }
                try { conn.close(); } catch (Exception e) {  /* ignored */ }
            }
        }
        
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
