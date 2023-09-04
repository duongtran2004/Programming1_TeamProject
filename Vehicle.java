import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
@JsonIdentityInfo(generator =ObjectIdGenerators.UUIDGenerator.class, property = "@vid", scope = Vehicle.class)
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String vid;
    private String name;
    //@JsonBackReference (value = "onsite_vehicles")
    private Port current_port;
    private Trip current_trip = null;
    private double fuel_capacity;
    private double current_fuel;
    private double carrying_capacity;
    //@JsonManagedReference (value = "vehicles")
    private ArrayList<Container> container_list = new ArrayList<Container>();
    public Vehicle(){

    }

    public Vehicle(String Vid, String name, double fuel_capacity, double carrying_capacity, Port port){
        this.vid = Vid;
        this.name = name;
        this.fuel_capacity = fuel_capacity;
        this.carrying_capacity = carrying_capacity;
        this.current_port = port;
    }


    public String toString(){
        return "Vehicle Id: " + this.vid + "\n" + "Vehicle name: " + this.name + "\n" + "Current Port: " + this.current_port + "\n" + "Current Trip: " + this.current_trip + "\n" + "Fuel capacity: " + this.fuel_capacity + "\n" + "Current fuel: " + this.current_fuel + "\n" + "Carrying capacity: " + this.carrying_capacity + "\n" +"\n";}


    //------------------------------------Getters-------------------------------------------//
    public String getVid(){
        return this.vid;
    }

    public Trip getCurrent_trip(String Tid){
        return this.current_trip;
    }
    public void setCurrent_trip(Trip trip){
        this.current_trip = trip;
    }

    public Port getCurrent_port(){
        return this.current_port;
    }

    public String getName(){
        return this.name;
    }

    public Trip getCurrent_trip() {
        return current_trip;
    }

    public double getFuel_capacity() {
        return fuel_capacity;
    }

    public double getCurrent_fuel() {
        return current_fuel;
    }

    public double getCarrying_capacity() {
        return carrying_capacity;
    }

    public ArrayList<Container> getContainer_list() {
        return container_list;
    }
    //-------------------------------------------------Setters------------------------------------//


    public void setVid(String vid) {
        this.vid = vid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrent_port(Port current_port) {
        this.current_port = current_port;
    }

    public void setFuel_capacity(double fuel_capacity) {
        this.fuel_capacity = fuel_capacity;
    }

    public void setCurrent_fuel(double current_fuel) {
        this.current_fuel = current_fuel;
    }

    public void setCarrying_capacity(double carrying_capacity) {
        this.carrying_capacity = carrying_capacity;
    }

    public void setContainer_list(ArrayList<Container> container_list) {
        this.container_list = container_list;
    }

    public boolean equals(Vehicle vehicle) {
        if (vehicle == this) {
            System.out.println("true");
            return true;
        }

        return this.vid.equals(vehicle.vid) && this.name.equals(vehicle.name);


    }

    public static Vehicle createNewVehicle(String name, int type, double fuel_capacity, double carrying_capacity, Port port) throws IOException {

        String Vid = "";
        Random random_id = new Random();
        for (int i =0; i<=10; i++){
            Vid = Vid + random_id.nextInt(10);
        }
        File file = new File("vehicle.json");
        Vehicle vehicle = new Vehicle();
        if (type == 0){
           vehicle = new ship("SH"+Vid, name, fuel_capacity, carrying_capacity, port);
        }
        else if (type == 1){
            vehicle = new basic_truck("BT"+Vid, name, fuel_capacity, carrying_capacity, port);
        }

        else if (type == 2){
            vehicle = new reefer_truck("RT"+Vid, name, fuel_capacity, carrying_capacity, port);
        }

        else {
            vehicle = new tanker_truck("TT"+Vid, name, fuel_capacity, carrying_capacity, port);
        }
        FileIOUtil.InputObjectIntoFile(vehicle, "vehicle.json");
        return vehicle;
    }

    public static Vehicle queryVehiclebyID(String Vid) throws IOException {
        for (Vehicle vehicle: FileIOUtil.ReadVehicleFromFile()){
            if (vehicle.vid.equals(Vid)){
                return vehicle;
            }
        }
        System.out.println("Vehicle not found");
        return null;
    }

    public void accept_container(Container container) throws IOException {
        this.container_list.add(container);
        FileIOUtil.updateObjectFromFile("vehicle.json", this);
    }

    public void unload_container(Container container) throws IOException {
        this.container_list.remove(container);
        FileIOUtil.updateObjectFromFile("vehicle.json", this);
    }

    public void depart(Trip trip) throws IOException {
        this.current_port = null;
        this.current_trip = trip;
        FileIOUtil.updateObjectFromFile("vehicle.json", this);


    }

    public void arrive(Port port) throws IOException {
        this.current_port = port;
        this.current_trip = null;
        FileIOUtil.updateObjectFromFile("vehicle.json", this);

    }

    public boolean refuel() throws IOException {
        this.current_fuel = this.fuel_capacity;
        FileIOUtil.updateObjectFromFile("vehicle.json", this);
        return true;
    }

    public boolean successAssessment(ArrayList<Container> container_list, double trip_length){
        double fuel_consumption_per_km = 0;
        double container_weight = 0;

        for (Container container: container_list){
            container_weight = container_weight + container.getWeight();
            if (vid.startsWith("SH")){
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
            if (this.fuel_capacity < total_fuel_consumption){
                System.out.println("the fuel capacity of this vehicle does not meet the fuel demand of this trip. Please unload some containers");
            }
            else{
                System.out.println("Carrying capacity exceeded!");
            }

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
    public ship(String Vid, String name, double fuel_capacity, double carrying_capacity, Port port){
        super(Vid, name, fuel_capacity,carrying_capacity, port);
    }

}


class basic_truck extends Vehicle{
    public basic_truck(String Vid, String name, double fuel_capacity, double carrying_capacity, Port port){
        super(Vid, name, fuel_capacity,carrying_capacity, port);
    }
}

class tanker_truck extends Vehicle{
    public tanker_truck(String Vid, String name, double fuel_capacity, double carrying_capacity, Port port){
        super(Vid, name, fuel_capacity,carrying_capacity, port);
    }
}
class reefer_truck extends Vehicle{
    public reefer_truck(String Vid, String name, double fuel_capacity, double carrying_capacity, Port port){
        super(Vid, name, fuel_capacity,carrying_capacity, port);
    }
}


