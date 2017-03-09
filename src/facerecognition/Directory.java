/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facerecognition;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author User
 */
public class Directory implements Serializable{
    private Set<String> userNames = new HashSet<String>();
    
    public void addName(String name){
        userNames.add(name);
    }
    
    public boolean search(String name){
        return userNames.contains(name);
    }
    

}
