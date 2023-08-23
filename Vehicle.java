import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;
public class Vehicle {
    private String Vid;
    private String name;
    private Port current_port;
    private Trip current_trip;
    private double fuel_capacity;
    private double current_fuel;
    private double carrying_capacity;
    private ArrayList<Container> container_list = new ArrayList<Container>();
    private static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();


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
        return Vehicle.vehicles;
    }




    public static void createNewVehicle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose 0 for ship, 1 for basic truck, 2 for reefer truck, 3 for tanker truck");
        int selection = scanner.nextInt();
        System.out.println("Please inout the name of the vehicle");
        String name = scanner.nextLine();
        System.out.println("Please input the fuel capacity of the vehicle");
        double fuel_capacity = scanner.nextDouble();
        System.out.println("Please input the carrying capacity of the vehicle");
        double carrying_capacity = scanner.nextDouble();
        String Vid = "";
        Random random_id = new Random();
        for (int i =0; i<=10; i++){
            Vid = Vid + random_id.nextInt(10);
        }
        if (selection == 0){
            ship new_ship = new ship("SH"+Vid, name, fuel_capacity, carrying_capacity);
            vehicles.add(new_ship);
        }
        else if (selection == 1){
            basic_truck new_basic_truck = new basic_truck("BT"+ Vid, name, fuel_capacity, carrying_capacity);
            vehicles.add(new_basic_truck);
        }

        else if (selection == 2){
            reefer_truck new_reefer_truck = new reefer_truck("RT"+ Vid, name, fuel_capacity, carrying_capacity);
            vehicles.add(new_reefer_truck);
        }

        else {
            tanker_truck new_tanker_truck = new tanker_truck("TT"+ Vid, name, fuel_capacity, carrying_capacity);
            vehicles.add(new_tanker_truck);
        }


    }

    public static Vehicle queryVehiclebyID(String Vid){
        for (Vehicle vehicle: vehicles){
            if (vehicle.Vid.equals(Vid)){
                return vehicle;
            }
        }
        System.out.println("Vehicle not found");
        return null;
    }

    public void accept_container(Container container){
        container_list.add(container);
    }

    public void unload_container(Container container){
        container_list.remove(container);
    }

    public void depart(){
        this.current_trip.getD_port().sendoff_vehicles(this);
        this.current_port = null;

    }

    public void arrive(Port current_port){
        this.current_port = this.current_trip.getA_port();
        this.current_trip.getA_port().receive_vehicles(this);
    }

    public void refuel(){
        this.carrying_capacity = this.fuel_capacity;
    }

    public boolean successAssessment(ArrayList<Container> container_list, double trip_length){
        double fuel_consumption_per_km = 0;
        double container_weight = 0;

        for (Container container: container_list){
            container_weight = container_weight + container.getWeight();
            if (this.Vid.startsWith("SH")){
                if (container.getName().startsWith("DS")){
                    fuel_consumption_per_km = fuel_consumption_per_km + ((Dry_Storage)container).getFuel_consumption_per_km_on_ship();
                }
                else if (container.getName().startsWith("OT")){
                    fuel_consumption_per_km = fuel_consumption_per_km + ((Open_Top)container).getFuel_consumption_per_km_on_ship();
                }

                else if (container.getName().startsWith("OS")){
                    fuel_consumption_per_km = fuel_consumption_per_km + ((Open_Side)container).getFuel_consumption_per_km_on_ship();
                }
                else if (container.getName().startsWith("RE")){
                    fuel_consumption_per_km = fuel_consumption_per_km + ((Refridgerated)container).getFuel_consumption_per_km_on_ship();
                }
                else {
                    fuel_consumption_per_km = fuel_consumption_per_km + ((Liquid)container).getFuel_consumption_per_km_on_ship();
                }

            }
            else {
                if (container.getName().startsWith("DS")){
                    fuel_consumption_per_km = fuel_consumption_per_km + ((Dry_Storage)container).getFuel_consumption_per_km_on_truck();
                }
                else if (container.getName().startsWith("OT")){
                    fuel_consumption_per_km = fuel_consumption_per_km + ((Open_Top)container).getFuel_consumption_per_km_on_truck();
                }

                else if (container.getName().startsWith("OS")){
                    fuel_consumption_per_km = fuel_consumption_per_km + ((Open_Side)container).getFuel_consumption_per_km_on_truck();
                }
                else if (container.getName().startsWith("RE")){
                    fuel_consumption_per_km = fuel_consumption_per_km + ((Refridgerated)container).getFuel_consumption_per_km_on_truck();
                }
                else {
                    fuel_consumption_per_km = fuel_consumption_per_km + ((Liquid)container).getFuel_consumption_per_km_on_truck();
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

class basic_truck extends Vehicle{
    public basic_truck(String Vid, String name, double fuel_capacity, double carrying_capacity){
        super(Vid, name, fuel_capacity,carrying_capacity);
    }
}

class tanker_truck extends Vehicle{
    public tanker_truck(String Vid, String name, double fuel_capacity, double carrying_capacity){
        super(Vid, name, fuel_capacity,carrying_capacity);
    }
}
class reefer_truck extends Vehicle{
    public reefer_truck(String Vid, String name, double fuel_capacity, double carrying_capacity){
        super(Vid, name, fuel_capacity,carrying_capacity);
    }
}
