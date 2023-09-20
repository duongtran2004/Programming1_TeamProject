package Users;

import Container_Class.*;
import Port.Port;
import Trip.Trip;
import Vehicle_Classes.*;
import Container_Class.*;
import FileIO.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Port_Manager extends User{
    private Port port;
    public Port_Manager(){

    }



    public Port_Manager (String Eid, String username, String password, String role, Port port){
        super(Eid, username, password, role);
        this.port = port;
    }

    public static void addPortManager(String username, String password, Port port) throws IOException {
        String Eid = "";
        Random random = new Random();
        for (int i = 1; i<=10; i++){
            Eid = Eid + random.nextInt(10);
        }
        Port_Manager new_user = new Port_Manager(Eid, username, password, "Port Manager", port);
        FileIOUtil.InputObjectIntoFile(new_user, "PortManager.json");
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
        System.out.println("EID not found");
        return null;
    }
    public static void filteringManagerbyPort(ArrayList<Port_Manager> filtered_list) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Port id");
        String Pid = scanner.nextLine();
        Port filter_port = Port.queryPortbyID(Pid);
        for (int i =0; i < filtered_list.size(); i++){
            if (!filter_port.equals(filtered_list.get(i).getPort())){
                filtered_list.remove(filtered_list.get(i));
            }
        }
    }

    public static Port_Manager login() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Please enter your Eid");
            String Eid = scanner.nextLine();
            Port_Manager verification = queryManagerbyID(Eid);
            if (verification!= null) {
                System.out.println("Please enter your username");
                String username = scanner.nextLine();
                System.out.println("Please enter your password");
                String password = scanner.nextLine();
                if (!verification.getPassword().equals(password) || !verification.getUsername().equals(username)){
                    System.out.println("Incorrect Password or Username");
                }
                else{
                    return verification;
                }
            }
        }




    }

    //This method allows Port.Port Manager to view the outgoing trips after/before inputted dates
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
                scanner.nextLine();
                if (!Utlity.yes_Or_no("Filter After: " + after + ". " + "Would you like to enter again? (Y-N)" )){
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
                scanner.nextLine();
                if (!Utlity.yes_Or_no("Filter Before " + before + ". " + "Would you like to enter again? (Y-N)" )){
                    break;
                }
            }
            Utlity.viewTrips(Trip.outgoingTrip_query(this.port, after, before));


        }

    }
    //This method allows Port.Port Manager to see the incoming trip after/before inputted dates.
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
            scanner.nextLine();
            if (!Utlity.yes_Or_no("Filter After: " + after + ". " + "Would you like to enter again? (Y-N)" )){
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
            scanner.nextLine();
            if (!Utlity.yes_Or_no("Filter Before " + before + ". " + "Would you like to enter again? (Y-N)" )){
                break;
            }
        }
        Utlity.viewTrips(Trip.incomingTrip_query(this.port, after, before));
    }

    //this method is called when the port manager wants to register a new trip. It will call the registeringTrip method from the Container_Class.Open_Top.Trip.Trip class in its body
    public void RegisteringTrip() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Date date_of_departure = new Date();
        Date date_of_arrival = new Date();
        Port port_of_arrival = new Port();
        ArrayList<Container> containers = new ArrayList<Container>();
        ArrayList<String> container_id = new ArrayList<String>();
        Vehicle selected_vehicle = null;
        while (true){
            System.out.println("Please input the departure year of the trip");
            int year = scanner.nextInt();
            System.out.println("Please input the departure month of the trip");
            int month = scanner.nextInt();
            System.out.println("Please input the departure day of the trip");
            int date = scanner.nextInt();
            date_of_departure = new Date(year, month-1, date);
            scanner.nextLine();
            if (!Utlity.yes_Or_no("Date of departure: " + date_of_departure + ". " + "Would you like to enter again? (Y-N)" )){
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
            date_of_arrival = new Date(year, month-1, date);
            scanner.nextLine();
            if (!Utlity.yes_Or_no("Date of arrival: " + date_of_arrival + ". " + "Would you like to enter again? (Y-N)" )){
                break;
            }


        }

        while (true){
            System.out.println("Please enter the Id of the destination port");
            String Pid = scanner.next();
            scanner.nextLine();
            port_of_arrival = Port.queryPortbyID(Pid);
            if (!Utlity.yes_Or_no("Please check if the following Port.Port is correct.\n" + port_of_arrival +"\n" + "Would you like to enter again? (Y-N)" )){
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
                container_id.add(Cid);
                if (!Utlity.yes_Or_no("Would you like to add more containers? (Y-N)" )){
                    break;
                }
            }
            if (!Utlity.yes_Or_no("Please check if the following containers are correct.\n" + containers +"\n" + "Would you like to enter again? (Y-N)" )){
                break;
            }
        }

        while (true){
            //Check vehicle availability
            ArrayList<Vehicle> deployable_vehicles = new ArrayList<Vehicle>();
            deployable_vehicles = Trip.checking_vehicle_eligibility(this.port, port_of_arrival, date_of_arrival, date_of_departure, containers);
            //Choose Vehicle_Classes.Vehicle
            String prompt = "Please select from the following vehicles ";
            for (int i =0; i <deployable_vehicles.size(); i++){
                prompt = prompt + i + ": " + deployable_vehicles.get(i).getName() + "\n";
            }
            System.out.println(Utlity.vehicle_Table(deployable_vehicles));
            System.out.println(prompt);
            int selection = scanner.nextInt();
            selected_vehicle = deployable_vehicles.get(selection);
            if (!Utlity.yes_Or_no("Please check if the following vehicle is correct.\n" + selected_vehicle +"\n" + "Would you like to enter again? (Y-N)" )){
                break;
            }
        }
        Trip.registeringTrip(this.port, date_of_departure, date_of_arrival, port_of_arrival, container_id, selected_vehicle);
    }
    //This method is called at the commencement of each outgoing trip. It will check the vehicle out of the port.
    public void commenceTrip() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the id of the trip");
        String Tid = scanner.nextLine();
        Trip trip = Trip.queryByName(Tid);
        Port main_port = Port.queryPortbyID(this.port.getPid());
        trip.getVehicle().depart(trip);

    }
    //this method is called after a vehicle reaches its destination port and finishes its trip. It will check the vehicle into the port
    public void completeTrip() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the id of the trip");
        String Tid = scanner.nextLine();
        Trip trip = Trip.queryByName(Tid);
        trip.completeTrip();
        Port main_port = Port.queryPortbyID(this.port.getPid());
        trip.getVehicle().arrive(main_port);
    }
    //this method is called when the port manager wants to load containers off a vehicle
    public void loadingContainersonVehicle() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the id of the trip");
        String tid = scanner.nextLine();
        Trip trip = Trip.queryByName(tid);
        ArrayList<String> containers_to_be_loaded_on = trip.getTo_be_delivered_containers();
        for (String container_id: containers_to_be_loaded_on){
            if (container_id.startsWith("DS")){
                Dry_Storage container = Dry_Storage.queryDryStoragebyID(container_id);
                FileIOUtil.updateContainerFromFile(container);
            }
            else if (container_id.startsWith("OT")){
                Open_Top container = Open_Top.queryOpenTopbyID(container_id);
                container.loadedonVehicle(trip.getVehicle());
            }
            else if (container_id.startsWith("OS")){
                Open_Side container = Open_Side.queryOpenSidebyID(container_id);
                container.loadedonVehicle(trip.getVehicle());
            }
            else if (container_id.startsWith("RE")){
                Refriderated container = Refriderated.queryRefridgeratedbyID(container_id);
                container.loadedonVehicle(trip.getVehicle());
            }
            else {
                Liquid container = Liquid.queryLiquidbyID(container_id);
                container.loadedonVehicle(trip.getVehicle());
            }


        }
    }
    public void loadingContaineroffVehicle() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the id of the trip");
        String tid = scanner.nextLine();
        Trip trip = Trip.queryByName(tid);
        ArrayList<String> containers_to_be_loaded_on = trip.getTo_be_delivered_containers();
        for (String container_id: containers_to_be_loaded_on){
            if (container_id.startsWith("DS")){
                Dry_Storage container = Dry_Storage.queryDryStoragebyID(container_id);
                container.loadedoffVehicle();
            }
            else if (container_id.startsWith("OT")){
                Open_Top container = Open_Top.queryOpenTopbyID(container_id);
                container.loadedoffVehicle();
            }
            else if (container_id.startsWith("OS")){
                Open_Side container = Open_Side.queryOpenSidebyID(container_id);
                container.loadedoffVehicle();
            }
            else if (container_id.startsWith("RE")){
                Refriderated container = Refriderated.queryRefridgeratedbyID(container_id);
                container.loadedoffVehicle();
            }
            else {
                Liquid container = Liquid.queryLiquidbyID(container_id);
                container.loadedoffVehicle();
            }
        }
    }

    public void viewVehicleonSite() throws IOException {
        ArrayList<Vehicle> vehicles = Vehicle.getVehicles();
        for (int i =0; i< vehicles.size(); i++){
            if (vehicles.get(i).getCurrent_port().equals(this.port)){
                vehicles.remove(vehicles.get(i));
            }
        }
        Utlity.viewVehicle(vehicles);

    }

    public void viewContainersonSite() throws IOException {
        ArrayList<Container> containers = Container.getContainer();
        for (int i = 0; i < containers.size(); i++){
            if (containers.get(i).getCurrent_port().equals(this.port)){
                containers.remove(containers.get(i));
            }
        }
        Utlity.viewContainers(containers);
    }
    public boolean removeTrip() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Trip remove_trip = new Trip();
        System.out.println("Please enter the id of the container you would like to remover");
        while (true){
            String Tid = scanner.nextLine();
            remove_trip = Trip.queryByName(Tid);
            if (remove_trip != null){
                break;
            }
            else {
                System.out.println("Please enter another id.");
            }
            FileIOUtil.removeTripFromFile(remove_trip);

        }
        return true;
    }

    public boolean updateTrip() throws IOException {
        Trip update_trip = new Trip();
        FileIOUtil.updateTripFromFile(update_trip);
        return true;
    }
}
