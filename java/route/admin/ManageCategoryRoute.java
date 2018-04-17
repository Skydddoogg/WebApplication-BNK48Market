/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route.admin;

import appcontext.DBConnector;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;
import repository.CategoryRepo;

/**
 *
 * @author toppy
 */
@WebServlet(name = "ManageCategoryRoute", urlPatterns = {"/admin/manage-category"})
public class ManageCategoryRoute extends HttpServlet {

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

        CategoryRepo categoryRepo = new CategoryRepo();
        ArrayList<Category> parentAndRootCat = categoryRepo.getParentAndRootCategory();

        request.setAttribute("parentAndRootCat", parentAndRootCat);

        ArrayList<String[]> catTable = new ArrayList<String[]>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();

        try {
            String sql = "SELECT `id`, `name` `category`, NULL `parent`, NULL `root`\n" +
                        "FROM `CATEGORY` \n" +
                        "WHERE `super` IS NULL\n" +
                        "\n" +
                        "UNION\n" +
                        "\n" +
                        "SELECT b.`id`, b.`name` `category`, a.`name` `parent`, NULL `root`\n" +
                        "FROM `CATEGORY` a \n" +
                        "JOIN `CATEGORY` b \n" +
                        "ON (b.`super` = a.`id` AND a.`super` IS NULL)\n" +
                        "\n" +
                        "UNION\n" +
                        "\n" +
                        "SELECT c.`id`, c.`name` `category`, b.`name` `parent`, a.`name` `root`\n" +
                        "FROM `CATEGORY` a \n" +
                        "JOIN `CATEGORY` b \n" +
                        "ON (b.`super` = a.`id` AND a.`super` IS NULL)\n" +
                        "JOIN `CATEGORY` c\n" +
                        "ON(c.`super` = b.`id`)";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String[] row = new String[4];
                row[0] = String.valueOf(rs.getInt("id"));
                row[1] = rs.getString("category");
                row[2] = rs.getString("parent");
                row[3] = rs.getString("root");
                catTable.add(row);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageCategoryRoute.class.getName()).log(Level.SEVERE, null, ex);
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

        request.setAttribute("catTable", catTable);

        RequestDispatcher dp = request.getRequestDispatcher("/static/admin/manage-category.jsp");
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
