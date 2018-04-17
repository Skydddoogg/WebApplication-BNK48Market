/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import appcontext.DBConnector;
import etc.ResponseCode;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
@WebServlet(name = "UpdateDeleteSalepostController", urlPatterns = {"/user/manage-salepost/submit"})
public class UpdateDeleteSalepostController extends HttpServlet {

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
        
        String action = request.getParameter("action");
        String[] deletePostIds = request.getParameterValues("delete");
        
        if(action.equals("ลบโพสต์")){
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            Connection conn = DBConnector.getConnection();
            String sql = null;

            try {
                for(String post : deletePostIds){
                    int post_id = NumberFormat.toNumeric(post);
                    
                    // Delete thumbnail file
                    sql = "SELECT thumbnail FROM POST WHERE id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, post_id);
                    rs = pstmt.executeQuery();

                    while(rs.next()){
                        String thumbnailFilename = rs.getString("thumbnail");
                        String postThumbnailDir = (String)getServletContext().getAttribute("postThumbnailDir");

                        if(thumbnailFilename != null && !thumbnailFilename.isEmpty()){
                            try{
                                File file = new File(postThumbnailDir + thumbnailFilename);
                                file.delete();
                            }
                            catch(Exception ex){ }
                        }
                    }

                    // Delete record
                    sql = "DELETE FROM POST WHERE id=?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, post_id);
                    pstmt.executeUpdate();
                    session.setAttribute("success", ResponseCode.SUCCESS_203);
                }

            } catch (SQLException ex) {
                Logger.getLogger(UpdateDeleteSalepostController.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                try { pstmt.close(); } catch (Exception e) { /* ignored */ }
                try { conn.close(); } catch (Exception e) {  /* ignored */ }
            }
        }
        else if(action.equals("บันทึกโพสต์")){
            PreparedStatement pstmt = null;
            Connection conn = DBConnector.getConnection();
            String sql = null;

            String[] allPostIds = request.getParameterValues("post_id");
            
            String status;
            int remaining;
            int post_id;
            
            try {
                sql = "UPDATE POST SET status=?, remaining=? WHERE id=?";
                pstmt = conn.prepareStatement(sql);             
                for(String post : allPostIds){
                    
                    status = request.getParameter("status_post_" + post);
                    remaining = NumberFormat.toNumeric(request.getParameter("remaining_post_" + post));
                    post_id = NumberFormat.toNumeric(post);
                    
                    if(status.equals("SOLD") || remaining == 0){
                        pstmt.setString(1, "SOLD");
                        pstmt.setInt(2, 0);
                    }
                    else {
                        pstmt.setString(1, status);
                        pstmt.setInt(2, remaining);
                    }
                    pstmt.setInt(3, post_id);
                    pstmt.executeUpdate();
                    session.setAttribute("success", ResponseCode.SUCCESS_201);
                    
                }

            } catch (SQLException ex) {
                Logger.getLogger(UpdateDeleteSalepostController.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                try { pstmt.close(); } catch (Exception e) { /* ignored */ }
                try { conn.close(); } catch (Exception e) {  /* ignored */ }
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/user/manage-salepost");
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
