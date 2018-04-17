/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import appcontext.DBConnector;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import model.enums.UserStatus;
import tool.Uploader;

/**
 *
 * @author toppy
 */
public class User {
    
    private Integer id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String sex;
    private String phone;
    private String profile_picture;
    private String facebook_id;
    private String twitter_id;
    private String line_id;
    private String national_id;
    private String id_card_picture;
    private String id_card_picture_selfie;
    private UserStatus activate_status;
    private Integer activate_by_admin_id;
    private Timestamp activate_timestamp;
    
    public User() {
        
    }
    
    public User(String firstname, String lastname, String username, String password, String email, String sex) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getTwitter_id() {
        return twitter_id;
    }

    public void setTwitter_id(String twitter_id) {
        this.twitter_id = twitter_id;
    }

    public String getLine_id() {
        return line_id;
    }

    public void setLine_id(String line_id) {
        this.line_id = line_id;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getId_card_picture() {
        return id_card_picture;
    }

    public void setId_card_picture(String id_card_picture) {
        this.id_card_picture = id_card_picture;
    }

    public String getId_card_picture_selfie() {
        return id_card_picture_selfie;
    }

    public void setId_card_picture_selfie(String id_card_picture_selfie) {
        this.id_card_picture_selfie = id_card_picture_selfie;
    }

    public UserStatus getActivate_status() {
        return activate_status;
    }

    public void setActivate_status(UserStatus activate_status) {
        this.activate_status = activate_status;
    }

    public Integer getActivate_by_admin_id() {
        return activate_by_admin_id;
    }

    public void setActivate_by_admin_id(Integer activate_by_admin_id) {
        this.activate_by_admin_id = activate_by_admin_id;
    }

    public Timestamp getActivate_timestamp() {
        return activate_timestamp;
    }

    public void setActivate_timestamp(Timestamp activate_timestamp) {
        this.activate_timestamp = activate_timestamp;
    }
    
    public boolean auth(String username, String password){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        boolean isSuccess = false;
        try {
            String sql = "SELECT * FROM USER WHERE username=? AND password=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                this.id = rs.getInt("id");
                this.username = rs.getString("username");
                this.password = rs.getString("password");
                this.firstname = rs.getString("firstname");
                this.lastname = rs.getString("lastname");
                this.email = rs.getString("email");
                this.sex = rs.getString("sex");
                this.phone = rs.getString("phone");
                this.profile_picture = rs.getString("profile_picture");
                this.facebook_id = rs.getString("facebook_id");
                this.twitter_id = rs.getString("twitter_id");
                this.line_id = rs.getString("line_id");
                this.national_id = rs.getString("national_id");
                this.id_card_picture = rs.getString("id_card_picture");
                this.id_card_picture_selfie = rs.getString("id_card_picture_selfie");
                String activate_status_str = rs.getString("activate_status");
                
                if(activate_status_str != null)
                    switch(activate_status_str){
                        case "ACTIVE":
                            this.activate_status = UserStatus.ACTIVE;
                            break;
                        case "PENDING":
                            this.activate_status = UserStatus.PENDING;
                            break;
                        case "REJECTED":
                            this.activate_status = UserStatus.REJECTED;
                            break;
                        case "BANNED":
                            this.activate_status = UserStatus.BANNED;
                            break;
                    }
                this.activate_by_admin_id = rs.getInt("activate_by_admin_id");
                this.activate_timestamp = rs.getTimestamp("activate_timestamp");
                
                isSuccess = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return isSuccess;
    }
    
    public void changeProfilePicture(Part profilePicture, String saveToPath){
        // Delete old file
        if(this.profile_picture != null && !this.profile_picture.equals("default.png")){
            try{
                File file = new File(saveToPath + this.profile_picture);
                file.delete();
            }
            catch(Exception ex){ }
        }
        // Upload the new ones
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String filename = id.toString() + "_" + timestamp.getTime();
        Uploader up = new Uploader(profilePicture, saveToPath, filename);
        this.profile_picture = up.getFilename();
    }
    
    public int insert(){
        PreparedStatement pstmt = null;
        Connection conn = DBConnector.getConnection();
        
        try {
            String sql = "INSERT INTO USER(firstname, lastname, username, password, email, sex) VALUES(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, this.getFirstname());
            pstmt.setString(2, this.getLastname());
            pstmt.setString(3, this.getUsername());
            pstmt.setString(4, this.getPassword());
            pstmt.setString(5, this.getEmail());
            pstmt.setString(6, this.getSex());
            
            pstmt.executeUpdate();
            return 200;
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getErrorCode(); // 1062
        } finally {
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
               
    }
    
    public void save(){
        PreparedStatement pstmt = null;
        Connection conn = DBConnector.getConnection();
        
        if(firstname.isEmpty())
            firstname = null;
        
        try {
            String sql = "UPDATE USER SET password=NULLIF(?,''), firstname=NULLIF(?,''), "
                    + "lastname=NULLIF(?,''), sex=NULLIF(?,''), phone=NULLIF(?,''), email=NULLIF(?,''), "
                    + "facebook_id=NULLIF(?,''), twitter_id=NULLIF(?,''), line_id=NULLIF(?,''), "
                    + "profile_picture=NULLIF(?,''), national_id=NULLIF(?,''), id_card_picture=NULLIF(?,''), "
                    + "id_card_picture_selfie=NULLIF(?,''), activate_status=NULLIF(?,''), activate_by_admin_id=?, activate_timestamp=NULLIF(?,'')"
                    + " WHERE id=?";
            
            int i = 1;
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(i++, this.password);
            pstmt.setString(i++, this.firstname);
            pstmt.setString(i++, this.lastname);
            pstmt.setString(i++, this.sex);
            pstmt.setString(i++, this.phone);
            pstmt.setString(i++, this.email);
            pstmt.setString(i++, this.facebook_id);
            pstmt.setString(i++, this.twitter_id);
            pstmt.setString(i++, this.line_id);
            pstmt.setString(i++, this.profile_picture);
            pstmt.setString(i++, this.national_id);
            pstmt.setString(i++, this.id_card_picture);
            pstmt.setString(i++, this.id_card_picture_selfie);
            
            
            switch(activate_status){
                case ACTIVE:
                    pstmt.setString(i++, "ACTIVE");
                    break;
                case PENDING:
                    pstmt.setString(i++, "PENDING");
                    break;
                case REJECTED:
                    pstmt.setString(i++, "REJECTED");
                    break;
                case BANNED:
                    pstmt.setString(i++, "BANNED");
                default:
                    pstmt.setString(i++, "");
                    break;
            }
            
            if(this.activate_by_admin_id == null){
                pstmt.setInt(i++, this.activate_by_admin_id);
            }
            else{
                pstmt.setNull(i++, java.sql.Types.INTEGER);
            }
            
            pstmt.setTimestamp(i++, this.activate_timestamp);
            pstmt.setInt(i++, this.id);
            
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
    }
    
    public static User findUserById(int id) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        boolean isSuccess = false;
        try {
            String sql = "SELECT * FROM USER WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();
            
            while(rs.next()){
                user = new User();
                user.id = rs.getInt("id");
                user.username = rs.getString("username");
                user.password = rs.getString("password");
                user.firstname = rs.getString("firstname");
                user.lastname = rs.getString("lastname");
                user.email = rs.getString("email");
                user.sex = rs.getString("sex");
                user.phone = rs.getString("phone");
                user.profile_picture = rs.getString("profile_picture");
                user.facebook_id = rs.getString("facebook_id");
                user.twitter_id = rs.getString("twitter_id");
                user.line_id = rs.getString("line_id");
                user.national_id = rs.getString("national_id");
                user.id_card_picture = rs.getString("id_card_picture");
                user.id_card_picture_selfie = rs.getString("id_card_picture_selfie");
                String activate_status_str = rs.getString("activate_status");
                
                if(activate_status_str != null)
                    switch(activate_status_str){
                        case "ACTIVE":
                            user.activate_status = UserStatus.ACTIVE;
                            break;
                        case "PENDING":
                            user.activate_status = UserStatus.PENDING;
                            break;
                        case "REJECTED":
                            user.activate_status = UserStatus.REJECTED;
                            break;
                        case "BANNED":
                            user.activate_status = UserStatus.BANNED;
                            break;
                    }
                user.activate_by_admin_id = rs.getInt("activate_by_admin_id");
                user.activate_timestamp = rs.getTimestamp("activate_timestamp");
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
        return user;
    }  
    
    public void fetchActivationStatus(){
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = DBConnector.getConnection();
        try {
            String sql = "SELECT activate_status, activate_by_admin_id, activate_timestamp FROM USER WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, this.id);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                String activate_status_str = rs.getString("activate_status");
                
                if(activate_status_str != null)
                    switch(activate_status_str){
                        case "ACTIVE":
                            this.activate_status = UserStatus.ACTIVE;
                            break;
                        case "PENDING":
                            this.activate_status = UserStatus.PENDING;
                            break;
                        case "REJECTED":
                            this.activate_status = UserStatus.REJECTED;
                            break;
                        case "BANNED":
                            this.activate_status = UserStatus.BANNED;
                            break;
                    }
                this.activate_by_admin_id = rs.getInt("activate_by_admin_id");
                this.activate_timestamp = rs.getTimestamp("activate_timestamp");
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { pstmt.close(); } catch (Exception e) { /* ignored */ }
            try { conn.close(); } catch (Exception e) { /* ignored */ }
        }
    }
    

}
