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
import model.Category;

/**
 *
 * @author toppy
 */
public class CategoryRepo {
    
    public ArrayList<Category> getAllCategoryBySuper(int super_id){
        ArrayList<Category> categories = new ArrayList<Category>();
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT * FROM CATEGORY WHERE super=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, super_id);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                categories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("super")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return categories;
    }

    public ArrayList<Category> getAllCategory(){
        ArrayList<Category> categories = new ArrayList<Category>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT * FROM CATEGORY WHERE super IS NULL";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                categories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("super")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return categories;
    }

    public Category getFirstEntry(ArrayList<Category> categories){
        return categories.get(0);
    }

    public Category getCategoryById(int id){
        
        Category category = null;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT * FROM CATEGORY WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            rs.next(); // don't forget!!
            category = new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("super"));
            
        } catch (SQLException ex) {
            //return null;
            Logger.getLogger(CategoryRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        
        return category;
    }
    
    public ArrayList<Category> getParentAndRootCategory(){
        ArrayList<Category> categories = new ArrayList<Category>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT *\n" +
                        "FROM `CATEGORY`\n" +
                        "WHERE `super` IS NULL\n" +
                        "\n" +
                        "UNION\n" +
                        "\n" +
                        "SELECT *\n" +
                        "FROM `CATEGORY`\n" +
                        "WHERE `super` IN (  SELECT `id`\n" +
                        "                    FROM `CATEGORY`\n" +
                        "                    WHERE `super` IS NULL)";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                categories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getInt("super")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return categories;
    }
   
}
