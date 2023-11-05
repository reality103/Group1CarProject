package com;

import util.MyUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class CarList {

    private BrandList brandList;
    private ArrayList<Car> carList;

    public CarList(BrandList blist) {
        brandList = blist;
        carList = new ArrayList<>();
    }

    public boolean loadFromFile(String filename) {

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
                    StringTokenizer stk = new StringTokenizer(line, ",:");
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

    public boolean saveToFile(String filename) {
        boolean writeMode = MyUtil.MODE_OVERRIDE;
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, writeMode))) {
            for (Car car : carList) {
                pw.println(car.toString());
            }
            pw.flush();
            return true;

        } catch (IOException e) {
            return false;
        }
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
        if (carList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getFrameID().equalsIgnoreCase(fID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchEngine(String eID) {
        if (carList.isEmpty()) {
            return -1;
        }
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
            carID = MyUtil.getString("Input car ID: ", "Car ID is required!");
            pos = searchID(carID);
            if (pos >= 0) {
                System.out.println("Car ID has already existed, please input another ID");
            }
        } while (pos != -1);

        Brand b = brandList.getUserChoice();

        color = MyUtil.getString("Input color: ", "Color is required!");
        do {
            frameID = MyUtil.getID("Input frame ID(FXXXXX): ", "Your input must be under "
                    + "the format of FXXXXX, X stands for a digit", "[F]\\d{5}$");
            pos = searchFrame(frameID);
            if (pos >= 0) {
                System.out.println("Frame ID has already existed, please input another frame ID");
            }
        } while (pos != -1);

        do {
            engineID = MyUtil.getID("Input frame ID(EXXXXX): ", "Your input must be under "
                    + "the format of EXXXXX, X stands for a digit", "[E]\\d{5}$");
            pos = searchEngine(engineID);
            if (pos >= 0) {
                System.out.println("Engine ID has already existed, please input another engine ID");
            }
        } while (pos != -1);

        carList.add(new Car(carID, b,
                color, frameID, engineID));
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
        String removedID = MyUtil.getString("Input carID you want to remove", "CarID is required");
        int pos = searchID(removedID);

        if (pos < 0) {
            System.out.println("NOT FOUND!");
            return false;
        } else {
            carList.remove(pos);
        }

        return true;
    }

    public boolean updateCar() {
        String newColor;
        Brand b;
        String newFrameID;
        String newEngineID;
        String updatedID = MyUtil.getString("Input the car ID to update: ", "Car ID is required!");
        int pos = searchID(updatedID);

        if (pos < 0) {
            System.out.println("NOT FOUND !");
            return false;
        } else {
            System.out.println("THE CAR IS FOUND, PLEASE UPDATE !!");
            System.out.println("Choose a new brand");
             b = brandList.getUserChoice();
            newColor = MyUtil.getString("Input new color: ", "Color is required!");
            do {
                newFrameID = MyUtil.getID("Input new frame ID(FXXXXX): ", "Your input must be under "
                        + "the format of FXXXXX, X stands for a digit", "[F]\\d{5}$");
                pos = searchFrame(newFrameID);
                if (pos >= 0) {
                    System.out.println("Frame ID has already existed, please input another frame ID");
                }
            } while (pos != -1);

            do {
                newEngineID = MyUtil.getID("Input new engine ID(EXXXXX): ", "Your input must be under "
                        + "the format of EXXXXX, X stands for a digit", "[E]\\d{5}$");
                pos = searchEngine(newEngineID);
                if (pos >= 0) {
                    System.out.println("Engine ID has already existed, please input another engine ID");
                }
            } while (pos != -1);
            System.out.println("BEFORE UPDATING:");
            pos = searchID(updatedID);
            System.out.println(carList.get(pos).screenString());
            // UPDATE
            carList.get(pos).setBrand(b);
            carList.get(pos).setColor(newColor);
            carList.get(pos).setEngineID(newEngineID);
            carList.get(pos).setFrameID(newFrameID);
            System.out.println("AFTER UPDATING");
            System.out.println(carList.get(pos).screenString());

        }

        return true;
    }

    public void listCars() {
        if (carList.isEmpty()) {
            System.out.println("The car list is empty");
        } else {
            Collections.sort(carList);
        }
        for (int i = 0; i < carList.size(); i++) {

            System.out.println(carList.get(i).screenString());
        }

    }

}
