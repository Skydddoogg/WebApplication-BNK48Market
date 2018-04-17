/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

/**
 *
 * @author toppy
 */
public class NumberFormat {
    public static boolean isNumeric(String str){
       try{
           Integer.parseInt(str);
       }
       catch(Exception ex){
           return false;
       }
       return true;
    }
    
    public static int toNumeric(String str){
       try{
           return Integer.parseInt(str);
       }
       catch(Exception ex){

       }
       return 0;
    }
}
