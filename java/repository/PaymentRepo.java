/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;


import appcontext.DBConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Payment;

/**
 *
 * @author toppy
 */
public class PaymentRepo {
    

    public ArrayList<Payment> getAllPayment(){
        ArrayList<Payment> payments = new ArrayList<Payment>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT * FROM PAYMENT";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                Payment payment = new Payment(rs.getInt("id"), rs.getString("name"), rs.getString("logo"));
                payments.add(payment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return payments;
    }

}
