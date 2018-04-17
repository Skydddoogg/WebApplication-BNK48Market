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
import model.Delivery;

/**
 *
 * @author toppy
 */
public class DeliveryRepo {
    

    public ArrayList<Delivery> getAllDelivery(){
        ArrayList<Delivery> deliveries = new ArrayList<Delivery>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT * FROM DELIVERY ORDER BY name";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                Delivery delivery = new Delivery(rs.getInt("id"), rs.getString("name"));
                deliveries.add(delivery);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return deliveries;
    }
    

}
