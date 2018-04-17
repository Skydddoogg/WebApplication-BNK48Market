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
import model.User;

/**
 *
 * @author toppy
 */
public class UserRepo {
    

    public ArrayList<User> getAllAwaitingUser(){
        ArrayList<User> users = new ArrayList<User>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT id FROM USER WHERE activate_status='PENDING' AND national_id IS NOT NULL";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                User user = User.findUserById(rs.getInt("id"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return users;
    }
    
    public ArrayList<User> getAllBannedUser(){
        ArrayList<User> users = new ArrayList<User>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT id FROM USER WHERE activate_status='BANNED'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                User user = User.findUserById(rs.getInt("id"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return users;
    }
    
    public ArrayList<User> getAllRejectedUser(){
        ArrayList<User> users = new ArrayList<User>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT id FROM USER WHERE activate_status='REJECTED'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                User user = User.findUserById(rs.getInt("id"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return users;
    }
    
    public ArrayList<User> getAllNonIdentifyUser(){
        ArrayList<User> users = new ArrayList<User>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT id FROM USER WHERE activate_status='PENDING' AND national_id IS NULL";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                User user = User.findUserById(rs.getInt("id"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return users;
    }
    
    public ArrayList<User> getAllActiveUser(){
        ArrayList<User> users = new ArrayList<User>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "SELECT id FROM USER WHERE activate_status='ACTIVE'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                User user = User.findUserById(rs.getInt("id"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return users;
    }
    
}
