/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 *
 * @author toppy
 */
public class Uploader {
    
    private String filename;
    
    public Uploader(Part file, String saveToPath, String newName){
        InputStream filecontent = null;
        try {
            String oldFilename = getFilename(file);
            filecontent = file.getInputStream();
            byte[] buffer = new byte[filecontent.available()];
            filecontent.read(buffer);
            
            this.filename = newName + "." + getFileExtension(oldFilename);
            
            File targetFile = new File(saveToPath + this.filename);
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Uploader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                filecontent.close();
            } catch (IOException ex) {
                Logger.getLogger(Uploader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    private String getFileExtension(String filename) {
        try {
            return filename.substring(filename.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    
    }
    
    public String getFilename(){
        return this.filename;
    }
}

