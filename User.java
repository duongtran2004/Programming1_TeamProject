import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role){
        this.username = username;
        this.password = password;
        this.role = role;
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
    Port port;
    static ArrayList<Port_Manager> manager_list = new ArrayList<Port_Manager>();
    public Port_Manager (String username, String password, String role, Port port){
        super(username, password, role);
        this.port = port;
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
        Port_Manager.manager_list.add(new Port_Manager(username, password, "Port Manager", Port.getPorts().get(selection)));

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
        trip.getVehicle().depart();
    }
    //this method is called after a vehicle reaches its destination port and finishes its trip. It will check the vehicle into the port
    public void completeTrip(){
        Trip trip = Trip.queryByName();
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
            this.port.load_containers_on_vehicle(container, vehicle);
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
            this.port.load_containers_off_vehicle(container, vehicle);
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

    }

    public static void removePort(){

    }

    public static void removeVehicle(){

    }

    public static void viewManager(){

    }

    public static void viewSpecificManager(){

    }

    public static void viewVehicle(){

    }

    public static void viewContainers(){

    }

    public static void viewTrips(){

    }

    public static void removePastTrip(){

    }
}


