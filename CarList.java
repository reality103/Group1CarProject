
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.lang.*;

public class CarList {

    private BrandList brandList;
    private List<Car> cars;

    public CarList(BrandList bList) {
        brandList = bList;
        cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String toString() {
        String result = "";
        for (Car car : cars) {
            result += "<" + car.getCarID() + ", " + car.getBrand().getBrandId() + ", "
                    + car.getColor() + ", " + car.getFrameID() + ", " + car.getEngineID() + ">\n";
        }
        return result;
    }

    public String screenString() {
        String result = "";
        for (Car car : cars) {
            result += "<" + car.getBrand().getBrandName() + ", \"\\n\", "
                    + car.getCarID() + ", " + car.getColor() + ", "
                    + car.getFrameID() + ", " + car.getEngineID() + ">\n";
        }
        return result;
    }

    public boolean loadFromFile(String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
              
                String[] parts = line.split(", ");

                if (parts.length != 5) {
                    
                    continue;
                }

                try {
                    int carID = Integer.parseInt(parts[0].trim());
                    int brandID = Integer.parseInt(parts[1].trim());
                    String color = parts[2].trim();
                    int frameID = Integer.parseInt(parts[3].trim());
                    int engineID = Integer.parseInt(parts[4].trim());

                    
                    int pos = brandList.searchID(brandID);
                    if (pos == -1) {
                        
                        continue;
                    }

                    Brand b = brandList.get(pos);
                    Car car = new Car(carID, b, color, frameID, engineID);
                    addCar(car);
                } catch (NumberFormatException e) {
                    
                    continue;
                }
            }
        } catch (IOException e) {
            
            return false;
        }

        return true;
    }

    public boolean saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Car car : cars) {
               
                String carData = car.getCarID() + ", " + car.getBrand().getBrandId() + ", "
                        + car.getColor() + ", " + car.getFrameID() + ", " + car.getEngineID();

                writer.write(carData);
                writer.newLine(); 
            }
        } catch (IOException e) {
            System.out.println("Khong co du lieu trong file");
            e.printStackTrace();

            return false;
        }

        return true; 
    }

    public int searchID(String carID) {
        int N = cars.size();
        for (int i = 0; i < N; i++) {
            if (cars.get(i).getCarID() == carID) {
                return i; 
            }
        }
        return -1; 
    }

    public int searchFrame(String fID) {
        int N = cars.size();
        for (int i = 0; i < N; i++) {
            if (cars.get(i).getEngineID() == fID) {
                return i; 
            }
        }
        return -1; 
    }

    public int searchEngine(String eID) {
        int N = cars.size();

        for (int i = 0; i < N; i++) {
            if (cars.get(i).getEngineID() == eID) {
                return i;
            }
        }

        return -1;
    }

    public void addCar() {
        Scanner scanner = new Scanner(System.in); 

        
        Brand brand = (Brand) menu.ref_getChoice(brandList);

        System.out.print("Enter carID (must be unique): ");
        String carID = scanner.nextLine();

      
        int existingCarIndex = searchID(carID);
        if (existingCarIndex != -1) {
            System.out.println("Car with this carID already exists.");
            return;
        }

      
        System.out.print("Enter color (cannot be blank): ");
        String color = scanner.nextLine();
        if (color.trim().isEmpty()) {
            System.out.println("Color cannot be blank.");
            return;
        }

      
        System.out.print("Enter frameID (must be in the format 'F0000' and not duplicated): ");
        String frameID = scanner.nextLine();
        if (!frameID.matches("^F\\d{4}$")) {
            System.out.println("Frame ID must be in the format 'F0000'.");
            return;
        }
        int frameIndex = searchFrame(frameID);
        if (frameIndex != -1) {
            System.out.println("Frame with this frameID already exists.");
            return;
        }

        
        System.out.print("Enter engineID (must be in the format 'E0000' and not duplicated): ");
        String engineID = scanner.nextLine();
        if (!engineID.matches("^E\\d{4}$")) {
            System.out.println("Engine ID must be in the format 'E0000'.");
            return;
        }
        int engineIndex = searchEngine(engineID);
        if (engineIndex != -1) {
            System.out.println("Engine with this engineID already exists.");
            return;
        }

     
        Car car = new Car(carID, brand, color, frameID, engineID);
        cars.add(car);
        System.out.println("Car added successfully.");
    }

    public void printBasedBrandName(String aPartOfBrandName) {
        int N = cars.size();
        int count = 0;
        for (int i = 0; i < N; i++) {
            Car car = cars.get(i);
            if (car.getBrand().getBrandName().contains(aPartOfBrandName)) {
                System.out.println(car.screenString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    public boolean removeCar(String removedID) {
        int pos = searchID(removedID);
        if (pos < 0) {
            System.out.println("Not found!");
            return false;
        } else {
            cars.remove(pos);
            return true;
        }
    }

    public boolean updateCar() {
        Scanner scanner = new Scanner(System.in); 

       
        System.out.print("Enter the car ID you want to update: ");
        String updatedID = scanner.nextLine();

        
        int pos = searchID(updatedID);

        if (pos < 0) {
            System.out.println("Car not found!");
            return false;
        } else {
           
            Brand brand = (Brand) menu.ref_getChoice(brandList);

            
            System.out.print("Enter color (cannot be blank): ");
            String color = scanner.nextLine();
            if (color.trim().isEmpty()) {
                System.out.println("Color cannot be blank.");
                return false;
            }

           
            System.out.print("Enter frameID (must be in the format 'F0000' and not duplicated): ");
            String frameID = scanner.nextLine();
            if (!frameID.matches("^F\\d{4}$")) {
                System.out.println("Frame ID must be in the format 'F0000'.");
                return false;
            }
            int frameIndex = searchFrame(frameID);
            if (frameIndex != -1 && !frameID.equals(carList.get(pos).getFrameID())) {
                System.out.println("Frame with this frameID already exists.");
                return false;
            }

           
            System.out.print("Enter engineID (must be in the format 'E0000' and not duplicated): ");
            String engineID = scanner.nextLine();
            if (!engineID.matches("^E\\d{4}$")) {
                System.out.println("Engine ID must be in the format 'E0000'.");
                return false;
            }
            int engineIndex = searchEngine(engineID);
            if (engineIndex != -1 && !engineID.equals(cars.get(pos).getEngineID())) {
                System.out.println("Engine with this engineID already exists.");
                return false;
            }

           
            Car car = cars.get(pos);
            car.setBrand(brand);
            car.setColor(color);
            car.setFrameID(frameID);
            car.setEngineID(engineID);
            System.out.println("Car updated successfully.");
            return true;
        }
    }

   public void listCars() {
    Collections.sort(cars);
    
    for (int i = 0; i < cars.size(); i++) {
        Car c = cars.get(i);
        System.out.println(c.screenString());
    }
}


}
