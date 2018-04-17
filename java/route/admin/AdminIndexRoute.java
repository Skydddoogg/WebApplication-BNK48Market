/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route.admin;

import appcontext.DBConnector;
import route.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author toppy
 */
@WebServlet(name = "AdminIndexRoute", urlPatterns = {"/admin/home"})
public class AdminIndexRoute extends HttpServlet {

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

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        String sql = null;

        try {
            // Post count
            sql = "SELECT COUNT(*) `post_count` FROM `POST`";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            int post_count = 0;
            if (rs != null) {
                while (rs.next()) {
                    post_count = rs.getInt("post_count");
                }
            }

            request.setAttribute("post_count", post_count);

            // User count
            sql = "SELECT COUNT(`a`.id) `all_user`, `tbl`.`active_user`, (COUNT(`a`.id) - `tbl`.`active_user`) `non_identify_user`\n"
                    + "FROM `USER` `a`, (\n"
                    + "	SELECT COUNT(*) `active_user`\n"
                    + "    FROM `USER`\n"
                    + "    WHERE activate_status = 'ACTIVE'\n"
                    + "	) `tbl`";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            int all_user = 0;
            int active_user = 0;
            int non_identify_user = 0;

            if (rs != null) {
                while (rs.next()) {
                    all_user = rs.getInt("all_user");
                    active_user = rs.getInt("active_user");
                    non_identify_user = rs.getInt("non_identify_user");
                }
            }

            request.setAttribute("all_user_count", all_user);
            request.setAttribute("active_user_count", active_user);
            request.setAttribute("non_identify_user_count", non_identify_user);
            
            // 5 Newly user
            sql = "SELECT * \n" +
                    "FROM `USER` \n" +
                    "ORDER BY id DESC\n" +
                    "LIMIT 5";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            ArrayList<HashMap<String, String>> newlyUsersData = new ArrayList<>();

            if (rs != null) {
                while (rs.next()) {
                    HashMap<String, String> row = new HashMap<>();
                    
                    row.put("id", rs.getString("id"));
                    row.put("username", rs.getString("username"));
                    row.put("firstname", rs.getString("firstname"));
                    row.put("lastname", rs.getString("lastname"));
                    row.put("profile_picture", rs.getString("profile_picture"));
                    
                    newlyUsersData.add(row);
                }
            }
            
            request.setAttribute("newlyUsersData", newlyUsersData);

            
            // Top 5 user that have most liked
            sql = "SELECT *\n" +
                    "FROM `USER` \n" +
                    "LEFT OUTER JOIN (SELECT dest_user_id, COUNT(`type`) `likes`\n" +
                    "                FROM `USER_VOTE`\n" +
                    "                WHERE `type` = 'LIKE' \n" +
                    "                GROUP BY dest_user_id) `tbl`\n" +
                    "ON(`tbl`.dest_user_id = `USER`.id)\n" +
                    "ORDER BY `tbl`.`likes` DESC\n" +
                    "LIMIT 5";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            ArrayList<HashMap<String, String>> mostLikedUsersData = new ArrayList<>();

            if (rs != null) {
                while (rs.next()) {
                    HashMap<String, String> row = new HashMap<>();
                    
                    row.put("id", rs.getString("id"));
                    row.put("username", rs.getString("username"));
                    row.put("firstname", rs.getString("firstname"));
                    row.put("lastname", rs.getString("lastname"));
                    row.put("profile_picture", rs.getString("profile_picture"));
                    row.put("likes", rs.getString("likes"));
                    
                    mostLikedUsersData.add(row);
                }
            }
            
            request.setAttribute("mostLikedUsersData", mostLikedUsersData);
            
            // Top 5 user that have most disliked
            sql = "SELECT *\n" +
                    "FROM `USER` \n" +
                    "LEFT OUTER JOIN (SELECT dest_user_id, COUNT(`type`) `likes`\n" +
                    "                FROM `USER_VOTE`\n" +
                    "                WHERE `type` = 'DISLIKE' \n" +
                    "                GROUP BY dest_user_id) `tbl`\n" +
                    "ON(`tbl`.dest_user_id = `USER`.id)\n" +
                    "ORDER BY `tbl`.`likes` DESC\n" +
                    "LIMIT 5";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            ArrayList<HashMap<String, String>> mostDislikedUsersData = new ArrayList<>();

            if (rs != null) {
                while (rs.next()) {
                    HashMap<String, String> row = new HashMap<>();
                    
                    row.put("id", rs.getString("id"));
                    row.put("username", rs.getString("username"));
                    row.put("firstname", rs.getString("firstname"));
                    row.put("lastname", rs.getString("lastname"));
                    row.put("profile_picture", rs.getString("profile_picture"));
                    row.put("likes", rs.getString("likes"));
                    
                    mostDislikedUsersData.add(row);
                }
            }
            request.setAttribute("mostDislikedUsersData", mostDislikedUsersData);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminIndexRoute.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                pstmt.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                conn.close();
            } catch (Exception e) {
                /* ignored */ }
        }

        RequestDispatcher dp = request.getRequestDispatcher("/static/admin/index.jsp");
        dp.forward(request, response);
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
