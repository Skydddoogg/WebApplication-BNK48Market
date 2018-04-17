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
import java.sql.ResultSet;
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
import tool.NumberFormat;
import tool.Uploader;

/**
 *
 * @author toppy
 */
@WebServlet(name = "AddPostController", urlPatterns = {"/user/new-post"})
@MultipartConfig
public class AddPostController extends HttpServlet {

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
        User user = (User) session.getAttribute("user");
        try {
            if(request.getParameter("category").equals("0"))
                throw new Exception();

            String description = request.getParameter("description");
            int member = NumberFormat.toNumeric(request.getParameter("member"));
            float price = Float.parseFloat(request.getParameter("price"));
            String location = request.getParameter("location");
            String[] deliveries = request.getParameterValues("delivery");
            int user_id = user.getId();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Upload Thumbnail
            Part thumbnail = request.getPart("thumbnail");
            String postThumbnailDir = (String) getServletContext().getAttribute("postThumbnailDir");

            String filename = user.getId() + "_" + timestamp.getTime();
            Uploader up = new Uploader(thumbnail, postThumbnailDir, filename);
            String thumbnailFilename = up.getFilename();

            PreparedStatement pstmt = null;
            Connection conn = DBConnector.getConnection();
            String sql = null;

            int post_id = 0;

            try {

                sql = "INSERT INTO POST(thumbnail, price, description, location, user_id, bnk48_member_id) VALUES(?,?,?,?,?,NULLIF(?,0))";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, thumbnailFilename);
                pstmt.setFloat(2, price);
                pstmt.setString(3, description);
                pstmt.setString(4, location);
                pstmt.setInt(5, user_id);
                pstmt.setInt(6, member);
                pstmt.executeUpdate();

                sql = "SELECT LAST_INSERT_ID() `last_id`";
                pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    post_id = rs.getInt("last_id");
                }

                // POST_TAG_CATEGORY
                sql = "INSERT INTO POST_TAG_CATEGORY(post_id, category_id) VALUES(?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, post_id);

                if (request.getParameterMap().keySet().contains("type")) {
                    String[] type_selected = request.getParameterValues("type");
                    for (String type : type_selected) {
                        int taged_category_id = NumberFormat.toNumeric(type);
                        pstmt.setInt(2, taged_category_id);
                        pstmt.executeUpdate();
                    }

                } else if (request.getParameterMap().keySet().contains("set")) {
                    int taged_category_id = NumberFormat.toNumeric(request.getParameter("set"));
                    pstmt.setInt(2, taged_category_id);
                    pstmt.executeUpdate();
                } else {
                    int taged_category_id = NumberFormat.toNumeric(request.getParameter("category"));
                    pstmt.setInt(2, taged_category_id);
                    pstmt.executeUpdate();
                }
                
                // POST_DELIVERY
                sql = "INSERT INTO `POST_DELIVERY`(`post_id`, `delivery_id`) VALUES(?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, post_id);
                
                String[] delivery_selected = request.getParameterValues("delivery");
                
                if (delivery_selected != null) {
                     
                    for (String delivery : delivery_selected) {
                        int taged_delivery_id = NumberFormat.toNumeric(delivery);
                        pstmt.setInt(2, taged_delivery_id);
                        pstmt.executeUpdate();
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(AddPostController.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                try {
                    pstmt.close();
                } catch (Exception e) {
                    /* ignored */ }
                try {
                    conn.close();
                } catch (Exception e) {
                    /* ignored */ }
            }
        } catch (Exception ex) {
            session.setAttribute("addPostError", ResponseCode.ERR_1048);
        }
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
