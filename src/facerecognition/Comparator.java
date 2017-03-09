/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facerecognition;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import static java.lang.Math.abs;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class Comparator {
    float diff;
    public boolean compareImage(BufferedImage biA, BufferedImage biB) {        
    try {
        // take buffer data from botm image files //
        
        DataBuffer dbA = biA.getData().getDataBuffer();
        int sizeA = dbA.getSize();                      
        DataBuffer dbB = biB.getData().getDataBuffer();
        int sizeB = dbB.getSize();
        // compare data-buffer objects //
        if(sizeA == sizeB) {
            for(int i=0; i<sizeA; i++) { 
                if(dbA.getElem(i) != dbB.getElem(i)) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    } 
    catch (Exception e) { 
        System.out.println("Failed to compare image files ...");
        return  false;
    }
}
    
    public float compareImagePercentage(BufferedImage biA, BufferedImage biB) {

    float percentage = 0;
    try {
        // take buffer data from both image files //
        DataBuffer dbA = biA.getData().getDataBuffer();
        int sizeA = dbA.getSize();
        DataBuffer dbB = biB.getData().getDataBuffer();
        int sizeB = dbB.getSize();
        int count = 0;
        // compare data-buffer objects //
        if (sizeA == sizeB) {

            for (int i = 0; i < sizeA; i++) {

                if (dbA.getElem(i) == dbB.getElem(i)) {
                    count = count + 1;
                }

            }
            percentage = (count * 100) / sizeA;
        } else {
            System.out.println("Both the images are not of same size");
        }

    } catch (Exception e) {
        System.out.println("Failed to compare image files ...");
    }
    return percentage;
}
    public float compareRGB(BufferedImage biA, BufferedImage biB){
        
        int height = biB.getHeight();
        int width = biB.getWidth();
        float diff = 0;
        
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                diff = diff + abs((new Color(biA.getRGB(j, i)).getRed()-new Color(biB.getRGB(j, i)).getRed()));
                diff = diff + abs((new Color(biA.getRGB(j, i)).getGreen()-new Color(biB.getRGB(j, i)).getGreen()));
                diff = diff + abs((new Color(biA.getRGB(j, i)).getBlue()-new Color(biB.getRGB(j, i)).getBlue()));
                 
            }
        }
        diff = diff/(height*width);
        return diff;
        
    }
    
}
