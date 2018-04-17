/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



/**
 *
 * @author toppy
 */
public class Payment {
    
    private Integer id;
    private String name;
    private String logo;
    
    public Payment(){
        
    }
    
    public Payment(String name, String logo){
        this.name = name;
        this.logo = logo;
    }

    public Payment(int id, String name, String logo){
        this.id = id;
        this.name = name;
        this.logo = logo;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
}
