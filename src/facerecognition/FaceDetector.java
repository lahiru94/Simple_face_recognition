/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facerecognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author User
 */
public class FaceDetector {
    
   public BufferedImage detectFace(BufferedImage img) throws FileNotFoundException, IOException{
        

        int height = img.getHeight();
        int width = img.getWidth();
        
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                
                 Color color = new Color(img.getRGB(j,i));
                 float red = color.getRed();
                 float green = color.getGreen();
                 float blue = color.getBlue();
                 float r = red/(red+green+blue);
                 float g = green/(red+green+blue);
                 double w = (r-0.33)*(r-0.33) + (g-0.33)*(g-0.33);
                 
                 double f1 = -1.3676*r*r + 1.0743*r + 0.2;
                 double f2 = -0.776*r*r + 0.5601*r + 0.18;
                 double d = (0.5*(2*red -(green + blue))/Math.pow((Math.pow((red-green),2)+(red - blue)*(green - blue)),0.5));
                 double theta = Math.acos(d) * (180 / Math.PI );
                 double h;
                 if(blue<=green){
                     h = theta;
                 }else{
                     h = 360 - theta;
                 }
                 if(g<f1 && g>f2 && w>0.001 && (h>280 || h<=30)){
                     System.out.println("skin pixel" + i+ " "  + j);
                 }else{
                     img.setRGB(j, i, Color.black.getRGB());
                     System.out.println("blacked" + i +" "+ j);
                 }
            }
        }
        return img;
    }
   
   public BufferedImage createPreview(BufferedImage img) throws FileNotFoundException, IOException{
        

        int height = img.getHeight();
        int width = img.getWidth();
        
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                
                 Color color = new Color(img.getRGB(j,i));
                 float red = color.getRed();
                 float green = color.getGreen();
                 float blue = color.getBlue();
                 float r = red/(red+green+blue);
                 float g = green/(red+green+blue);
                 double w = (r-0.33)*(r-0.33) + (g-0.33)*(g-0.33);
                 
                 double f1 = -1.3676*r*r + 1.0743*r + 0.2;
                 double f2 = -0.776*r*r + 0.5601*r + 0.18;
                 double d = (0.5*(2*red -(green + blue))/Math.pow((Math.pow((red-green),2)+(red - blue)*(green - blue)),0.5));
                 double theta = Math.acos(d) * (180 / Math.PI );
                 double h;
                 if(blue<=green){
                     h = theta;
                 }else{
                     h = 360 - theta;
                 }
                 if(g<f1 && g>f2 && w>0.001 && (h>280 || h<=30)){
                     img.setRGB(j, i, Color.white.getRGB());
                 }else{
                     img.setRGB(j, i, Color.black.getRGB());
                     
                 }
            }
        }
        return img;
    }
   
   
    
}
