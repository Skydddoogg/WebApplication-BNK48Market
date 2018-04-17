package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import etc.ResponseCode;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author toppy
 */
@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

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

        try (PrintWriter out = response.getWriter()) {

            try {
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String sex = request.getParameter("sex");

                User user = new User(firstname, lastname, username, password, email, sex);
                int resultCode = user.insert();
                
                switch(resultCode){
                    case 200:
                        // OK, don't set ERROR
                        break;
                    case 1048:
                        request.setAttribute("error", ResponseCode.ERR_1048); // ใส่ข้อมูลไม่ครบ (NN)
                        break;
                    case 1062:
                        request.setAttribute("error", ResponseCode.ERR_1062); // PK, UQ ซ้ำ
                        break;
                    default:
                        request.setAttribute("error", ResponseCode.ERR_9999);
                }
            }
            catch(Exception ex){
                request.setAttribute("error", ResponseCode.ERR_1048); // ใส่ข้อมูลไม่ครบ
            }
            
            RequestDispatcher dp;
            if(request.getAttribute("error") != null){
                request.setAttribute("errorType", "register");
                dp = request.getRequestDispatcher("/login");
            }
            else{
                request.setAttribute("username", request.getParameter("username"));
                request.setAttribute("password", request.getParameter("password"));
                dp = request.getRequestDispatcher("/user-auth");
            }
            dp.forward(request, response);
            

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
