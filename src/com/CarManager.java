/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author reality
 */
public class CarManager {
    
    
    public static void main(String[] args) {
        BrandList bl1 = new BrandList();
        
        
//        bl1.addBrand();
        bl1.loadFromFile("C:\\Users\\2002t\\OneDrive\\Desktop\\OOP\\SE1866_PRO192\\CarPrj\\src\\Brands.txt");
        bl1.listBrand();
        CarList cl1 = new CarList(bl1);
        
        cl1.loadFromFile("C:\\Users\\2002t\\OneDrive\\Desktop\\OOP\\SE1866_PRO192\\CarPrj\\src\\Cars.txt");
        cl1.listCar();
        
        
        
        
    }
    
            
    
}
