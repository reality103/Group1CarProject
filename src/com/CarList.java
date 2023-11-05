package com;
import util.MyUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CarList {
    private BrandList brandList;
    private ArrayList<Car> carList = new ArrayList<Car>();

    public CarList(BrandList bList) {
        brandList = bList;
    }

    public boolean loadFromFile() {
    String filename = "Cars.txt"; // Tên tệp cố định
    File file = new File(filename);
    if (!file.exists()) {
        System.out.println("FILE IS NOT EXIST");
        return false;
    }

    try {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();

            if (line.length() > 0) {
                StringTokenizer stk = new StringTokenizer(line, ",");
                String carID = stk.nextToken().trim();
                String brandID = stk.nextToken().trim();
                String color = stk.nextToken().trim();
                String frameID = stk.nextToken().trim();
                String engineID = stk.nextToken().trim();
                int pos = brandList.searchID(brandID);
                Brand brand = brandList.getBrand(pos);
                carList.add(new Car(carID, brand, color, frameID, engineID));
            }

        }
        br.close();
        fr.close();

    } catch (IOException e) {
        System.out.println("FAILED");
        return false;
    }

    return true;
}
     public static final boolean MODE_APPEND =  true;
    public static final boolean MODE_OVERRIDE = false;

public boolean saveToFile() {
   
    String filename = "Cars.txt"; // Tên tệp cố định
    try {
        File f1 = new File(filename);
        boolean  writeMode = MODE_APPEND;
        FileWriter fw = new FileWriter(f1);
        PrintWriter pw = new PrintWriter(fw);
        pw.flush();
       }
       catch (Exception e) {
        System.out.println("FAILED");
        return false;
    }
    return true;
}

    public int searchID(String carID) {
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getCarID().equalsIgnoreCase(carID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchFrame(String fID) {
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getFrameID().equalsIgnoreCase(fID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getEngineID().equalsIgnoreCase(eID)) {
                return i;
            }
        }
        return -1;
    }

    public void addCar() {
        String carID;
        String color;
        String frameID;
        String engineID;
        int pos;
        do {
            carID = MyUtil.getString("Input car ID: ", "Car ID is required !");
            pos = searchID(carID);
            if (pos >= 0) {
                System.out.println("Car ID has already existed, please input another ID");
            }
        } while (pos != -1);

        Brand brand = getBrandFromUserChoice();
        color = MyUtil.getString("Input color: ", "Color is required!");
        frameID = getFrameID();
        engineID = getEngineID();
        carList.add(new Car(carID, brand, color, frameID, engineID));
        System.out.println("CAR HAS BEEN ADDED SUCCESSFULLY !");
    }

    public void printBasedBrandName() {
        String aPartOfBrandName = MyUtil.getString("Input a part of brand name: ", "Brand name is required!");
        int count = 0;

        for (Car c : carList) {
            if (c.getBrand().getBrandName().toLowerCase().contains(aPartOfBrandName.toLowerCase())) {
                System.out.println(c.screenString());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    public boolean removeCar() {
        String removedID = MyUtil.getString("Input the car ID to remove: ", "Car ID is required!");
        int pos = searchID(removedID);

        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            carList.remove(pos);
        }

        return true;
    }

    public boolean updateCar() {
        String updatedID = MyUtil.getString("Input the car ID to update: ", "Car ID is required!");
        int pos = searchID(updatedID);

        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            Brand brand = getBrandFromUserChoice();
            String color = MyUtil.getString("Input new color: ", "Color is required!");
            String frameID = getFrameID();
            String engineID = getEngineID();
            carList.get(pos).setBrand(brand);
            carList.get(pos).setColor(color);
            carList.get(pos).setFrameID(frameID);
            carList.get(pos).setEngineID(engineID);
        }

        return true;
    }

    public void listCars() {
        carList.sort(Car::comparedTo);
        for (Car c : carList) {
            System.out.println(c.screenString());
        }
    }

    private Brand getBrandFromUserChoice() {
        return brandList.getUserChoice();
    }

    private String getFrameID() {
        String frameID;
        do {
            frameID = MyUtil.getString("Input frame ID (in the format F0000): ", "Invalid frame ID format!");
        } while (searchFrame(frameID) != -1);
        return frameID;
    }

    private String getEngineID() {
        String engineID;
        do {
            engineID = MyUtil.getString("Input engine ID (in the format E0000): ", "Invalid engine ID format!");
        } while (searchEngine(engineID) != -1);
        return engineID;
    }
    
}
