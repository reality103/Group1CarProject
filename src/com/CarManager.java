package com;

import util.MyUtil;
import java.util.ArrayList;

public class CarManager {

    public static void main(String[] args) {
        
        ArrayList<String> ops = new ArrayList<>();
        ops.add("List all brands");
        ops.add("Add a new brand");
        ops.add("Search a brand based on its ID");
        ops.add("Update a brand");
        ops.add("Save brands to the file, named brands.txt");
        ops.add("List all cars in ascending order of brand names");
        ops.add("List cars based on a part of an input brand name");
        ops.add("Add a car");
        ops.add("Remove a car based on its ID");
        ops.add("Update a car based on its ID");
        ops.add("Save cars to file, named cars.txt");
        ops.add("Exit");

        BrandList brandList = new BrandList();
        brandList.loadFromFile("C:\\Users\\2002t\\OneDrive\\Desktop\\OOP\\SE1866_PRO192\\CarPrj\\src\\Brands.txt");

        CarList carList = new CarList(brandList);
        carList.loadFromFile("C:\\Users\\2002t\\OneDrive\\Desktop\\OOP\\SE1866_PRO192\\CarPrj\\src\\Cars.txt");

        Menu<String> menu = new Menu<>();

        int choice;
       
                
        do {
          
            choice = menu.int_getChoice(ops);

            switch (choice) {
                case 1:
                    brandList.listBrand();
                    System.out.println(); // Dòng trắng
                    System.out.println(); // Dòng trắng
                    break;
                case 2:
                    brandList.addBrand();
                    System.out.println(); // Dòng trắng
                    System.out.println(); // Dòng trắng
                    break;
                case 3:
                    String brandID = MyUtil.getString("Enter the brand ID: ", "Invalid input.");
                    brandList.searchID(brandID, 1);
                    System.out.println(); // Dòng trắng
                    System.out.println(); // Dòng trắng
                    break;
                case 4:
                    brandList.updateBrand();
                    System.out.println(); // Dòng trắng
                    System.out.println(); // Dòng trắng
                    break;
                case 5:
                    if (brandList.saveToFile("C:\\Users\\2002t\\OneDrive\\Desktop\\OOP\\SE1866_PRO192\\CarPrj\\src\\Brands.txt")) {
                        System.out.println("SAVE SUCCESSFULLY");
                    }
                    System.out.println(); // Dòng trắng
                    System.out.println(); // Dòng trắng

                    break;

                case 6:
                    carList.listCars();
                    System.out.println(); // Dòng trắng
                    System.out.println(); // Dòng trắng
                    break;
                case 7:
                    carList.printBasedBrandName();
                    System.out.println(); // Dòng trắng
                    System.out.println(); // Dòng trắng
                    break;
                case 8:
                    carList.addCar();
                    System.out.println(); // Dòng trắng
                    System.out.println(); // Dòng trắng
                    break;
                case 9:
                    carList.removeCar();
                    System.out.println(); // Dòng trắng
                    System.out.println(); // Dòng trắng
                    break;
                case 10:
                    carList.updateCar();
                    System.out.println(); // Dòng trắng
                    System.out.println(); // Dòng trắng
                    break;
                case 11:
                    if (carList.saveToFile("C:\\Users\\2002t\\OneDrive\\Desktop\\OOP\\SE1866_PRO192\\CarPrj\\src\\Cars.txt")) {
                        System.out.println("SAVE SUCCESSFULLY");
                        System.out.println();
                        System.out.println();
                    }

                    break;

                case 12:
                    System.out.println("Goodbye!");
                    System.out.println();
                    System.out.println();

                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid choice. Please try again.!!!!!");
                    System.out.println();
                    
                    break;
            }
            
            
        } while (choice != 12);
    }
}
