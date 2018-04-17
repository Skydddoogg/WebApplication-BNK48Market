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
import model.UserPayment;

/**
 *
 * @author toppy
 */
public class UserPaymentRepo {
    

    public void clearAllPaymentByUserId(int user_id){
        PreparedStatement pstmt = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "DELETE FROM USER_PAYMENT WHERE user_id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user_id);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserPayment.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    public ArrayList<Payment> getAllPaymentByUserId(int user_id){
        ArrayList<Payment> payments = new ArrayList<Payment>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT b.id, b.name, b.logo "
                    + "FROM USER_PAYMENT a "
                    + "JOIN PAYMENT b "
                    + "ON(a.payment_id=b.id AND a.user_id=?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user_id);
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
