/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import appcontext.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author toppy
 */
public class Delivery {
    
    private Integer id;
    private String name;
    
    public Delivery(){
        
    }
    
    public Delivery(String name){
        this.name = name;
    }
    
    public Delivery(int id, String name){
        this.id = id;
        this.name = name;
    }
            
    public Integer getId() {
        return id;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int insert(){
        PreparedStatement pstmt = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "INSERT INTO DELIVERY(name) VALUES(?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, this.name);
            
            pstmt.executeUpdate();
            return 200;
            
        } catch (SQLException ex) {
            Logger.getLogger(Delivery.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode(); // 1062
        } finally {
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
               
    }
    
}
