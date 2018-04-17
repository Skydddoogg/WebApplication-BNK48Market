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
import model.Advertisement;

/**
 *
 * @author toppy
 */
public class AdvertisementRepo {
    

    public ArrayList<Advertisement> getAllAdvertisement(){
        ArrayList<Advertisement> advertisements = new ArrayList<Advertisement>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT * FROM ADVERTISEMENT";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                Advertisement ads = new Advertisement(rs.getInt("id"), rs.getString("html_code"), rs.getInt("added_by_admin_id"));
                advertisements.add(ads);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdvertisementRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return advertisements;
    }
    

}
