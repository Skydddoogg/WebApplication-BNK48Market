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
public class Advertisement {
    
    private Integer id;
    private String html_code;
    private Integer added_by_admin_id;
    
    public Advertisement(){
        
    }

    public Advertisement(Integer id, String html_code, Integer added_by_admin_id) {
        this.id = id;
        this.html_code = html_code;
        this.added_by_admin_id = added_by_admin_id;
    }

    public Integer getId() {
        return id;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getHtml_code() {
        return html_code;
    }

    public void setHtml_code(String html_code) {
        this.html_code = html_code;
    }

    public Integer getAdded_by_admin_id() {
        return added_by_admin_id;
    }

    public void setAdded_by_admin_id(Integer added_by_admin_id) {
        this.added_by_admin_id = added_by_admin_id;
    }
    
    
    

    
}
