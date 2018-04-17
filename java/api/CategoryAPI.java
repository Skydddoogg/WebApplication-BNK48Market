/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import appcontext.DBConnector;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "CategoryAPI", urlPatterns = {"/api/category"})
public class CategoryAPI extends HttpServlet {

    private Connection conn;
    @Override
    public void init(){
        conn = DBConnector.getConnection();
    }
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
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        String super_id_str = request.getParameter("super_id");
        
        String json;
        CategoryRepo categoryRepo = new CategoryRepo();
        if(super_id_str == null){
            ArrayList<Category> categories = categoryRepo.getAllCategory();
            json = new Gson().toJson(categories);
        }
        else{
            try {
                int super_id = Integer.parseInt(super_id_str);   
                ArrayList<Category> categories = categoryRepo.getAllCategoryBySuper(super_id);
                json = new Gson().toJson(categories);
            }
            catch (NumberFormatException nfe){
                json = "[]";
            }
        }

        try (PrintWriter out = response.getWriter()) {
            out.write(json);
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
