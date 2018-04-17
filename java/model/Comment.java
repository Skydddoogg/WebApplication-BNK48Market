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
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author toppy
 */
public class Comment {
    
    private Integer id;
    private Integer src_user_id;
    private Integer dest_user_id;
    private String comment;
    private Timestamp timestamp;

    public Integer getId() {
        return id;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public Integer getSrc_user_id() {
        return src_user_id;
    }

    public void setSrc_user_id(Integer src_user_id) {
        this.src_user_id = src_user_id;
    }

    public Integer getDest_user_id() {
        return dest_user_id;
    }

    public void setDest_user_id(Integer dest_user_id) {
        this.dest_user_id = dest_user_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    

    public int insert(){
        PreparedStatement pstmt = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "INSERT INTO USER_COMMENT(src_user_id, dest_user_id, comment) VALUES(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, this.src_user_id);
            pstmt.setInt(2, this.dest_user_id);
            pstmt.setString(3, this.comment);
            
            pstmt.executeUpdate();
            return 200;
            
        } catch (SQLException ex) {
            Logger.getLogger(Comment.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode(); // 1062
        } finally {
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
               
    }
    
}
