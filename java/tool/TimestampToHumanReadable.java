/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 *
 * @author toppy
 */
public class TimestampToHumanReadable {
    
    public static String convert(Timestamp timestamp){
    
        Timestamp timestampNow = new Timestamp(System.currentTimeMillis());    
    
        long now = timestampNow.getTime();
        long past = timestamp.getTime();

        //in milliseconds
        long diff = now - past;

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        if(diffDays > 30){
            return "หลายเดือนที่แล้ว";
        }
        else if(diffDays > 1){
            return diffDays + " วันที่แล้ว";
        }
        else if(diffDays == 1){
            return "เมื่อวานนี้";
        }
        else if(diffHours >= 1){
            return diffHours + " ชั่วโมงที่แล้ว";
        }    
        else if(diffMinutes >= 1){
            return diffMinutes + " นาทีที่แล้ว";
        }
        else {
            return "ไม่กี่วินาทีก่อน";
        }
    }
}
