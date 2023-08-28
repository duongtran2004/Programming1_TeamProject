import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;
public class Vehicle implements Serializable {
    private String Vid;
    private String name;
    private Port current_port;
    private Trip current_trip;
    private double fuel_capacity;
    private double current_fuel;
    private double carrying_capacity;
    private ArrayList<Container> container_list = new ArrayList<Container>();



    public Vehicle(String Vid, String name, double fuel_capacity, double carrying_capacity){
        this.Vid = Vid;
        this.name = name;
        this.fuel_capacity = fuel_capacity;
        this.carrying_capacity = carrying_capacity;
    }

    public String toString(){
        return "Vehicle Id: " + this.Vid + "\n" + "Vehicle name: " + this.name + "\n" + "Current Port: " + this.current_port + "\n" + "Current Trip: " + this.current_trip + "\n" + "Fuel capacity: " + this.fuel_capacity + "\n" + "Current fuel: " + this.current_fuel + "\n" + "Carrying capacity: " + this.carrying_capacity + "\n";}


    public String getVid(){
        return this.Vid;
    }

    public Trip getCurrent_trip(){
        return this.current_trip;
    }
    public void setCurrent_trip(Trip trip){
        this.current_trip = trip;
    }
    public String getName(){
        return this.name;
    }

    public static ArrayList<Vehicle> getVehicles(){
        ArrayList<Vehicle> vehicle_list = new ArrayList<Vehicle>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("vehicle.txt"));
            while (true){
                try {
                    Vehicle vehicle = (Vehicle) ois.readObject();
                    vehicle_list.add(vehicle);
                } catch (EOFException e){
                    break;
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                }

            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return vehicle_list;
    }

    public static void inputVehicleIntoFile(File file, Vehicle vehicle){
        if (file.length() == 0 ){
            try{
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("vehicle.txt"));
                oos.writeObject(vehicle);
                oos.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            try{
                AppendObjectOutputStream oos = new AppendObjectOutputStream(new FileOutputStream("vehicle.txt", true));
                oos.writeObject(vehicle);
                oos.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void createNewVehicle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please inout the name of the vehicle");
        String name = scanner.nextLine();
        System.out.println("Choose 0 for ship, 1 for basic truck, 2 for reefer truck, 3 for tanker truck");
        int selection = scanner.nextInt();
        System.out.println("Please input the fuel capacity of the vehicle");
        double fuel_capacity = scanner.nextDouble();
        System.out.println("Please input the carrying capacity of the vehicle");
        double carrying_capacity = scanner.nextDouble();
        String Vid = "";
        Random random_id = new Random();
        for (int i =0; i<=10; i++){
            Vid = Vid + random_id.nextInt(10);
        }
        File file = new File("vehicle.txt");
        if (selection == 0){
           Vehicle vehicle = new ship("SH"+Vid, name, fuel_capacity, carrying_capacity);
           inputVehicleIntoFile(file, vehicle);

        }
        else if (selection == 1){
            Vehicle vehicle = new basic_truck("BT"+Vid, name, fuel_capacity, carrying_capacity);
            inputVehicleIntoFile(file, vehicle);
        }

        else if (selection == 2){
            Vehicle vehicle = new reefer_truck("RT"+Vid, name, fuel_capacity, carrying_capacity);
            inputVehicleIntoFile(file, vehicle);
        }

        else {
            Vehicle vehicle = new tanker_truck("TT"+Vid, name, fuel_capacity, carrying_capacity);
            inputVehicleIntoFile(file, vehicle);
        }
    }

    public static Vehicle queryVehiclebyID(String Vid){
        for (Vehicle vehicle: Vehicle.getVehicles()){
            if (vehicle.Vid.equals(Vid)){
                return vehicle;
            }
        }
        System.out.println("Vehicle not found");
        return null;
    }

    public void accept_container(Container container){
        this.container_list.add(container);
        ArrayList<Vehicle> vehicle_list = getVehicles();
        File file = new File("vehicle.txt");
        for (int i =0; i<vehicle_list.size(); i++){
            if (vehicle_list.get(i) == this){
                vehicle_list.set(i, this);
            }
        }
        file.delete();
        try{
            file.createNewFile();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void unload_container(Container container){
        this.container_list.remove(container);
        ArrayList<Vehicle> vehicle_list = getVehicles();
        File file = new File("vehicle.txt");
        for (int i =0; i<vehicle_list.size(); i++){
            if (vehicle_list.get(i) == this){
                vehicle_list.set(i, this);
            }
        }
        file.delete();
        try{
            file.createNewFile();
            for (Vehicle vehicle: vehicle_list){
                Vehicle.inputVehicleIntoFile(file, vehicle);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void depart(Trip trip){
        this.current_port = null;
        this.current_trip = trip;
        ArrayList<Vehicle> vehicle_list = getVehicles();
        File file = new File("vehicle.txt");
        for (int i =0; i<vehicle_list.size(); i++){
            if (vehicle_list.get(i) == this){
                vehicle_list.set(i, this);
            }
        }
        file.delete();
        try{
            file.createNewFile();
            for (Vehicle vehicle: vehicle_list){
                Vehicle.inputVehicleIntoFile(file, vehicle);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void arrive(Port current_port){
        this.current_port = this.current_trip.getA_port();
        this.current_trip = null;
        ArrayList<Vehicle> vehicle_list = getVehicles();
        File file = new File("vehicle.txt");
        for (int i =0; i<vehicle_list.size(); i++){
            if (vehicle_list.get(i) == this){
                vehicle_list.set(i, this);
            }
        }
        file.delete();
        try{
            file.createNewFile();
            for (Vehicle vehicle: vehicle_list){
                Vehicle.inputVehicleIntoFile(file, vehicle);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public boolean refuel(){
        this.current_fuel = this.fuel_capacity;
        ArrayList<Vehicle> vehicle_list = getVehicles();
        File file = new File("vehicle.txt");
        for (int i =0; i<vehicle_list.size(); i++){
            if (vehicle_list.get(i) == this){
                vehicle_list.set(i, this);
            }
        }
        file.delete();
        try{
            file.createNewFile();
            for (Vehicle vehicle: vehicle_list){
                Vehicle.inputVehicleIntoFile(file, vehicle);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean successAssessment(ArrayList<Container> container_list, double trip_length){
        double fuel_consumption_per_km = 0;
        double container_weight = 0;

        for (Container container: container_list){
            container_weight = container_weight + container.getWeight();
            if (Vid.startsWith("SH")){
                if (container.getCid().startsWith("DS")){
                    fuel_consumption_per_km = fuel_consumption_per_km + container.getFuel_consumption_per_km_on_ship();
                }
                else if (container.getCid().startsWith("OT")){
                    fuel_consumption_per_km = fuel_consumption_per_km + container.getFuel_consumption_per_km_on_ship();
                }

                else if (container.getCid().startsWith("OS")){
                    fuel_consumption_per_km = fuel_consumption_per_km + container.getFuel_consumption_per_km_on_ship();
                }
                else if (container.getCid().startsWith("RE")){
                    fuel_consumption_per_km = fuel_consumption_per_km + container.getFuel_consumption_per_km_on_ship();
                }
                else {
                    fuel_consumption_per_km = fuel_consumption_per_km + container.getFuel_consumption_per_km_on_ship();
                }

            }
            else {
                if (container.getCid().startsWith("DS")){
                    fuel_consumption_per_km = fuel_consumption_per_km + container.getFuel_consumption_per_km_on_truck();
                }
                else if (container.getCid().startsWith("OT")){
                    fuel_consumption_per_km = fuel_consumption_per_km + container.getFuel_consumption_per_km_on_truck();
                }

                else if (container.getCid().startsWith("OS")){
                    fuel_consumption_per_km = fuel_consumption_per_km + container.getFuel_consumption_per_km_on_truck();
                }
                else if (container.getCid().startsWith("RE")){
                    fuel_consumption_per_km = fuel_consumption_per_km + container.getFuel_consumption_per_km_on_truck();
                }
                else {
                    fuel_consumption_per_km = fuel_consumption_per_km + container.getFuel_consumption_per_km_on_truck();
                }
            }
        }

        double total_fuel_consumption = trip_length * fuel_consumption_per_km;

        if (this.fuel_capacity < total_fuel_consumption || this.carrying_capacity < container_weight){
            System.out.println("the fuel capacity of this vehicle does not meet the fuel demand of this trip. Please unload some containers");
            return false;
        }
        else {
            if (this.current_fuel < total_fuel_consumption ){
                System.out.println("Please refuel");
            }
            return true;
        }
    }



}

class ship extends Vehicle{
    public ship(String Vid, String name, double fuel_capacity, double carrying_capacity){
        super(Vid, name, fuel_capacity,carrying_capacity);
    }

}

class truck extends Vehicle{
    public truck(String Vid, String name, double fuel_capacity, double carrying_capacity){
        super(Vid, name, fuel_capacity,carrying_capacity);
    }
}

class basic_truck extends truck{
    public basic_truck(String Vid, String name, double fuel_capacity, double carrying_capacity){
        super(Vid, name, fuel_capacity,carrying_capacity);
    }
}

class tanker_truck extends truck{
    public tanker_truck(String Vid, String name, double fuel_capacity, double carrying_capacity){
        super(Vid, name, fuel_capacity,carrying_capacity);
    }
}
class reefer_truck extends truck{
    public reefer_truck(String Vid, String name, double fuel_capacity, double carrying_capacity){
        super(Vid, name, fuel_capacity,carrying_capacity);
    }
}
