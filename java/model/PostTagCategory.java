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
public class PostTagCategory {
    
    private Integer post_id;
    private Integer category_id;
    
    public PostTagCategory(Integer post_id, Integer category_id){
        this.post_id = post_id;
        this.category_id = category_id;
    }
    
    public void insert(){
        PreparedStatement pstmt = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "INSERT INTO POST_TAG_CATEGORY(post_id, category_id) VALUES(?,?)";
            System.out.println(sql);
            
            int i = 1;
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(i++, this.post_id);
            pstmt.setInt(i++, this.category_id);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PostTagCategory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
    }
    
}
