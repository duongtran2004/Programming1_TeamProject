import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
@JsonIdentityInfo(generator =ObjectIdGenerators.UUIDGenerator.class, property = "@eid", scope = User.class)
public class User implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
    private String eid;
    private String username;
    private String password;
    private String role;

    public User(){

    }
    public User(String Eid, String username, String password, String role){
        this.eid = Eid;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean equals(User user) {
        if (user == this) {
            System.out.println("true");
            return true;
        }

        return this.eid.equals(user.eid) && this.username.equals(user.username);
    }



    //-----------------------------------------------Getters-----------------------------//
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEid(){
        return this.eid;
    }

    public String getRole() {
        return role;
    }

    //-------------------------------------------------Setters---------------------------------//


    public void setEid(String eid) {
        eid = eid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

class Port_Manager extends User{
    private Port port;
    public Port_Manager(){

    }



    public Port_Manager (String Eid, String username, String password, String role, Port port){
        super(Eid, username, password, role);
        this.port = port;
    }

    public static void addPortManager(String username, String password, Port port) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String Eid = "";
        Random random = new Random();
        for (int i = 1; i<=10; i++){
            Eid = Eid + random.nextInt(10);
        }
        User new_user = new Port_Manager(Eid, username, password, "Port Manager", port);
        FileIOUtil.InputObjectIntoFile(new_user, "port_manager.json");
    }

    public String toString(){
        return "username: " + this.getPassword() + "\n" + "port: " + this.port.getName() + "\n" + "Employee ID: " + this.getEid();
    }

    //----------------------------------------Getter-------------------------------------//
    public Port getPort(){
        return this.port;
    }
    //----------------------------------------Setter-------------------------------------//
    public void setPort(Port port) {
        this.port = port;
    }

    public static Port_Manager queryManagerbyID(String Eid) throws IOException {
        for (Port_Manager manager: FileIOUtil.ReadManagerFromFile()){
            if (manager.getEid().equals(Eid)){
                return manager;
            }
        }
        System.out.println("Vehicle not found");
        return null;
    }

    //This method allows Port Manager to view the outgoing trips after/before inputted dates
    public void outgoingTrip_query() throws IOException {
        Date after = null;
        Date before = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to filter by date after? (Y-N)");
        if (scanner.nextLine().equals("Y")){
            while (true){
                System.out.println("Please enter the year");
                int year = scanner.nextInt();
                System.out.println("Please enter the month");
                int month = scanner.nextInt();
                System.out.println("Please enter the date");
                int date = scanner.nextInt();
                after = new Date(year,month,date);
                System.out.println("Filter After: " + after + ". " + "Would you like to enter again? (Y-N)" );
                String response = scanner.next();
                scanner.nextLine();
                if (response.equals("N")){
                    break;
                }
            }
            System.out.println("Would you like to filter by date before? (Y-N)");
            if (scanner.nextLine().equals("Y")){

            }
            while (true){
                System.out.println("Please enter the year");
                int year = scanner.nextInt();
                System.out.println("Please enter the month");
                int month = scanner.nextInt();
                System.out.println("Please enter the date");
                int date = scanner.nextInt();
                before = new Date(year,month,date);
                System.out.println("Filter Before " + before + ". " + "Would you like to enter again? (Y-N)" );
                String response = scanner.next();
                scanner.nextLine();
                if (response.equals("N")){
                    break;
                }
            }
            Trip.outgoingTrip_query(this.port, after, before);

        }






    }
    //This method allows Port Manager to see the incoming trip after/before inputted dates.
    public void incomingTrip_query() throws IOException {
        Date after = null;
        Date before = null;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Please enter the year");
            int year = scanner.nextInt();
            System.out.println("Please enter the month");
            int month = scanner.nextInt();
            System.out.println("Please enter the date");
            int date = scanner.nextInt();
            after = new Date(year,month,date);
            System.out.println("Filter After: " + after + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.next();
            scanner.nextLine();
            if (response.equals("N")){
                break;
            }
        }
        System.out.println("Would you like to filter by date before? (Y-N)");
        if (scanner.nextLine().equals("Y")){

        }
        while (true){
            System.out.println("Please enter the year");
            int year = scanner.nextInt();
            System.out.println("Please enter the month");
            int month = scanner.nextInt();
            System.out.println("Please enter the date");
            int date = scanner.nextInt();
            before = new Date(year,month,date);
            System.out.println("Filter Before " + before + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.next();
            scanner.nextLine();
            if (response.equals("N")){
                break;
            }
        }
       Trip.incomingTrip_query(this.port, after, before);
    }

    //this method is called when the port manager wants to register a new trip. It will call the registeringTrip method from the Trip class in its body
    public void RegisteringTrip() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Date date_of_departure = new Date();
        Date date_of_arrival = new Date();
        Port port_of_arrival = new Port();
        ArrayList<Container> containers = new ArrayList<Container>();
        Vehicle selected_vehicle = new Vehicle();
        while (true){
            System.out.println("Please input the departure year of the trip");
            int year = scanner.nextInt();
            System.out.println("Please input the departure month of the trip");
            int month = scanner.nextInt();
            System.out.println("Please input the departure day of the trip");
            int date = scanner.nextInt();
            date_of_departure = new Date(year, month, date);
            System.out.println("Date of departure: " + date_of_departure + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.next();
            scanner.nextLine();
            if (response.equals("N")){
                break;
            }

        }
        while (true){
            System.out.println("Please input the arrival year of the trip");
            int year = scanner.nextInt();
            System.out.println("Please input the arrival month of the trip");
            int month = scanner.nextInt();
            System.out.println("Please input the arrival day of the trip");
            int date = scanner.nextInt();
            date_of_arrival = new Date(year, month, date);
            System.out.println("Date of arrival: " + date_of_arrival + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.next();
            scanner.nextLine();
            if (response.equals("N")){
                break;
            }

        }

        while (true){
            System.out.println("Please enter the Id of the destination port");
            String Pid = scanner.next();
            scanner.nextLine();
            port_of_arrival = Port.queryPortbyID(Pid);
            System.out.println("Please check if the following Port is correct.\n" + port_of_arrival +"\n" + "Would you like to enter again? (Y-N)" );
            String response = scanner.nextLine();
            if (response.equals("N")){
                break;
            }

        }

        while (true){
            containers = new ArrayList<Container>();
            while (true){
                System.out.println("Please select the container you would like to load");
                String Cid = scanner.nextLine();
                Container container = Container.queryContainerbyID(Cid);
                containers.add(container);
                System.out.println("Would you like to add more containers? (Y-N)");
                String rep = scanner.nextLine();
                if (rep.equals("N")){
                    break;
                }
            }
            System.out.println("Please check if the following containers are correct.\n" + containers +"\n" + "Would you like to enter again? (Y-N)" );
            String response = scanner.nextLine();
            if (response.equals("N")){
                break;
            }
        }

        while (true){
            //Check vehicle availability
            ArrayList<Vehicle> deployable_vehicles = new ArrayList<Vehicle>();
            deployable_vehicles = Trip.checking_vehicle_eligibility(this.port, port_of_arrival, date_of_arrival, date_of_departure, containers);
            //Choose Vehicle
            String prompt = "Please select from the following vehicles ";
            for (int i =0; i <deployable_vehicles.size(); i++){
                prompt = prompt + i + ": " + deployable_vehicles.get(i).getName() + "\n";
            }
            System.out.println(prompt);
            int selection = scanner.nextInt();
            selected_vehicle = deployable_vehicles.get(selection);
            System.out.println("Please check if the following vehicle is correct.\n" + selected_vehicle +"\n" + "Would you like to enter again? (Y-N)" );
            String response = scanner.next();
            scanner.nextLine();
            if (response.equals("N")){
                break;
            }
        }
        Trip.registeringTrip(this.port, date_of_departure, date_of_arrival, port_of_arrival, containers, selected_vehicle);
    }
    //This method is called at the commencement of each outgoing trip. It will check the vehicle out of the port.
    public void commenceTrip() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the id of the trip");
        String Tid = scanner.nextLine();
        Trip trip = Trip.queryByName(Tid);
        Port main_port = Port.queryPortbyID(this.port.getPid());
        main_port.checkout_vehicles(trip.getVehicle());
        trip.getVehicle().depart(trip);
        for (Container container: trip.getTo_be_delivered_containers()){
            main_port.checkout_containers(container);
            container.enterPort(main_port);
        }

    }
    //this method is called after a vehicle reaches its destination port and finishes its trip. It will check the vehicle into the port
    public void completeTrip() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the id of the trip");
        String Tid = scanner.nextLine();
        Trip trip = Trip.queryByName(Tid);
        trip.completeTrip();
        Port main_port = Port.queryPortbyID(this.port.getPid());
        main_port.accept_vehicles(trip.getVehicle());
        trip.getVehicle().arrive(main_port);

        for (Container container: trip.getTo_be_delivered_containers()){
            main_port.accept_containers(container);
            container.leavePort();
        }
    }
    //this method is called when the port manager wants to load containers off a vehicle
    public void loadingContainersonVehicle() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the id of the trip");
        String tid = scanner.nextLine();
        Trip trip = Trip.queryByName(tid);
        ArrayList<Container> containers_to_be_loaded_on = trip.getTo_be_delivered_containers();
        for (Container container: containers_to_be_loaded_on){
            trip.getVehicle().accept_container(container);
            container.loadedonVehicle(trip.getVehicle());
        }
    }
    public void loadingContaineroffVehicle() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the id of the trip");
        String tid = scanner.nextLine();
        Trip trip = Trip.queryByName(tid);
        ArrayList<Container> containers_to_be_loaded_off = trip.getTo_be_delivered_containers();
        for (Container container: containers_to_be_loaded_off){
            trip.getVehicle().unload_container(container);
            container.loadedoffVehicle();
        }
    }

    }
















class System_Admin extends  User{
    public System_Admin(String Eid, String username, String password, String role){
        super(Eid,username, password, role);
    }

    //-----------------------------------------------CREATE METHODS----------------------------------------------------//

    public static void addManager() throws IOException {
        String username;
        String password;
        Port port = new Port();
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Please input your username here: ");
            username = scanner.nextLine();
            System.out.println("Username: " + username + "." + "Would you like to input again? (Y/N)");
            String rep = scanner.nextLine();
            if (rep.equals("N")){
                break;
            }
        }
        while (true){
            System.out.println("Please input your password here: ");
            password = scanner.nextLine();
            System.out.println("Password: " + password + "." + "Would you like to input again? (Y/N)");
            String rep = scanner.nextLine();
            if (rep.equals("N")){
                break;
            }
        }
        while (true){
            String prompt = "Please assign the new manager to a port: ";
            for (int i = 0; i < FileIOUtil.ReadPortFromFile().size(); i++){
                prompt = prompt + i +": "+ (FileIOUtil.ReadPortFromFile().get(i)).getName() + " ";
            }
            System.out.println(prompt);
            int selection = scanner.nextInt();
            scanner.nextLine();
            port = FileIOUtil.ReadPortFromFile().get(selection);
            System.out.println("Please check port detail:\n" + password + "\n" + "Would you like to choose another port? (Y/N)");
            String rep = scanner.nextLine();
            if (rep.equals("N")){
                break;
            }

        }

        Port_Manager.addPortManager(username, password, port);
    }
    public static Port addPort() throws IOException {
        String name = "";
        double latitude = 0;
        double longtitude = 0;
        double storing_capacity = 0;
        boolean landing_ability = false;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Please input the name of the new port: ");
            name = scanner.nextLine();
            System.out.println("Port name: " + name + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.nextLine();
            if (response.equals("N")){
                break;
            }
        }
        while (true){
            String hemisphere = "";
            double degree = 0;
            double minute = 0;
            double second = 0;
            while (true){
                System.out.println("Please input the port latitude (N or S)");
                System.out.println("Please Choose Hemisphere");
                hemisphere = scanner.nextLine();
                System.out.println("Please input degree (maximum 180)");
                degree = scanner.nextDouble();
                System.out.println("Please input minute");
                minute = scanner.nextDouble();
                System.out.println("Please input second");
                second = scanner.nextDouble();
                scanner.nextLine();
                double total_degree = (double) Math.round((degree + minute/60 + second/3600)*100)/100;
                if (total_degree <= 180){
                    if (hemisphere.equals("N")){
                        latitude = total_degree;
                    }
                    else {
                        latitude = -total_degree;
                    }
                    break;
                }
                else {
                    System.out.println("Incorrect latitude. Please enter again");
                }
            }

            System.out.println("Port latitude: " + degree + " degree " + minute + " minutes " + second + " seconds " + hemisphere + ". " +"Would you like to enter again? (Y-N)" );
            String response = scanner.nextLine();
            if (response.equals("N")){
                break;
            }
        }
        while (true){
            String hemisphere = "";
            double degree = 0;
            double minute = 0;
            double second = 0;
            while (true){
                System.out.println("Please input the port longtitude");
                System.out.println("Please Choose Hemisphere (W or E)");
                hemisphere = scanner.nextLine();
                System.out.println("Please input degree (maximum 180)");
                degree = scanner.nextDouble();
                System.out.println("Please input minute");
                minute = scanner.nextDouble();
                System.out.println("Please input second");
                second = scanner.nextDouble();
                scanner.nextLine();
                double total_degree = (double) Math.round((degree + minute/60 + second/3600)*100)/100;
                if (total_degree <= 180){
                    if (hemisphere.equals("E")){
                        longtitude = total_degree;
                    }
                    else {
                        longtitude = -total_degree;
                    }
                    break;
                }
                else {
                    System.out.println("Incorrect latitude. Please enter again");
                }
            }

            System.out.println("Port longtitude: " + degree + " degree " + minute + " minutes " + second + " seconds" + hemisphere + ". " +"Would you like to enter again? (Y-N)" );
            String response = scanner.nextLine();
            if (response.equals("N")){
                break;
            }
        }

        while (true){
            System.out.println("PLease input its storing capacity");
            storing_capacity = scanner.nextDouble();
            System.out.println("Port storing capacity: " + storing_capacity + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.next();
            scanner.nextLine();
            if (response.equals("N")){
                break;
            }

        }
        while (true){
            System.out.println("Please input its landing ability");
            landing_ability = scanner.nextBoolean();
            System.out.println("Port landing ability: " + landing_ability + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.next();
            scanner.nextLine();
            if (response.equals("N")){
                break;
            }

        }

        return Port.createNewPort(name, latitude, longtitude, storing_capacity, landing_ability);
    }
    public static Vehicle addVehicle() throws IOException {
        String name ="";
        int type = 0;
        double fuel_capacity = 0;
        double carrying_capacity = 0;
        Port port = new Port();
        Scanner scanner = new Scanner(System.in);
        String vehicle_type = "";
        while (true){
            System.out.println("Please inout the name of the vehicle");
            name = scanner.nextLine();
            System.out.println("Vehicle name: " + name + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.nextLine();
            if (response.equals("N")){
                break;
            }

        }
        while (true){
            System.out.println("Choose 0 for ship, 1 for basic truck, 2 for reefer truck, 3 for tanker truck");
            int selection = scanner.nextInt();
            if (selection == 0){
                vehicle_type = "ship";
            }
            else if (selection == 1){
                vehicle_type = "basic truck";
            }
            else if (selection == 2){
                vehicle_type= "reefer truck";
            }
            else {
                vehicle_type = "tanker truck";
            }

            System.out.println("Vehicle type: " + vehicle_type + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.next();
            scanner.nextLine();
            if (response.equals("N")){
                break;
            }
        }
        while (true){
            System.out.println("Please input the fuel capacity of the vehicle");
            fuel_capacity = scanner.nextDouble();
            System.out.println("Fuel capacity: " + fuel_capacity + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.next();
            scanner.nextLine();
            if (response.equals("N")){
                break;
            }
        }
        while (true){
            System.out.println("Please input the carrying capacity of the vehicle");
            carrying_capacity = scanner.nextDouble();
            System.out.println("Carrying capacity: " + carrying_capacity + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.next();
            scanner.nextLine();
            if (response.equals("N")){
                break;
            }

        }
        while (true){
            boolean condition = false;
            while (condition == false){
                System.out.println("Please input the ID of the port you would like to assign this vehicle to");
                String Pid = scanner.next();
                scanner.nextLine();
                port = Port.queryPortbyID(Pid);
                if (!port.isLandingAbility() && type!=0){
                    System.out.println("Trucks cannot be assigned to this port. Please select another port");
                }
                else {
                    condition = true;
                }
            }
            System.out.println("Please check if the following port is correct\n" +port + "\n" + "Would you like to enter again? (Y-N)" );
            String response = scanner.next();
            scanner.nextLine();
            if (response.equals("N")){
                break;
            }
        }
        Vehicle new_vehicle = Vehicle.createNewVehicle(name, type, fuel_capacity, carrying_capacity, port);
        new_vehicle.getCurrent_port().accept_vehicles(new_vehicle);
        return new_vehicle;


    }
    public static Container addContainer() throws IOException {
        int type = 0;
        String name = "";
        Port port = new Port();
        double weight = 0;
        Scanner scanner = new Scanner(System.in);
        String Pid = "";
        while (true){
            String container_type = "";
            while (true){
                System.out.println("Choose 0 for Dry Storage, 1 for Open Top, 2 for Open Side, 3 for Refridgerated, 4 for Liquid");
                type = scanner.nextInt();
                if (type >=0 && type <= 4){
                    break;
                }
            }
            if (type == 0){
                container_type = "Dry Storage";
            }
            else if (type == 1){
                container_type = "Open Top";
            }
            else if (type == 2){
                container_type = "Open Side";
            }
            else if (type == 3) {
                container_type = "Refridgerated";
            }
            else {
                container_type = "Liquid";
            }
            System.out.println("Container type: " + container_type + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.next();
            scanner.nextLine();
            if (response.equals("N")){
                break;
            }

        }
        while (true){
            System.out.println("Please enter container name");
            name = scanner.nextLine();
            System.out.println("Container name: " + name + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.nextLine();
            if (response.equals("N")){
                break;
            }
        }
        while (true){
            System.out.println("Please input the ID of the port you would like to assign this container to");
            Pid = scanner.nextLine();
            port = Port.queryPortbyID(Pid);
            System.out.println("Please check Port detail \n" + port + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.nextLine();
            if (response.equals("N")){
                break;
            }
        }
        while (true){
            System.out.println("Please enter container weight");
            weight = scanner.nextDouble();
            System.out.println("Container weight: " + weight + ". " + "Would you like to enter again? (Y-N)" );
            String response = scanner.next();
            scanner.nextLine();
            if (response.equals("N")){
                break;
            }
        }
        Container new_container = Container.createNewContainer(type, name, port, weight);
        new_container.getCurrent_port().accept_containers(new_container);
        return new_container;

    }
    //--------------------------------------------------READ METHODS---------------------------------------------------//
    //Ask User if they want to filter. If yes, ask by what key they would like to filter.
    //Ask User if they want to sort. If yes, ask what key they would like to sort by and what direction
    //print result
    //the method to read from list for each class has been provided
    public static boolean viewManager() throws IOException {
        //There is no need to filter or sort managers
        ArrayList<Port_Manager> to_be_printed_list = FileIOUtil.ReadManagerFromFile();
        System.out.println(to_be_printed_list);
        return true;
    }

    public static boolean viewPort() throws IOException {
        //There is no need to filter Port
        //Ports can be sorted by storing capacity
        ArrayList<Port> to_be_printed_list = FileIOUtil.ReadPortFromFile();
        System.out.println(to_be_printed_list);
        return true;
    }

    public static boolean viewVehicle() throws IOException {
        //Vehicle can be filtered by: type(Check the Vid, each type has a distinct id pattern)
        //Vehicle can be sorted by: fuel_capacity, current fuel, carrying capacity
        ArrayList<Port> to_be_printed_list = FileIOUtil.ReadPortFromFile();
        System.out.println(to_be_printed_list);
        return true;
    }

    public static boolean viewContainers() throws IOException {
        //Container can be filtered by: type(Check the Cid, each type has a distinct id pattern)
        //Container can be sorted by: weight
        ArrayList<Container> to_be_printed_list = FileIOUtil.ReadContainerFromFile();
        System.out.println(to_be_printed_list);
        return true;
    }

    public static boolean viewTrips() throws IOException {
        //Trips can be filtered by Vehicle, a_port, d_port
        //Trips can be sorted by Date of departure, Date of arrival
        ArrayList<Trip> to_be_printed_list = FileIOUtil.ReadTripFromFile();
        System.out.println(to_be_printed_list);
        return true;

    }

    //--------------------------------------------------UPDATE METHODS--------------------------------------------------//

    //Ask user the id of the object they would like to update
    // Then ask them which key they would like to update

    public static boolean updateManager() throws IOException {
        Port_Manager update_manager = new Port_Manager();
        FileIOUtil.updateObjectFromFile("port_manager.json", update_manager);
        return true;
    }

    public static boolean updatePort() throws IOException {
        Port update_port = new Port();
        FileIOUtil.updateObjectFromFile("port.json", update_port);
        return true;
    }

    public static  boolean updateVehicle() throws IOException {
        Vehicle update_vehicle = new Vehicle();
        FileIOUtil.updateObjectFromFile("vehicle.json", update_vehicle);
        return true;
    }

    public static boolean updateContainer() throws IOException {
        Container update_container = new Container();
        FileIOUtil.updateObjectFromFile("container.json", update_container );
        return true;
    }

    public static boolean updateTrip() throws IOException {
        Trip update_trip = new Trip();
        FileIOUtil.updateObjectFromFile("trip.json", update_trip);
        return true;
    }


    //--------------------------------------------------DELETE METHODS--------------------------------------------------//

    public static boolean removeManager() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Port_Manager remove_manager= new Port_Manager();
        while (true){
            System.out.println("Please enter the id of the manager you would like to remover");
            String Eid = scanner.nextLine();
            remove_manager = Port_Manager.queryManagerbyID(Eid);
            if (remove_manager != null){
                break;
            }
            else{
                System.out.println("Please enter another id");
            }
        }
        FileIOUtil.removeObjectFromFile(remove_manager, "port.json");
        return true;
    }
    public static boolean removePort() throws IOException {
        Scanner scanner = new Scanner(System.in);

        Port remove_port = new Port();
        while (true){
            System.out.println("Please enter the id of the port you would like to remover");
            String Pid = scanner.nextLine();
            remove_port = Port.queryPortbyID(Pid);
            if (remove_port != null){
                break;
            }
            else {
                System.out.println("Please enter another id.");
            }
        }
        FileIOUtil.removeObjectFromFile(remove_port, "port.json");
        return true;
    }

    public static boolean removeVehicle() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Vehicle remove_vehicle = new Vehicle();
        while (true){
            System.out.println("Please enter the id of the vehicle you would like to remover");
            String Vid = scanner.nextLine();
            remove_vehicle = Vehicle.queryVehiclebyID(Vid);
            if (remove_vehicle != null){
                break;
            }
            else {
                System.out.println("Please enter another id.");
            }
        }

        FileIOUtil.removeObjectFromFile(remove_vehicle, "vehicle.json");
        return true;
    }

    public static boolean removeContainer() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Container remove_container = new Container();
        System.out.println("Please enter the id of the container you would like to remover");
        while (true){
            String Cid = scanner.nextLine();
            remove_container = Container.queryContainerbyID(Cid);
            if (remove_container != null){
                break;
            }
            else {
                System.out.println("Please enter another id.");
            }
            FileIOUtil.removeObjectFromFile(remove_container, "container.json");

        }
        return true;

    }

    public static void removePastTrip(){

    }








}


