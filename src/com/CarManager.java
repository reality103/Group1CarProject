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
        brandList.loadFromFile("brands.txt");

        CarList carList = new CarList(brandList);
        carList.loadFromFile();

        Menu<String> menu = new Menu<>();

        int choice;
        do {
            choice = menu.int_getChoice(ops);

            switch (choice) {
                case 1:
                    brandList.listBrand();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                   String brandID = MyUtil.getString("Enter the brand ID: ", "Invalid input.");
                    brandList.searchID(brandID, 1);
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile("brands.txt");
                    break;
                case 6:
                    carList.listCars();
                    break;
                case 7:
                    carList.printBasedBrandName();
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    carList.removeCar();
                    break;
                case 10:
                    carList.updateCar();
                    break;
                case 11:
                    carList.saveToFile();
                    break;
                case 12:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice > 0 && choice < ops.size());
    }
}
