package com;

import java.util.*;
import java.io.*;
import java.lang.*;
import util.MyUtil;
public class BrandList {

    private ArrayList<Brand> brandList = new ArrayList<Brand>();

    public BrandList() {
    }

    public boolean loadFromFile(String filename) {
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
                    String brandID = stk.nextToken().trim();
                    String brandName = stk.nextToken().trim();
                    String soundBrand = stk.nextToken().trim();
                    double price = Double.parseDouble(stk.nextToken().trim());
                    brandList.add(new Brand(brandID, brandName, soundBrand, price));

                }

            }
            br.close(); fr.close();

        } catch (IOException e) {
            System.out.println("FAILED");
            
        }
        return true;

    }

    public boolean saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Brand brand : brandList) {
                pw.println(brand.toString());
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public int searchID(String ID) {

        if (brandList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < brandList.size(); i++) {
            if (brandList.get(i).getBrandID().equalsIgnoreCase(ID)) {
                return i;
            }

        }

        return -1;
    }

    public void searchID(String ID, int a) {
        int pos = searchID(ID);
        if (pos == -1) {
            System.out.println("NOT FOUND");
        } else {
            System.out.println(brandList.get(pos));
        }

    }

    public Brand getUserChoice() {

        return null;
    }

    public void addBrand() {
        String brandID;
        String brandName;
        String soundBrand;
        double price;
        int pos;
        do {

            brandID = MyUtil.getString("Input brand ID: ", "Brand ID is required !");
            pos = searchID(brandID);
            if (pos >= 0) {
                System.out.println(" Brand ID has been existed, please input another ID");
            }

        } while (pos != -1);

        brandName = MyUtil.getString("Input brandName: ", "Brand name is required !");
        soundBrand = MyUtil.getString("input sound brand: ", "Sound brand is required !");
        price = MyUtil.getADouble("Input price (greater than 0):", "Input price again !");
        brandList.add(new Brand(brandID, brandName, soundBrand, price));
        System.out.println("BRAND HAS BEED ADDED SUCCESSFULLY !");

    }

    public void updateBrand() {
        String brandID;
        String newBrandName;
        String newSoundBrand;
        double newPrice;
        int pos;
        brandID = MyUtil.getString("Input brand ID: ", "Brand ID is required !");
        pos = searchID(brandID);
        if (pos == -1) {
            System.out.println("NOT FOUND");
        } else {
            System.out.println("THE BRAND IS FOUND, PLEASE UPDATE !!");
            newBrandName = MyUtil.getString("Input brandName: ", "Brand name is required !");
            newSoundBrand = MyUtil.getString("input sound brand: ", "Sound brand is required !");
            newPrice = MyUtil.getADouble("Input price (greater than 0): ", "Input price again !");
            System.out.println("BEFORE UPDATING");
            System.out.println(brandList.get(pos));

            //Update
            brandList.get(pos).setBrandName(newBrandName);
            brandList.get(pos).setSoundBrand(newSoundBrand);
            brandList.get(pos).setPrice(newPrice);
            System.out.println("AFTER UPDATING");
            System.out.println(brandList.get(pos));

        }

    }

    public void listBrand() {
        if (brandList.isEmpty()) {
            System.out.println("The brand list is empty");
        }
        for (int i = 0; i < brandList.size(); i++) {
            System.out.println(brandList.get(i));

        }

    }

    public Brand getBrand(int brandPos) {
    if (brandPos >= 0 && brandPos < brandList.size()) {
        return brandList.get(brandPos);
    } else {
        return null; // Hoặc bạn có thể xử lý tùy thuộc vào logic ứng dụng của bạn
    }
}}
