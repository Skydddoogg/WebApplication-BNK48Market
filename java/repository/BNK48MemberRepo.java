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
import model.BNK48Member;

/**
 *
 * @author toppy
 */
public class BNK48MemberRepo {
    

    public ArrayList<BNK48Member> getAllBNK48Member(){
        ArrayList<BNK48Member> members = new ArrayList<BNK48Member>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT * FROM BNK48_MEMBER ORDER BY generation, name";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                BNK48Member member = new BNK48Member(rs.getInt("id"), rs.getInt("generation"), rs.getString("name"));
                members.add(member);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BNK48MemberRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return members;
    }
    

}
