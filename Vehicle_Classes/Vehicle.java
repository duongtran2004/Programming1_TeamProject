package Vehicle_Classes;
import Port.Port;
import Trip.Trip;
import Container_Class.*;
import FileIO.*;
import Users.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
//@JsonSubTypes ({@JsonSubTypes.Type(value = Vehicle_Classes.ship.class, name = "Vehicle_Classes.ship"), @JsonSubTypes.Type(value = Vehicle_Classes.basic_truck.class, name = "basic truck"), @JsonSubTypes.Type(value = Vehicle_Classes.reefer_truck.class, name = "reefer truck"), @JsonSubTypes.Type(value = Vehicle_Classes.tanker_truck.class, name = "basic truck") })
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String vid;
    private String name;
    private Port current_port;
    private double fuel_capacity;
    private double current_fuel;
    private double carrying_capacity;
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
        return "Vehicle_Classes.Vehicle Id: " + this.vid + "\n" + "Vehicle_Classes.Vehicle name: " + this.name + "\n" + "Current Port.Port: " + this.current_port + "\n" + "Fuel capacity: " + this.fuel_capacity + "\n" + "Current fuel: " + this.current_fuel + "\n" + "Carrying capacity: " + this.carrying_capacity + "\n" +"\n";}


    //------------------------------------Getters-------------------------------------------//
    public String getVid(){
        return this.vid;
    }
    public Port getCurrent_port(){
        return this.current_port;
    }

    public String getName(){
        return this.name;
    }

    /*public Container_Class.Open_Top.Trip.Trip getCurrent_trip() {
        return current_trip;
    }*/

    public double getFuel_capacity() {
        return fuel_capacity;
    }

    public double getCurrent_fuel() {
        return current_fuel;
    }

    public double getCarrying_capacity() {
        return carrying_capacity;
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


    public boolean equals(Vehicle vehicle) {
        if (vehicle == this) {
            System.out.println("true");
            return true;
        }

        return this.vid.equals(vehicle.vid) && this.name.equals(vehicle.name);


    }

    public static boolean createNewVehicle(String name, int type, double fuel_capacity, double carrying_capacity, Port port) throws IOException {

        String Vid = "";
        Random random_id = new Random();
        for (int i =0; i<=10; i++){
            Vid = Vid + random_id.nextInt(10);
        }
        File file = new File("vehicle.json");
        Vehicle vehicle = null;
        if (type == 0){
           ship new_ship = new ship("SH"+Vid, name, fuel_capacity, carrying_capacity, port);
            FileIOUtil.InputObjectIntoFile(new_ship, "Vehicle_Classes.ship.json");
            port.setNumberofVehiclesOnsite((int) (port.getNumberofVehiclesOnsite() + 1));
            FileIOUtil.updatePortFromFile(port);
        }
        else if (type == 1){
            basic_truck new_basic_truck = new basic_truck("BT"+Vid, name, fuel_capacity, carrying_capacity, port);
            FileIOUtil.InputObjectIntoFile(new_basic_truck, "Vehicle_Classes.basic_truck.json");
            port.setNumberofVehiclesOnsite((int) (port.getNumberofVehiclesOnsite() + 1));
            FileIOUtil.updatePortFromFile(port);
        }

        else {
            if (type == 2){
                reefer_truck new_reefer_truck = new reefer_truck("RT"+Vid, name, fuel_capacity, carrying_capacity, port);
                FileIOUtil.InputObjectIntoFile(new_reefer_truck, "Vehicle_Classes.reefer_truck.json");
                port.setNumberofVehiclesOnsite((int) (port.getNumberofVehiclesOnsite() + 1));
                FileIOUtil.updatePortFromFile(port);
            }

            else {
                tanker_truck new_tanker = new tanker_truck("TT"+Vid, name, fuel_capacity, carrying_capacity, port);
                FileIOUtil.InputObjectIntoFile(new_tanker, "Vehicle_Classes.tanker_truck.json");
                port.setNumberofVehiclesOnsite((int) (port.getNumberofVehiclesOnsite() + 1));
                FileIOUtil.updatePortFromFile(port);
            }
        }
        return true;
    }

    public static ArrayList<Vehicle> getVehicles() throws IOException {
        ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
        File SH_file = new File("Vehicle_Classes.ship.json");
        if (SH_file.exists()){
            for (ship ship: FileIOUtil.ReadShipFromFile()){
                vehicles.add((Vehicle) ship);
            }
        }
        File BT_file = new File("Vehicle_Classes.basic_truck.json");
        if (BT_file.exists()){
            for (basic_truck truck: FileIOUtil.ReadBasicTruckFromFile()){
                vehicles.add((Vehicle) truck);
            }
        }
        File RT_file = new File("reefer_ship.json");
        if (RT_file.exists()){
            for (reefer_truck truck: FileIOUtil.ReadReeferFromFile()){
                vehicles.add((Vehicle) truck);
            }
        }
        File TT_file = new File("tanker.json");
        if (TT_file.exists()){
            for (tanker_truck tanker: FileIOUtil.ReadTankerFromFile()){
                vehicles.add((Vehicle) tanker);
            }
        }
        return vehicles;
    }

    public static Vehicle queryVehiclebyID(String Vid) throws IOException {
        for (Vehicle vehicle: Vehicle.getVehicles()){
            if (vehicle.vid.equals(Vid)){
                return vehicle;
            }
        }
        System.out.println("Vehicle_Classes.Vehicle not found");
        return null;
    }

    public static void sortVehicleby_FuelCapacity(ArrayList<Vehicle> vehicle_list, boolean order){
        if (!order){
            for (int i =0; i < vehicle_list.size(); i++){
                double max_fuelcapac = vehicle_list.get(i).getFuel_capacity();
                int index = i;
                Vehicle temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getFuel_capacity() > max_fuelcapac){
                        index = k;
                        max_fuelcapac = vehicle_list.get(index).getFuel_capacity();
                    }
                }
                vehicle_list.set(i, vehicle_list.get(index));
                vehicle_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < vehicle_list.size(); i++){
                double min_fuelcapac = vehicle_list.get(i).getFuel_capacity();
                int index = i;
                Vehicle temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getFuel_capacity() < min_fuelcapac){
                        index = k;
                        min_fuelcapac = vehicle_list.get(index).getFuel_capacity();
                    }
                }
                vehicle_list.set(i, vehicle_list.get(index));
                vehicle_list.set(index, temp);
            }
        }

    }

    public static void sortVehicleby_MaxCapacity(ArrayList<Vehicle> vehicle_list, boolean order){
        if (!order){
            for (int i =0; i < vehicle_list.size(); i++){
                double max_capac = vehicle_list.get(i).getCarrying_capacity();
                int index = i;
                Vehicle temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getCarrying_capacity() > max_capac){
                        index = k;
                        max_capac = vehicle_list.get(k).getCarrying_capacity();
                    }
                }
                vehicle_list.set(i, vehicle_list.get(index));
                vehicle_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < vehicle_list.size(); i++){
                double min_capac = vehicle_list.get(i).getCarrying_capacity();
                int index = i;
                Vehicle temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getCarrying_capacity() < min_capac){
                        index = k;
                        min_capac = vehicle_list.get(k).getCarrying_capacity();
                    }
                }
                vehicle_list.set(i, vehicle_list.get(index));
                vehicle_list.set(index, temp);
            }
        }

    }

    public static void sortVehicleby_CurrentFuel(ArrayList<Vehicle> vehicle_list, boolean order){
        if (!order){
            for (int i =0; i < vehicle_list.size(); i++){
                double max_currentfuel = vehicle_list.get(i).getCurrent_fuel();
                int index = i;
                Vehicle temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getCurrent_fuel() > max_currentfuel){
                        index = k;
                        max_currentfuel = vehicle_list.get(k).getCurrent_fuel();
                    }
                }
                vehicle_list.set(i, vehicle_list.get(index));
                vehicle_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < vehicle_list.size(); i++){
                double min_currentfuel = vehicle_list.get(i).getCurrent_fuel();
                int index = i;
                Vehicle temp = vehicle_list.get(i);
                for (int k = i+1; k < vehicle_list.size(); k++){
                    if (vehicle_list.get(k).getCurrent_fuel() < min_currentfuel){
                        index = k;
                        min_currentfuel = vehicle_list.get(k).getCurrent_fuel();
                    }
                }
                vehicle_list.set(i, vehicle_list.get(index));
                vehicle_list.set(index, temp);
            }
        }

    }

    public void depart(Trip trip) throws IOException {
        this.current_port = null;
        FileIOUtil.updateVehicleFromFile(this);


    }

    public void arrive(Port port) throws IOException {
        this.current_port = port;
        FileIOUtil.updateVehicleFromFile(this);

    }

    public boolean refuel() throws IOException {
        this.current_fuel = this.fuel_capacity;
        FileIOUtil.updateVehicleFromFile(this);
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

    public static void filteringVehiclebyType() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Please select the vehicle type you want to filter:\ns: Vehicle_Classes.ship\nbt: basic trucks\nrt: reefer trucks\ntt: tanker trucks\nq: quite");
            String selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("q")){
                break;
            }
            else if (selection.equalsIgnoreCase("s")){
                ArrayList<ship> ships = ship.getShip();
                Utlity.sortingByShip(ships);
                System.out.println(Utlity.ship_Table(ships));

            }
            else if (selection.equalsIgnoreCase("bt")){
                ArrayList<basic_truck> basic_trucks = basic_truck.getBasicTruck();
                Utlity.sortingBasicTruck(basic_trucks);
                System.out.println(Utlity.basic_Table(basic_trucks));
            }
            else if (selection.equalsIgnoreCase("rt")){
                ArrayList<reefer_truck> reefer_trucks = reefer_truck.getReeferTruck();
                Utlity.sortingReefer(reefer_trucks);
                System.out.println(Utlity.reefer_Table(reefer_trucks));


            }
            else if (selection.equalsIgnoreCase("tt")) {
                ArrayList<tanker_truck> tanker_trucks = tanker_truck.getTankerTruck();
                Utlity.sortingTanker(tanker_trucks);
                System.out.println(Utlity.tanker_Table(tanker_trucks));
            }
            else {
                System.out.println("option is not available. Please choose another option");
            }
        }
    }

    public static void filteringVehiclebyPortID(ArrayList<Vehicle> vehicles) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter port id");
        String Pid = scanner.nextLine();
        Port filter_port = Port.queryPortbyID(Pid);
        for (int i =0; i < vehicles.size(); i++){
            if (!filter_port.equals(vehicles.get(i).getCurrent_port())){
                vehicles.remove(vehicles.get(i));
            }
        }
        Utlity.sortingVehicle(vehicles);
        System.out.println(vehicles);
    }

}







