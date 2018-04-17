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
@WebServlet(name = "VoteController", urlPatterns = {"/user/vote"})
public class VoteController extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        int src_user_id = user.getId();
        int dest_user_id = NumberFormat.toNumeric(request.getParameter("dest_user_id"));
        String action = request.getParameter("action");
            
        PreparedStatement pstmt = null;
        Connection conn = DBConnector.getConnection();
        String sql = null;

        try {
            
            if(action.equals("like")){
                sql = "INSERT INTO `USER_VOTE` (src_user_id, dest_user_id, type) \n" +
                        "VALUES(?, ?, 'LIKE')\n" +
                        "ON DUPLICATE KEY UPDATE type='LIKE'";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, src_user_id);
                pstmt.setInt(2, dest_user_id);
                pstmt.executeUpdate();
            }
            else if(action.equals("dislike")){
                sql = "INSERT INTO `USER_VOTE` (src_user_id, dest_user_id, type) \n" +
                        "VALUES(?, ?, 'DISLIKE')\n" +
                        "ON DUPLICATE KEY UPDATE type='DISLIKE'";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, src_user_id);
                pstmt.setInt(2, dest_user_id);
                pstmt.executeUpdate();
            }
            else if(action.equals("unlike") || action.equals("undislike")){
                sql = "DELETE FROM `USER_VOTE` \n" +
                      "WHERE src_user_id=? AND dest_user_id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, src_user_id);
                pstmt.setInt(2, dest_user_id);
                pstmt.executeUpdate();
            }
            

            
        } catch (SQLException ex) {
            Logger.getLogger(VoteController.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) {  /* ignored */ }
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
