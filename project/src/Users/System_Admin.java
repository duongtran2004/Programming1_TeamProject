package Users;

import Container_Class.Container;
import Port.Port;
import Trip.Trip;
import Vehicle_Classes.*;
import Container_Class.*;
import FileIO.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class System_Admin extends User{
    public System_Admin(){

    }
    public System_Admin(String Eid, String username, String password, String role){
        super(Eid,username, password, role);
    }
    public static System_Admin queryAdminbyID(String Eid) throws IOException {
        for (System_Admin admin: FileIOUtil.ReadAdminFromFile()){
            if (admin.getEid().equals(Eid)){
                return admin;
            }
        }
        System.out.println("EID not found");
        return null;
    }

    public static System_Admin login() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Please enter your Eid");
            String Eid = scanner.nextLine();
            System_Admin verification = queryAdminbyID(Eid);
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

    //-----------------------------------------------CREATE METHODS----------------------------------------------------//
    public static void addSystemAdmin() throws IOException {
        String username;
        String password;
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Please input your username here: ");
            username = scanner.nextLine();
            if (!Utlity.yes_Or_no("Username: " + username + "." + "Would you like to input again? (Y or N)" )){
                break;
            }
        }

        while (true){
            System.out.println("Please input your password here: ");
            password = scanner.nextLine();
            if (!Utlity.yes_Or_no("Password: " + password + "." + "Would you like to input again? (Y or N)" )){
                break;
            }
        }

        String Eid = "";
        Random random = new Random();
        for (int i = 1; i<=10; i++){
            Eid = Eid + random.nextInt(10);
        }

        System_Admin new_user = new System_Admin(Eid, username, password, "Admin");
        FileIOUtil.InputObjectIntoFile(new_user, "SystemAdmin.json");

    }

    public static void addManager() throws IOException {
        String username;
        String password;
        Port port = new Port();
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Please input your username here: ");
            username = scanner.nextLine();
            if (!Utlity.yes_Or_no("Username: " + username + "." + "Would you like to input again? (Y or N)" )){
                break;
            }
        }

        while (true){
            System.out.println("Please input your password here: ");
            password = scanner.nextLine();
            if (!Utlity.yes_Or_no("Password: " + password + "." + "Would you like to input again? (Y or N)" )){
                break;
            }
        }

        while (true){
            String prompt = "Please assign the new manager to a port: ";
            for (int i = 0; i < FileIOUtil.ReadPortFromFile().size(); i++){
                prompt = prompt + (i+1) +": "+ (FileIOUtil.ReadPortFromFile().get(i)).getName() + " ";
            }
            System.out.println(prompt);
            int selection = scanner.nextInt();
            scanner.nextLine();
            port = FileIOUtil.ReadPortFromFile().get(selection-1);
            if (!Utlity.yes_Or_no("Please check port detail:\n" + password + "\n" + "Would you like to choose another port? (Y or N)" )){
                break;
            }

        }
        Port_Manager.addPortManager(username, password, port);
    }
    public static Port addPort() throws IOException {
        String name = "";
        double latitude = 0;
        double longitude = 0;
        double storing_capacity = 0;
        boolean landing_ability = false;
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Please input the name of the new port: ");
            name = scanner.nextLine();
            if (!Utlity.yes_Or_no("Port name: " + name + ". " + "Would you like to enter again? (Y or N)" )){
                break;
            }
        }

        while (true){
            String hemisphere = "";
            double degree = 0;
            double minute = 0;
            double second = 0;
            while (true){
                System.out.println("Please input the port latitude");
                while (!hemisphere.equals("N") && !hemisphere.equals("S")) {
                    System.out.println("Please Choose Hemisphere (N or S)");
                    hemisphere = scanner.nextLine().toUpperCase();
                }
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
            if (!Utlity.yes_Or_no("Port latitude: " + degree + " degree " + minute + " minutes " + second + " seconds " + hemisphere + ". " +"Would you like to enter again? (Y-N)" )){
                break;
            }
        }

        while (true){
            String hemisphere = "";
            double degree = 0;
            double minute = 0;
            double second = 0;
            while (true){
                System.out.println("Please input the port longitude");
                while (!hemisphere.equals("W") && !hemisphere.equals("E")) {
                    System.out.println("Please Choose Hemisphere (W or E)");
                    hemisphere = scanner.nextLine().toUpperCase();
                }
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
                        longitude = total_degree;
                    }
                    else {
                        longitude = -total_degree;
                    }
                    break;
                }
                else {
                    System.out.println("Incorrect latitude. Please enter again");
                }
            }
            if (!Utlity.yes_Or_no("Port longitude: " + degree + " degree " + minute + " minutes " + second + " seconds " + hemisphere + ". " +"Would you like to enter again? (Y or N)" )){
                break;
            }
        }

        while (true){
            System.out.println("PLease input its storing capacity");
            storing_capacity = scanner.nextDouble();
            storing_capacity = (double) Math.round(storing_capacity * 100)/100;
            scanner.nextLine();
            if (!Utlity.yes_Or_no("Port storing capacity: " + storing_capacity + ". " + "Would you like to enter again? (Y or N)")){
                break;
            }
        }

        while (true){
            System.out.println("Please input its landing ability");
            landing_ability = scanner.nextBoolean();
            scanner.nextLine();
            if (!Utlity.yes_Or_no("Port landing ability: " + landing_ability + ". " + "Would you like to enter again? (Y or N)")){
                break;
            }
        }

        return Port.createNewPort(name, latitude, longitude, storing_capacity, landing_ability);
    }
    public static boolean addVehicle() throws IOException {
        String name ="";
        int type = 0;
        double fuel_capacity = 0;
        double carrying_capacity = 0;
        Port port = new Port();
        Scanner scanner = new Scanner(System.in);
        String vehicle_type = "";
        while (true){
            System.out.println("Please input the name of the vehicle");
            name = scanner.nextLine();
            if (!Utlity.yes_Or_no("Vehicle name: " + name + ". " + "Would you like to enter again? (Y or N)")){
                break;
            }

        }
        while (true){
            System.out.println("Choose 0 for ship, 1 for basic truck, 2 for reefer truck, 3 for tanker truck");
            type = scanner.nextInt();
            if (type == 0){
                vehicle_type = "ship";
            }
            else if (type == 1){
                vehicle_type = "basic truck";
            }
            else if (type == 2){
                vehicle_type= "reefer truck";
            }
            else {
                vehicle_type = "tanker truck";
            }
            scanner.nextLine();
            if (!Utlity.yes_Or_no("Vehicle type: " + vehicle_type + ". " + "Would you like to enter again? (Y-N)")){
                break;
            }
        }

        while (true){
            System.out.println("Please input the fuel capacity of the vehicle");
            fuel_capacity = scanner.nextDouble();
            fuel_capacity = (double) Math.round(fuel_capacity * 100)/100;
            scanner.nextLine();
            if (!Utlity.yes_Or_no("Fuel capacity: " + fuel_capacity + ". " + "Would you like to enter again? (Y or N)")){
                break;
            }

        }

        while (true){
            System.out.println("Please input the carrying capacity of the vehicle");
            carrying_capacity = scanner.nextDouble();
            carrying_capacity = (double) Math.round(carrying_capacity * 100)/100;
            scanner.nextLine();
            if (!Utlity.yes_Or_no("Carrying capacity: " + carrying_capacity + ". " + "Would you like to enter again? (Y-N)")){
                break;
            }

        }
        while (true){
            while (true){
                System.out.println("Please input the ID of the port you would like to assign this vehicle to");
                String Pid = scanner.next();
                scanner.nextLine();
                port = Port.queryPortbyID(Pid);
                if (!port.isLandingAbility() && type!=0){
                    System.out.println("Trucks cannot be assigned to this port. Please select another port");
                }
                else {
                    break;
                }
            }
            if (!Utlity.yes_Or_no("Please check if the following port is correct\n" +port + "\n" + "Would you like to enter again? (Y-N)")){
                break;
            }

        }
        Vehicle.createNewVehicle(name, type, fuel_capacity, carrying_capacity, port);
        return true;

    }
    public static boolean addContainer() throws IOException {
        int type = 0;
        String name = "";
        Port port = new Port();
        double weight = 0;
        Scanner scanner = new Scanner(System.in);
        String Pid = "";

        while (true){
            String container_type = "";
            while (true){
                System.out.println("Choose 0 for Dry Storage, 1 for Open Top, 2 for Open Side, 3 for Refrigerated, 4 for Container_Class.Liquid");
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
                container_type = "Refrigerated";
            }
            else {
                container_type = "Container_Class.Liquid";
            }
            scanner.nextLine();
            if (!Utlity.yes_Or_no("Container type: " + container_type + ". " + "Would you like to enter again? (Y-N)")){
                break;
            }
        }

        while (true){
            System.out.println("Please enter container name");
            name = scanner.nextLine();
            if (!Utlity.yes_Or_no("Container name: " + name + ". " + "Would you like to enter again? (Y-N)")){
                break;
            }

        }
        while (true){
            System.out.println("Please input the ID of the port you would like to assign this container to");
            Pid = scanner.nextLine();
            port = Port.queryPortbyID(Pid);
            if (!Utlity.yes_Or_no("Please check Port.Port detail \n" + port + ". " + "Would you like to enter again? (Y-N)")){
                break;
            }
        }

        while (true){
            System.out.println("Please enter container weight");
            weight = scanner.nextDouble();
            weight = (double) Math.round(weight * 100)/100;
            scanner.nextLine();
            if (!Utlity.yes_Or_no("Container weight: " + weight + ". " + "Would you like to enter again? (Y-N)")){
                break;
            }
        }

        Container.createNewContainer(type, name, port, weight);
        //new_container.getCurrent_port().accept_containers(new_container);
        return true;

    }
    //--------------------------------------------------READ METHODS---------------------------------------------------//
    //Ask Users.User if they want to filter. If yes, ask by what key they would like to filter.
    //Ask Users.User if they want to sort. If yes, ask what key they would like to sort by and what direction
    //print result
    //the method to read from list for each class has been provided
    public static boolean viewManager() throws IOException {
        //There is no need to filter or sort managers
        ArrayList<Port_Manager> to_be_printed_list = FileIOUtil.ReadManagerFromFile();
        Utlity.viewManager(to_be_printed_list);
        return true;
    }

    public static boolean viewAdmin() throws IOException {
        ArrayList<System_Admin> admins = FileIOUtil.ReadAdminFromFile();
        Utlity.viewAdmin(admins);
        return true;
    }

    public static boolean viewPort() throws IOException {
        ArrayList<Port> port_list = FileIOUtil.ReadPortFromFile();
        Utlity.viewPort(port_list);
        return true;
    }

    public static boolean viewVehicle() throws IOException {
        //Vehicle_Classes.Vehicle can be filtered by: type(Check the Vid, each type has a distinct id pattern)
        //Vehicle_Classes.Vehicle can be sorted by: fuel_capacity, current fuel, carrying capacity
        ArrayList<Vehicle> vehicles = Vehicle.getVehicles();
        Utlity.viewVehicle(vehicles);
        return true;
    }

    public static boolean viewContainers() throws IOException {
        //Container_Class.Container can be filtered by: type(Check the Cid, each type has a distinct id pattern)
        //Container_Class.Container can be sorted by: weight
        ArrayList<Container> container_list = Container.getContainer();
        Utlity.viewContainers(container_list);
        return true;
    }

    public static boolean viewAllTrips() throws IOException {
        Utlity.viewTrips(FileIOUtil.ReadTripFromFile());
        return true;
    }



    //--------------------------------------------------UPDATE METHODS--------------------------------------------------//

    //Ask user the id of the object they would like to update
    // Then ask them which key they would like to update

    public static boolean updateManager() throws IOException {
        Port_Manager update_manager = new Port_Manager();
        FileIOUtil.updateManagerFromFile(update_manager);
        return true;
    }

    public static boolean updateAdmin() throws IOException {
        System_Admin update_admin = new System_Admin();
        FileIOUtil.updateAdminFromFile(update_admin);
        return true;
    }

    public static boolean updatePort() throws IOException {
        Port update_port = new Port();
        FileIOUtil.updatePortFromFile(update_port);
        return true;
    }

    public static  boolean updateVehicle() throws IOException {
        Vehicle update_vehicle = null;
        FileIOUtil.updateVehicleFromFile(update_vehicle);
        return true;
    }

    public static boolean updateContainer() throws IOException {
        Container update_container = new Container();
        FileIOUtil.updateVehicleFromFile(update_container);
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
        FileIOUtil.removeManagerFromFile(remove_manager);
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
        FileIOUtil.removePortFromFile(remove_port);
        return true;
    }

    public static boolean removeVehicle() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Vehicle remove_vehicle = null;
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

        FileIOUtil.removeVehicleFromFile(remove_vehicle);
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
            FileIOUtil.removeContainerFromFile(remove_container);

        }
        return true;

    }

    public static boolean removeSystem_Admin() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System_Admin remove_admin = new System_Admin();
        System.out.println("Please enter the id of the container you would like to remover");
        while (true){
            String Eid = scanner.nextLine();
            remove_admin = System_Admin.queryAdminbyID(Eid);
            if (remove_admin != null){
                break;
            }
            else {
                System.out.println("Please enter another id.");
            }
            FileIOUtil.removeAdminFromFile(remove_admin);

        }
        return true;

    }


}
