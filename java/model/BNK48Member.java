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
public class BNK48Member {
    
    private Integer id;
    private Integer generation;
    private String name;
    
    public BNK48Member(){
        
    }
    
    public BNK48Member(int generation, String name){
        this.generation = generation;
        this.name = name;
    }
    
    public BNK48Member(int id, int generation, String name){
        this.id = id;
        this.generation = generation;
        this.name = name;
    }
            
    public Integer getId() {
        return id;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

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
            String sql = "INSERT INTO BNK48_MEMBER(generation, name) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, this.generation);
            pstmt.setString(2, this.name);
            
            pstmt.executeUpdate();
            return 200;
            
        } catch (SQLException ex) {
            Logger.getLogger(BNK48Member.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode(); // 1062
        } finally {
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
               
    }
    
}
