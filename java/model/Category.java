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
public class Category {
    private Integer id;
    private String name;
    private Integer super_id;
    
    public Category(){
        
    }
    
    public Category(int id, String name, int super_id){
        this.id = id;
        this.name = name;
        this.super_id = super_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSuper_id() {
        return super_id;
    }

    public void setSuper_id(int super_id) {
        this.super_id = super_id;
    }
    
    
}
