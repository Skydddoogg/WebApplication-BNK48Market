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

/**
 *
 * @author toppy
 */
@WebServlet(name = "ManageSalePostRoute", urlPatterns = {"/user/manage-salepost"})
public class ManageSalePostRoute extends HttpServlet {

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
        User user = (User)session.getAttribute("user");
        
        if(user != null && user.getActivate_status() != UserStatus.ACTIVE){
            // Fetch activation status for keep update
            user.fetchActivationStatus();
        }
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        ArrayList<HashMap<String, String>> allPostData = new ArrayList<>();
        try {
                String sql = "SELECT `a`.post_id, GROUP_CONCAT(DISTINCT `b`.name SEPARATOR '<br>') `level1`, `c`.name `level2`, `d`.name `level3`, `e`.`status`, `e`.`remaining`, `f`.`name` `member_name`\n" +
                            "FROM `POST_TAG_CATEGORY` `a`\n" +
                            "JOIN `CATEGORY` `b`\n" +
                            "ON (`a`.category_id = `b`.id)\n" +
                            "LEFT OUTER JOIN `CATEGORY` `c`\n" +
                            "ON(`b`.super = `c`.id)\n" +
                            "LEFT OUTER JOIN `CATEGORY` `d`\n" +
                            "ON(`c`.super = `d`.id)\n" +
                            "JOIN `POST` `e`\n" +
                            "ON(`a`.post_id = `e`.id)\n" +
                            "LEFT OUTER JOIN `BNK48_MEMBER` `f`\n" +
                            "ON(`e`.`bnk48_member_id` = `f`.id)\n" +
                            "WHERE `e`.user_id = ?\n" +
                            "GROUP BY `a`.post_id\n" +
                            "ORDER BY `a`.post_id";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, user.getId());
                rs = pstmt.executeQuery();
                
                
                while(rs.next()){
                    HashMap<String, String> row = new HashMap<>();
                    row.put("post_id", rs.getString("post_id"));
                    row.put("cat_level1", rs.getString("level1"));
                    row.put("cat_level2", rs.getString("level2"));
                    row.put("cat_level3", rs.getString("level3"));
                    row.put("status", rs.getString("status"));
                    row.put("remaining", rs.getString("remaining"));
                    row.put("member_name", rs.getString("member_name"));
                    
                    allPostData.add(row);
                }

        } catch (SQLException ex) {
            Logger.getLogger(ManageSalePostRoute.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) {  /* ignored */ }
        }
        
        request.setAttribute("allPostData", allPostData);
        RequestDispatcher dp = request.getRequestDispatcher("/static/manage-salepost.jsp");
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
