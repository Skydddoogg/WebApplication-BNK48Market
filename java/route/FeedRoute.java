/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route;

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
import model.Advertisement;
import model.BNK48Member;
import model.Category;
import model.Delivery;
import model.User;
import model.enums.UserStatus;
import repository.AdvertisementRepo;
import repository.BNK48MemberRepo;
import repository.CategoryRepo;
import repository.DeliveryRepo;
import tool.TimestampToHumanReadable;

/**
 *
 * @author toppy
 */
@WebServlet(name = "FeedRoute", urlPatterns = {"/feed"})
public class FeedRoute extends HttpServlet {

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
        
        // -- For search & Create Post
        BNK48MemberRepo memberRepo = new BNK48MemberRepo();
        ArrayList<BNK48Member> members = memberRepo.getAllBNK48Member();
        
        DeliveryRepo deliveryRepo = new DeliveryRepo();
        ArrayList<Delivery> deliveries = deliveryRepo.getAllDelivery();
        
        CategoryRepo categoryRepo = new CategoryRepo();
        ArrayList<Category> categories = categoryRepo.getAllCategory();
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if(user != null && user.getActivate_status() != UserStatus.ACTIVE){
            // Fetch activation status for keep update
            user.fetchActivationStatus();
        }

        request.setAttribute("members", members);
        request.setAttribute("deliveries", deliveries);
        request.setAttribute("categories", categories);
        
        // Ads
        AdvertisementRepo adsRepo = new AdvertisementRepo();
        ArrayList<Advertisement> ads = adsRepo.getAllAdvertisement();
        
        int visitCount = (int) getServletContext().getAttribute("visitCount");
        getServletContext().setAttribute("visitCount", (visitCount + 1) % 1000000);
        
        int amount_of_ads = ads.size();
        int iterator = visitCount % amount_of_ads;
        request.setAttribute("ads", ads.get(iterator));
        
        RequestDispatcher dp = request.getRequestDispatcher("static/feed.jsp");
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
