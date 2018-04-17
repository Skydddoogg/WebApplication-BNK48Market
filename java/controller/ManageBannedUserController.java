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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tool.NumberFormat;

/**
 *
 * @author toppy
 */
@WebServlet(name = "ManageBannedUserController", urlPatterns = {"/admin/manage-user/ban"})
public class ManageBannedUserController extends HttpServlet {

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

        PreparedStatement pstmt = null;
        Connection conn = DBConnector.getConnection();
        String sql;

        try {

            Set<String> allStatusById = request.getParameterMap().keySet();
            
            for (String id : allStatusById) {
                String status = request.getParameter(id);
                int user_id = NumberFormat.toNumeric(id);
                if (status.equals("banned")) {
                    sql = "UPDATE USER SET activate_status=? WHERE id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, "BANNED");
                    pstmt.setInt(2, user_id);
                    pstmt.executeUpdate();
                    
                } else if (status.equals("normal")) {
                    sql = "UPDATE USER SET activate_status=? WHERE id=? AND activate_status='BANNED'";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, "PENDING");
                    pstmt.setInt(2, user_id);
                    pstmt.executeUpdate();
                }   
            }
            HttpSession session = request.getSession();
            session.setAttribute("success", ResponseCode.SUCCESS_204);

        } catch (SQLException ex) {
            Logger.getLogger(ManageBannedUserController.class.getName()).log(Level.SEVERE, null, ex);
            
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
