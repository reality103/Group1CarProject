/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author reality
 */
public class CarList {
    
    private BrandList bList;
    private ArrayList<Car> carList = new ArrayList<Car> ();

    public CarList(BrandList bList) {
        this.bList = bList;
    }
    
    public boolean loadFromFile(String filename){
        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("FILE IS NOT EXIST");
            return false;

        }
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.length() > 0) {
                    StringTokenizer stk = new StringTokenizer(line, ",:");
                   String carID = stk.nextToken().trim();
                   String brandID = stk.nextToken().trim();
                   String color = stk.nextToken().trim();
                   String frameID = stk.nextToken().trim();
                   String engineID = stk.nextToken().trim();
                   
                   int pos = bList.searchID(brandID);
                   Brand b = bList.getBrandList().get(pos);
                   
                   carList.add(new Car(carID, b, color, frameID, engineID));
                   
                   

                }

            }
            br.close(); fr.close();

        } catch (IOException e) {
            System.out.println("FAILED");
            
        }
        return true;
        
        
    }
    
    
  
    
    public void listCar() {
        if (carList.isEmpty())
            System.out.println("The car list is empty");
        for (int i = 0; i < carList.size(); i++) {
            
            System.out.println(carList.get(i).screenString());
            
        }
        
        
    }

   
    
            
    
}
