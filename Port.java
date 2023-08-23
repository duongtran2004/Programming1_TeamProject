import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;
public class Port {
    private String Pid;
    private String latitude;
    private String longtitude;
    private String name;
    private double storing_capacity;
    private boolean landing_ability;
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    private ArrayList<Container> containers = new ArrayList<Container>();

    private static ArrayList<Port> ports = new ArrayList<Port>();

    public static ArrayList<Port> getPorts(){
        return ports;
    }

    public Port(String Pid, String latitude, String longtitude, String name, double storing_capacity, boolean landing_ability){
        this.Pid = Pid;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.name = name;
        this.storing_capacity = storing_capacity;
        this.landing_ability = landing_ability;
    }

    public String getPortName(){
        return this.name;
    }

    public boolean getLandingAbility(){
        return this.landing_ability;
    }

    public ArrayList<Vehicle> getVehiclesList(){
        return  this.vehicles;
    }

    public ArrayList<Container> getContainerList(){
        return this.containers;
    }

    public static void createNewPort(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the name of the new port: ");
        String name = scanner.nextLine();
        System.out.println("Please input the port latitude");
        String latitude = scanner.nextLine();
        System.out.println("Please input the port longtitude");
        String longtitude = scanner.nextLine();
        System.out.println("PLease input its storing capacity");
        double storing_capacity = scanner.nextDouble();
        System.out.println("Please input its landing ability");
        boolean landing_ability = scanner.nextBoolean();
        String Pid = "";
        Random random = new Random();
        for (int i = 1; i<=10; i++){
            Pid = Pid + random.nextInt(10);
        }
        ports.add(new Port(Pid, latitude, longtitude, name, storing_capacity, landing_ability));
    }

    public static Port queryPortbyID(String Pid){
        for (Port port: ports){
            if (port.Pid.equals(Pid)){
                return port;
            }
        }
        System.out.println("This port does not exist");
        return null;
    }


    public void receive_vehicles(Vehicle vehicle){
        vehicles.add(vehicle);
    }
    public void sendoff_vehicles(Vehicle vehicle){
        vehicles.remove(vehicle);
    }

    public void load_containers_off_vehicle(Container container, Vehicle vehicle){
        containers.add(container);
        vehicle.unload_container(container);
    }

    public void load_containers_on_vehicle(Container container, Vehicle vehicle){
        containers.remove(container);
        vehicle.accept_container(container);
    }

    public double distanceCalc(Port targetPort){
        double distance = 0.0;
        return distance;
    }





}
