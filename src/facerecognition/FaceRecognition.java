/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facerecognition;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 *
 * @author User
 */
public class FaceRecognition {
    static LoginUI loginWindow = new LoginUI();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        loginWindow.setVisible(true);
    }
    
    public void processLogin(String username, File uploadedFile) throws IOException{
        Comparator comp = new Comparator();
        
        BufferedImage submittedImage = ImageIO.read(uploadedFile);
        FaceDetector fd = new FaceDetector();
        BufferedImage processedImage = fd.detectFace(submittedImage);
        
        BufferedImage storedImage = this.loadImage(username);
        
        boolean result = comp.compareImage(storedImage, processedImage);
        
        if(result){
            loginWindow.setVisible(false);
            LoggedInUI loginLanding = new LoggedInUI(username);
            loginLanding.setVisible(true);
        }else{
            //show a dialog box
        }
        
    }
    
    public void saveImage(String path, String userName){
        BufferedImage img = null;
        try {
            System.out.println("setting path");
            img = ImageIO.read(new File(path));
            
            System.out.println("detect file");
            FaceDetector fd = new FaceDetector();
            BufferedImage editedImg = fd.detectFace(img);
            
            File outputfile = new File(userName + ".png");
            System.out.println("saving File");
            ImageIO.write(editedImg, "png", outputfile);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }    
    }
    
    public BufferedImage loadImage(String fileName){
        BufferedImage img = null;
        try {
        System.out.println("setting path");
        img = ImageIO.read(new File(fileName + ".png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return img;
    }
    
}
