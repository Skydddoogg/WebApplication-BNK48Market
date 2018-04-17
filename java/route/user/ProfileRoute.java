/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route.user;

import appcontext.DBConnector;
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
import javax.servlet.http.HttpSession;
import model.User;
import model.enums.UserStatus;
import tool.NumberFormat;
import tool.TimestampToHumanReadable;

/**
 *
 * @author toppy
 */
@WebServlet(name = "ProfileRoute", urlPatterns = {"/user/profile"})
public class ProfileRoute extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user != null && user.getActivate_status() != UserStatus.ACTIVE){
            // Fetch activation status for keep update
            user.fetchActivationStatus();
        }
                
        int id = NumberFormat.toNumeric(request.getParameter("id"));
        
        User userQuery = User.findUserById(id);
        
        // User information
        if(userQuery != null){
            request.setAttribute("userQuery", userQuery);
            
            // Other Parts
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            Connection conn = DBConnector.getConnection();
            String sql = null;

            try {
                
                // Comments
                sql = "SELECT `a`.id `src_user_id`, `a`.username, `a`.profile_picture,  `b`.comment, `b`.timestamp\n" +
                        "FROM `USER` `a`\n" +
                        "JOIN `USER_COMMENT` `b`\n" +
                        "ON (`a`.id = `b`.src_user_id AND `b`.dest_user_id=?)"
                        + " ORDER BY `b`.timestamp DESC";
                pstmt = conn.prepareStatement(sql);             
                pstmt.setInt(1, userQuery.getId());
                rs = pstmt.executeQuery();
                
                ArrayList<HashMap<String, String>> allCommentData = new ArrayList<>();
                
                while(rs.next()){
                    HashMap<String, String> row = new HashMap<>();
                    row.put("src_user_id", rs.getString("src_user_id"));
                    row.put("username", rs.getString("username"));
                    row.put("profile_picture", rs.getString("profile_picture"));
                    row.put("comment", rs.getString("comment"));
                    row.put("timestamp", TimestampToHumanReadable.convert(rs.getTimestamp("timestamp")));
                    
                    allCommentData.add(row);
                }
                
                request.setAttribute("allCommentData", allCommentData);
                
                // Payment
                sql = "SELECT name, logo \n" +
                        "FROM `USER_PAYMENT` `a`\n" +
                        "JOIN `PAYMENT` `b`\n" +
                        "ON(`a`.payment_id = `b`.id AND `a`.user_id=?)";
                pstmt = conn.prepareStatement(sql);             
                pstmt.setInt(1, userQuery.getId());
                rs = pstmt.executeQuery();
                
                ArrayList<HashMap<String, String>> allPaymentData = new ArrayList<>();
                while(rs.next()){
                    HashMap<String, String> row = new HashMap<>();
                    row.put("name", rs.getString("name"));
                    row.put("logo", rs.getString("logo"));
                    
                    allPaymentData.add(row);
                }
                
                request.setAttribute("allPaymentData", allPaymentData);
                
                // Like / Dislike
                sql = "SELECT IFNULL(like_count, 0) `like`, IFNULL(dislike_count, 0) `dislike`\n" +
                    "FROM (SELECT dest_user_id, COUNT(*) `like_count`\n" +
                    "FROM `USER_VOTE`\n" +
                    "WHERE type='LIKE' AND dest_user_id = ?\n" +
                    "GROUP BY dest_user_id) `like_tbl`\n" +
                    "LEFT OUTER JOIN (SELECT dest_user_id, COUNT(*) `dislike_count`\n" +
                    "    FROM `USER_VOTE`\n" +
                    "    WHERE type='DISLIKE' AND dest_user_id = ?\n" +
                    "    GROUP BY dest_user_id) `dislike_tbl`\n" +
                    "ON (`like_tbl`.dest_user_id = `dislike_tbl`.dest_user_id)\n" +
                    "\n" +
                    "UNION\n" +
                    "\n" +
                    "SELECT IFNULL(like_count, 0) `like`, IFNULL(dislike_count, 0) `dislike`\n" +
                    "FROM (SELECT dest_user_id, COUNT(*) `like_count`\n" +
                    "FROM `USER_VOTE`\n" +
                    "WHERE type='LIKE' AND dest_user_id = ?\n" +
                    "GROUP BY dest_user_id) `like_tbl`\n" +
                    "RIGHT OUTER JOIN (SELECT dest_user_id, COUNT(*) `dislike_count`\n" +
                    "    FROM `USER_VOTE`\n" +
                    "    WHERE type='DISLIKE' AND dest_user_id = ?\n" +
                    "    GROUP BY dest_user_id) `dislike_tbl`\n" +
                    "ON (`like_tbl`.dest_user_id = `dislike_tbl`.dest_user_id)";
                
                pstmt = conn.prepareStatement(sql);             
                pstmt.setInt(1, userQuery.getId());
                pstmt.setInt(2, userQuery.getId());
                pstmt.setInt(3, userQuery.getId());
                pstmt.setInt(4, userQuery.getId());
                rs = pstmt.executeQuery();
                
                HashMap<String, String> voteCount = new HashMap<>();
                
                // Initiate value (when this user has no entry in VOTE table)
                voteCount.put("like", "0");
                voteCount.put("dislike", "0");
                    
                while(rs.next()){
                    voteCount.put("like", String.valueOf(rs.getInt("like")));
                    voteCount.put("dislike", String.valueOf(rs.getInt("dislike")));
                }
                
                request.setAttribute("voteCount", voteCount);
                
                // Voted ?
                sql = "SELECT type FROM `USER_VOTE` WHERE `src_user_id` = ? AND `dest_user_id` = ?";
                
                pstmt = conn.prepareStatement(sql);             
                pstmt.setInt(1, user.getId());
                pstmt.setInt(2, userQuery.getId());
                rs = pstmt.executeQuery();
                
                String voted_type = null;
                
                while(rs.next()){
                    voted_type = rs.getString("type");
                }
                
                request.setAttribute("voted_type", voted_type);

            } catch (SQLException ex) {
                Logger.getLogger(ProfileRoute.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                try { rs.close(); } catch (Exception e) { /* ignored */ }
                try { pstmt.close(); } catch (Exception e) { /* ignored */ }
                try { conn.close(); } catch (Exception e) {  /* ignored */ }
            }
            

            RequestDispatcher dp = request.getRequestDispatcher("/static/profile.jsp");
            dp.forward(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
