/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facerecognition;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class Comparator {
    public boolean compareImage(BufferedImage biA, File fileB) {        
    try {
        // take buffer data from botm image files //
        
        DataBuffer dbA = biA.getData().getDataBuffer();
        int sizeA = dbA.getSize();                      
        BufferedImage biB = ImageIO.read(fileB);
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
    
}
