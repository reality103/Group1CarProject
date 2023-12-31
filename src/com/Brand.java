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

  

public class Brand implements Comparable<Brand>{
    private String brandID;
    private String brandName;
    private String soundBrand;
    private double price;
    
    // Hàm khởi tạo không tham số
    public Brand() {
    }

    // Hàm khởi tạo có tham số truyền vào
    public Brand(String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }

    // Hàm Getter và Setter
    public String getBrandID() {
        return brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getSoundBrand() {
        return soundBrand;
    }

    public double getPrice() {
        return price;
    }

   
    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    // Hàm ToString
    
    @Override
    public String toString() {
        return "<" + brandID + ", " + brandName + ", " + soundBrand + ": " + price + ">";
    }
    
    public String showProfileBrand() {
        
        return brandID + ", " + brandName + ", " + soundBrand + ": " + price;
    }

    @Override
    public int compareTo(Brand o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
    

