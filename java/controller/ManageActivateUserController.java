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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Admin;
import tool.NumberFormat;

/**
 *
 * @author toppy
 */
@WebServlet(name = "ManageActivateUserController", urlPatterns = {"/admin/manage-user/activate"})
public class ManageActivateUserController extends HttpServlet {

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
        Admin admin = (Admin)session.getAttribute("admin");
        
        PreparedStatement pstmt = null;
        Connection conn = DBConnector.getConnection();
        String sql = null;

        try {

            int user_id = NumberFormat.toNumeric(request.getParameter("uid"));
            String action = request.getParameter("action");
            
            if(action.equals("activate")){
                sql = "UPDATE USER SET activate_status='ACTIVE', activate_by_admin_id=?, activate_timestamp=? WHERE id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, admin.getId());
                pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
                pstmt.setInt(3, user_id);
                pstmt.executeUpdate();
                session.setAttribute("success", ResponseCode.SUCCESS_204);
            }
            else if(action.equals("reject")){
                sql = "UPDATE USER SET activate_status='REJECTED' WHERE id=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, user_id);
                pstmt.executeUpdate();
                session.setAttribute("success", ResponseCode.SUCCESS_204);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManageActivateUserController.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) {  /* ignored */ }
        }

        response.sendRedirect(request.getContextPath() + "/admin/manage-user");
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
