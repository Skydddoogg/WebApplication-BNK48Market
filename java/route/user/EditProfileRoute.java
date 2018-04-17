/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package route.user;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Payment;
import model.User;
import model.enums.UserStatus;
import repository.PaymentRepo;
import repository.UserPaymentRepo;

/**
 *
 * @author toppy
 */
@WebServlet(name = "EditProfileRoute", urlPatterns = {"/user/edit-profile"})
public class EditProfileRoute extends HttpServlet {

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
        
        PaymentRepo paymentRepo = new PaymentRepo();
        
        ArrayList<Payment> payments = paymentRepo.getAllPayment();
        request.setAttribute("payments", payments);
        
        UserPaymentRepo userPaymentRepo = new UserPaymentRepo();
        ArrayList<Payment> userPaymentSelected = userPaymentRepo.getAllPaymentByUserId(user.getId());
        
        request.setAttribute("userPaymentSelected", userPaymentSelected);
        RequestDispatcher dp = request.getRequestDispatcher("/static/edit-profile.jsp");
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
