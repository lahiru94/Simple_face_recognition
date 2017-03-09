/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facerecognition;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        
        float diff = comp.compareRGB(submittedImage, storedImage);
        
//        float percentage = comp.compareImagePercentage(storedImage, processedImage);
//        System.out.println(percentage);
        
//        boolean result = comp.compareImage(storedImage, processedImage);
//        
        if(diff<50){
            loginWindow.setVisible(false);
            LoggedInUI loginLanding = new LoggedInUI(username);
            loginLanding.setVisible(true);
        }else{
            //show a dialog box
            System.out.println("face doesn't match");
        }
        
    }
    
    public void saveImage(String path, String userName) throws ClassNotFoundException{
        BufferedImage img = null;
        Directory d = null;
        try {
            FileInputStream fileIn = new FileInputStream("directory.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            d = (Directory) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException e) {
            d = new Directory();
        }
        
        if (!d.search(userName)){
            try {
            System.out.println("setting path");
            img = ImageIO.read(new File(path));
            
            System.out.println("detect file");
            FaceDetector fd = new FaceDetector();
            BufferedImage editedImg = fd.detectFace(img);
            
            
            
            File outputfile = new File(userName + ".png");
            
            System.out.println("saving File");
            ImageIO.write(editedImg, "png", outputfile);
            
            
            BufferedImage editedImgPreview = fd.createPreview(img);
            File outputfile2 = new File(userName + "Preview.png");
            ImageIO.write(editedImgPreview, "png", outputfile2);
            
            //adding name to directory
            d.addName(userName);
            FileOutputStream fileOut = new FileOutputStream("directory.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(d);
            out.close();
            fileOut.close();
            
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
        
        }else{
            System.out.println("user name taken!");
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
