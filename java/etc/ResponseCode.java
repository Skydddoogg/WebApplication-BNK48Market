/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etc;

/**
 *
 * @author toppy
 */
public enum ResponseCode {
    SUCCESS_200("การดำเนินการสำเร็จ"),
    SUCCESS_201("<strong>สำเร็จ!</strong> แก้ไขข้อมูลเรียบร้อย"),
    SUCCESS_202("<strong>สำเร็จ!</strong> ส่งคำขอยืนยันตัวตนเรียบร้อย"),
    SUCCESS_203("<strong>สำเร็จ!</strong> ลบข้อมูลเรียบร้อย"),
    SUCCESS_204("<strong>สำเร็จ!</strong> ปรับปรุงสถานะของผู้ใช้เรียบร้อย"),
    SUCCESS_205("<strong>สำเร็จ!</strong> เพิ่มข้อมูลเรียบร้อย"),
    ERR_400("กรุณาเข้าสู่ระบบ หรือ ลงทะเบียนเพื่อเข้าถึงส่วนนี้"),
    ERR_403("<strong>ผิดพลาด!</strong> ชื่อผู้ใช้หรือรหัสผ่านไม่ถูกต้อง"),
    ERR_1048("<strong>ผิดพลาด!</strong> กรุณากรอกข้อมูลให้ครบถ้วน"),
    ERR_1062("<strong>ผิดพลาด!</strong> ข้อมูลนี้มีในระบบแล้ว"),
    ERR_9999("เกิดข้อผิดพลาด");
    
    private String message;
    
    ResponseCode(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return message;
    }
    
}
