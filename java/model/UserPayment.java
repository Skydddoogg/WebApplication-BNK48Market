/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import appcontext.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author toppy
 */
public class UserPayment {
    
    private Integer user_id;
    private Integer payment_id;

    public UserPayment(){
        
    }
    
    public UserPayment(Integer user_id, Integer payment_id){
        this.user_id = user_id;
        this.payment_id = payment_id;
    }
    
    public void insert(){
        PreparedStatement pstmt = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "INSERT INTO USER_PAYMENT(user_id, payment_id) VALUES(?,?)";
            System.out.println(sql);
            
            int i = 1;
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(i++, this.user_id);
            pstmt.setInt(i++, this.payment_id);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserPayment.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
    }
    
}
