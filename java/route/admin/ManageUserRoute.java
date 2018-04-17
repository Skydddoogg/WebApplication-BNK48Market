/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route.admin;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import repository.UserRepo;

/**
 *
 * @author toppy
 */
@WebServlet(name = "ManageUserRoute", urlPatterns = {"/admin/manage-user"})
public class ManageUserRoute extends HttpServlet {

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
        
        UserRepo userRepo = new UserRepo();
        ArrayList<User> awaitingUsers = userRepo.getAllAwaitingUser();
        ArrayList<User> bannedUsers = userRepo.getAllBannedUser();
        ArrayList<User> rejectedUsers = userRepo.getAllRejectedUser();
        ArrayList<User> nonIdentifyUsers = userRepo.getAllNonIdentifyUser();
        ArrayList<User> activeUsers = userRepo.getAllActiveUser();
        
        request.setAttribute("awaitingUsers", awaitingUsers);
        request.setAttribute("activeUsers", activeUsers);
        request.setAttribute("rejectedUsers", rejectedUsers);
        request.setAttribute("bannedUsers", bannedUsers);
        request.setAttribute("nonIdentifyUsers", nonIdentifyUsers);
        
        RequestDispatcher dp = request.getRequestDispatcher("/static/admin/manage-user.jsp");
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
