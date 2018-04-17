/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appcontext;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author toppy
 */
public class DBConnector {

    private Connection _conn;
    private static DBConnector instance;
    
    public DBConnector(){
        try {
            _conn = getDb().getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection(){
        return new DBConnector()._conn;
    }
    
    private DataSource getDb() throws NamingException {
        Context c = new InitialContext();
        return (DataSource) c.lookup("java:comp/env/db");
    }
    
}
