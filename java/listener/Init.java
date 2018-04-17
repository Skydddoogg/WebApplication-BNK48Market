/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author toppy
 */
public class Init implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String profilePictureDir = System.getenv("MARKET48_RESOURCE_PATH") + "profile_picture/";
        String paymentLogoDir = System.getenv("MARKET48_RESOURCE_PATH") + "payment_logo/";
        String idCardPictureDir = System.getenv("MARKET48_RESOURCE_PATH") + "id_card_picture/";
        String idCardPictureSelfieDir = System.getenv("MARKET48_RESOURCE_PATH") + "id_card_picture_selfie/";
        String postThumbnailDir = System.getenv("MARKET48_RESOURCE_PATH") + "post_thumbnail/";
                
        // Path for backend writing binary files
        sce.getServletContext().setAttribute("profilePictureDir", profilePictureDir);
        sce.getServletContext().setAttribute("paymentLogoDir", paymentLogoDir);
        sce.getServletContext().setAttribute("idCardPictureDir", idCardPictureDir);
        sce.getServletContext().setAttribute("idCardPictureSelfieDir", idCardPictureSelfieDir);
        sce.getServletContext().setAttribute("postThumbnailDir", postThumbnailDir);
        
        // Set view count for iterate advertisement
        int visitCount = 0;
        sce.getServletContext().setAttribute("visitCount", visitCount);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
