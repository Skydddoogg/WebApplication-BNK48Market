/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import route.*;
import appcontext.DBConnector;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import model.BNK48Member;
import model.Category;
import model.Delivery;
import model.User;
import repository.BNK48MemberRepo;
import repository.CategoryRepo;
import repository.DeliveryRepo;
import tool.NumberFormat;
import tool.TimestampToHumanReadable;

/**
 *
 * @author toppy
 */
@WebServlet(name = "FetchPostController", urlPatterns = {"/posts"})
public class FetchPostController extends HttpServlet {

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
        
        int item_per_page = 5;
        int page_number = NumberFormat.toNumeric(request.getParameter("page"));
        int position = (page_number - 1) * item_per_page;
        // -- For show each post
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        String sql;
        
        PreparedStatement pstmt2 = null;
        ResultSet rs2 = null;
        Connection conn2 = DBConnector.getConnection();
        String sql2;

        ArrayList <HashMap<String,ArrayList<String>>> allPostsData = new ArrayList <>();
        
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        
        DecimalFormat formatter = new DecimalFormat("#,###");

        try {
            sql = "SELECT *\n" +
                        "FROM `POST` `a`\n" +
                        "JOIN `USER` `b`\n" +
                        "ON (`a`.`user_id` = `b`.`id`) AND `a`.status='ACTIVE' AND `b`.activate_status='ACTIVE' "
                      + "LEFT OUTER JOIN `BNK48_MEMBER` `c`"
                      + "ON (`a`.`bnk48_member_id` = `c`.`id`) "
                      + "ORDER BY timestamp DESC "
                    + "LIMIT ?, ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, position);
            pstmt.setInt(2, item_per_page);
            rs = pstmt.executeQuery();
            
            ArrayList<String> handler;
            while (rs.next()) {
                HashMap<String,ArrayList<String>> row = new HashMap<>();
                handler = new ArrayList<>(); handler.add(rs.getString("a.id")); row.put("post_id", handler);
                handler = new ArrayList<>(); handler.add(rs.getString("thumbnail")); row.put("thumbnail", handler);
                handler = new ArrayList<>(); handler.add(formatter.format(rs.getFloat("price"))); row.put("price", handler);
                handler = new ArrayList<>(); handler.add(rs.getString("description").replace("\n", "<br>")); row.put("description", handler);
                handler = new ArrayList<>(); handler.add(rs.getString("remaining")); row.put("remaining", handler);
                handler = new ArrayList<>(); handler.add(rs.getString("location")); row.put("location", handler);
                handler = new ArrayList<>(); handler.add(rs.getString("user_id")); row.put("user_id", handler);
                handler = new ArrayList<>(); handler.add(rs.getString("c.name")); row.put("member", handler);
                handler = new ArrayList<>(); handler.add(TimestampToHumanReadable.convert(rs.getTimestamp("timestamp"))); row.put("timestamp", handler);
                handler = new ArrayList<>(); handler.add(rs.getString("username")); row.put("username", handler);
                handler = new ArrayList<>(); handler.add(rs.getString("email")); row.put("email", handler);
                handler = new ArrayList<>(); handler.add(rs.getString("profile_picture")); row.put("profile_picture", handler);
                handler = new ArrayList<>(); handler.add(rs.getString("facebook_id")); row.put("facebook_id", handler);
                handler = new ArrayList<>(); handler.add(rs.getString("twitter_id")); row.put("twitter_id", handler);
                handler = new ArrayList<>(); handler.add(rs.getString("line_id")); row.put("line_id", handler);
                

                // Category
                int post_id = rs.getInt("a.id");
                
                sql2 = "SELECT `b`.name `level1`, `c`.name `level2`, `d`.name `level3`\n" +
                        "FROM `POST_TAG_CATEGORY` `a`\n" +
                        "JOIN `CATEGORY` `b`\n" +
                        "ON (`a`.category_id = `b`.id AND `a`.post_id = ?)\n" +
                        "LEFT OUTER JOIN `CATEGORY` `c`\n" +
                        "ON(`b`.super = `c`.id)\n" +
                        "LEFT OUTER JOIN `CATEGORY` `d`\n" +
                        "ON(`c`.super = `d`.id)";
                pstmt2 = conn2.prepareStatement(sql2);
                pstmt2.setInt(1, post_id);
                rs2 = pstmt2.executeQuery();
                
                ArrayList<String> handler_lv1 = new ArrayList<>();
                ArrayList<String> handler_lv2 = new ArrayList<>();
                ArrayList<String> handler_lv3 = new ArrayList<>();
                
                while(rs2.next()){    
                    handler_lv1.add(rs2.getString("level1"));
                    handler_lv2.add(rs2.getString("level2"));
                    handler_lv3.add(rs2.getString("level3"));                    
                }

                row.put("cat_level1", handler_lv1);
                row.put("cat_level2", handler_lv2);
                row.put("cat_level3", handler_lv3);
                
                // Delivery
                sql2 = "SELECT * \n" +
                        "FROM `POST_DELIVERY` `a`\n" +
                        "JOIN `DELIVERY` `b`\n" +
                        "ON(`a`.delivery_id = `b`.id AND `a`.post_id=?)";
                pstmt2 = conn2.prepareStatement(sql2);
                pstmt2.setInt(1, post_id);
                rs2 = pstmt2.executeQuery();
                
                handler = new ArrayList<>();
                while(rs2.next()){
                    handler.add(rs2.getString("name"));
                }
                row.put("delivery", handler);
                
                // Payment
                int user_id = rs.getInt("user_id");
                sql2 = "SELECT * \n" +
                        "FROM `USER_PAYMENT` `a`\n" +
                        "JOIN `PAYMENT` `b`\n" +
                        "ON(`a`.payment_id = `b`.id AND `a`.user_id=?)";
                pstmt2 = conn2.prepareStatement(sql2);
                pstmt2.setInt(1, user_id);
                rs2 = pstmt2.executeQuery();
                
                handler = new ArrayList<>();
                ArrayList<String> handler2 = new ArrayList<>();
                while(rs2.next()){
                    handler.add(rs2.getString("name"));
                    handler2.add(rs2.getString("logo"));
                }
                row.put("payment_name", handler);
                row.put("payment_logo", handler2);
                
                // Like count
                sql2 = "SELECT COUNT(*) `like_count` " +
                        "FROM `USER_VOTE` "
                        + "WHERE dest_user_id=? AND type='LIKE'";
                pstmt2 = conn2.prepareStatement(sql2);
                pstmt2.setInt(1, user_id);
                rs2 = pstmt2.executeQuery();
                
                handler = new ArrayList<>();
                while(rs2.next()){
                    handler.add(rs2.getString("like_count"));
                }
                row.put("like_count", handler);
                
                // Save-Post (This post has been saved?)
                if(user != null){
                    sql2 = "SELECT COUNT(*) `count` "
                            + "FROM `USER_SAVE_POST` \n" +
                            "WHERE `user_id`=? AND `post_id`=?";
                    pstmt2 = conn2.prepareStatement(sql2);
                    pstmt2.setInt(1, user.getId());
                    pstmt2.setInt(2, post_id);
                    rs2 = pstmt2.executeQuery();

                    handler = new ArrayList<>();
                    while(rs2.next()){
                        handler.add(rs2.getString("count"));
                    }
                    row.put("is_saved", handler);
                }
                
                allPostsData.add(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FetchPostController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                rs2.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                pstmt.close();
                pstmt2.close();
            } catch (Exception e) {
                /* ignored */ }
            try {
                conn.close();
                conn2.close();
            } catch (Exception e) {
                /* ignored */ }
        }
        
        request.setAttribute("allPostsData", allPostsData);
        RequestDispatcher dp = request.getRequestDispatcher("static/posts.jsp");
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
