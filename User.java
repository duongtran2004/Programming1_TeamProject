import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Serializable{
    private String Eid;
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getEid(){
        return this.Eid;
    }

    public static void viewSpecificVehicle(Vehicle vehicle){
        System.out.println(vehicle);
    }

    public static void viewSpecificPort(Port port){
        System.out.println(port);
    }

    public static void viewSpecificContainer(Container container){
        System.out.println(container);
    }
}

class Port_Manager extends User{
    private Port port;
    static ArrayList<Port_Manager> manager_list = new ArrayList<Port_Manager>();
    public Port_Manager (String username, String password, String role, Port port){
        super(username, password, role);
        this.port = port;
    }
    public static ArrayList<Port_Manager> getManagers(){
        ArrayList<Port_Manager> manager_list = new ArrayList<Port_Manager>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("port_manager.txt"));
            while (true){
                try {
                    Port_Manager manager = (Port_Manager) ois.readObject();
                    manager_list.add(manager);
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
        return manager_list;
    }

    public static void inputPortManagerIntoFile(File file, Port_Manager port_manager){

        if (file.length() == 0 ){
            try{
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("port_manager.txt"));
                oos.writeObject(port_manager);
                oos.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            try{
                AppendObjectOutputStream oos = new AppendObjectOutputStream(new FileOutputStream("port_manager.txt", true));
                oos.writeObject(port_manager);
                oos.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void addPortManager(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the username");
        String username = scanner.nextLine();
        System.out.println("Please enter the password");
        String password = scanner.nextLine();
        String prompt = "Please assign the new manager to a port: ";

        for (int i = 0; i < Port.getPorts().size(); i++){
            prompt = prompt + i +":"+ Port.getPorts().get(i).getPortName();
        }
        System.out.println(prompt);
        int selection = scanner.nextInt();
        Port_Manager new_manager = new Port_Manager(username, password, "Port Manager", Port.getPorts().get(selection));
        File file = new File("port_manager");
        inputPortManagerIntoFile(file, new_manager);



    }


    public static Port_Manager queryManagerbyID(String Eid){
        for (Port_Manager manager: getManagers()){
            if (manager.getEid().equals(Eid)){
                return manager;
            }
        }
        System.out.println("Vehicle not found");
        return null;
    }

    //This method allows Port Manager to view the outgoing trips after/before inputted dates
    public void outgoingTrip_query(){
        Trip.outgoingTrip_query(this.port);
    }
    //This method allows Port Manager to see the incoming trip after/before inputted dates.
    public void incomingTrip_query(){
       Trip.incomingTrip_query(this.port);
    }
    //this method is called when the port manager wants to register a new trip. It will call the registeringTrip method from the Trip class in its body
    public void RegisteringTrip(){
        Trip.registeringTrip(this.port);
    }
    //This method is called at the commencement of each outgoing trip. It will check the vehicle out of the port.
    public void commenceTrip(){
        Trip trip = Trip.queryByName();
        this.port.sendoff_vehicles(trip.getVehicle());
        trip.getVehicle().depart(trip);
    }
    //this method is called after a vehicle reaches its destination port and finishes its trip. It will check the vehicle into the port
    public void completeTrip(){
        Trip trip = Trip.queryByName();
        trip.setStatus("Complete");
        this.port.receive_vehicles(trip.getVehicle());
        trip.getVehicle().arrive(this.port);
    }
    //this method is called when the port manager wants to load containers off a vehicle
    public void loadingContainersonVehicle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the vehicle to load containers on");
        String Vid = scanner.nextLine();
        Vehicle vehicle = Vehicle.queryVehiclebyID(Vid);
        while (true){
            System.out.println("Please select the container you would like to load");
            String Cid = scanner.nextLine();
            Container container = Container.queryContainerbyID(Cid);
            this.port.load_containers_on_vehicle(container);
            vehicle.accept_container(container);
            System.out.println("Would you like to continue? (Y-N)");
            String rep = scanner.nextLine();
            if (rep.equals("N")){
                break;
            }
        }
    }
    public void loadingContaineroffVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select the vehicle to load containers on");
        String Vid = scanner.nextLine();
        Vehicle vehicle = Vehicle.queryVehiclebyID(Vid);
        while (true) {
            System.out.println("Please select the container you would like to load");
            String Cid = scanner.nextLine();
            Container container = Container.queryContainerbyID(Cid);
            this.port.load_containers_off_vehicle(container);
            vehicle.unload_container(container);
            System.out.println("Would you like to continue? (Y-N)");
            String rep = scanner.nextLine();
            if (rep.equals("N")) {
                break;
            }
        }
    }

    }
















class System_Admin extends  User{
    public System_Admin(String username, String password, String role){
        super(username, password, role);
    }

    public static void addManager(){
        Port_Manager.addPortManager();
    }

    public static void addPort(){
        Port.createNewPort();
    }
    public static void addVehicle(){
        Vehicle.createNewVehicle();
    }

    public static void removeManager(){
        ArrayList<Port_Manager>manager_list = Port_Manager.getManagers();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the id of the manager you would like to remove");
        String Eid = scanner.nextLine();
        manager_list.remove(Port.queryPortbyID(Eid));
        File file = new File("port_manager.txt");
        file.delete();
        try{
            file.createNewFile();
            for (Port_Manager manager: manager_list){
                Port_Manager.inputPortManagerIntoFile(file, manager);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void removePort(){
        ArrayList<Port>port_list = Port.getPort();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the id of the port you would like to remove");
        String Pid = scanner.nextLine();
        port_list.remove(Port.queryPortbyID(Pid));
        File file = new File("port.txt");
        file.delete();
        try{
            file.createNewFile();
            for (Port port: port_list){
                Port.inputPortIntoFile(file, port);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void removeVehicle(){
        ArrayList<Vehicle>vehicle_list = Vehicle.getVehicles();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the id of the vehicle you would like to remove");
        String Vid = scanner.nextLine();
        vehicle_list.remove(Vehicle.queryVehiclebyID(Vid));
        File file = new File("vehicle.txt");
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

    public static void viewManager(){
        System.out.println(Port_Manager.getManagers());
    }

    public static void viewSpecificManager(){

    }

    public static void viewVehicle(){
        System.out.println(Vehicle.getVehicles());
    }

    public static void viewContainers(){
        System.out.println(Container.getContainer());
    }

    public static void viewTrips(){
        System.out.println(Trip.getTrip_list());
    }

    public static void removePastTrip(){

    }
}


