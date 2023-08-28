import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Port implements Serializable {
    private String Pid;
    private String latitude;
    private String longtitude;
    private String name;
    private double storing_capacity;
    private boolean landing_ability;
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    private ArrayList<Container> containers = new ArrayList<Container>();

    public static ArrayList<Port> getPorts(){
        ArrayList<Port> port_list = new ArrayList<Port>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("port.txt"));
            while (true){
                try{
                    Port port = (Port) ois.readObject();
                    port_list.add(port);
                } catch (EOFException e){
                    break;
                }
                catch (ClassNotFoundException e){
                    e.printStackTrace();
                }

            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return port_list;
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
        return this.vehicles;
    }

    public ArrayList<Container> getContainerList(){
        return this.containers;
    }

    public String toString(){
        return "Port id: " + this.Pid + "\n" + "Port name: "+ this.name + "\n" + "Port latitude: " + this.latitude + "\n" + "Port longtitude: " + this.longtitude + "\n" + "Storing capacity: "+ this.storing_capacity + "\n" + "Landing ability: " + this.landing_ability;}

    public static ArrayList<Port> getPort(){
        ArrayList<Port> port_list = new ArrayList<Port>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("port.txt"));
            while (true){
                try {
                    Port port = (Port) ois.readObject();
                    port_list.add(port);
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
        return port_list;
    }

    public static void inputPortIntoFile(File file, Port port){
        if (file.length() == 0){
            try{
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("port.txt"));
                oos.writeObject(port);
                oos.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            try{
                AppendObjectOutputStream oos = new AppendObjectOutputStream(new FileOutputStream("port.txt", true));
                oos.writeObject(port);
                oos.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
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
        File file = new File("port.txt");
        Port new_port = new Port(Pid, latitude, longtitude, name, storing_capacity, landing_ability);
        inputPortIntoFile(file, new_port);

    }

    public static Port queryPortbyID(String Pid){
        for (Port port: getPorts()){
            if (port.Pid.equals(Pid)){
                return port;
            }
        }
        System.out.println("This port does not exist");
        return null;
    }


    public void receive_vehicles(Vehicle vehicle){
        vehicles.add(vehicle);
        ArrayList<Port> port_list = getPort();
        File file = new File("port.txt");
        for (int i =0; i<port_list.size(); i++){
            if (port_list.get(i) == this){
                port_list.set(i, this);
            }
        }
        file.delete();
        try{
            file.createNewFile();
            for (Port port: port_list){
                inputPortIntoFile(file, port);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void sendoff_vehicles(Vehicle vehicle){
        vehicles.remove(vehicle);
        ArrayList<Port> port_list = getPort();
        File file = new File("port.txt");
        for (int i =0; i<port_list.size(); i++){
            if (port_list.get(i) == this){
                port_list.set(i, this);
            }
        }
        file.delete();
        try{
            file.createNewFile();
            for (Port port: port_list){
                inputPortIntoFile(file, port);
            }
        } catch (IOException e){
            e.printStackTrace();
        }





    }

    public void load_containers_off_vehicle(Container container){
        containers.add(container);
        ArrayList<Port> port_list = getPort();
        File file = new File("port.txt");
        for (int i =0; i<port_list.size(); i++){
            if (port_list.get(i) == this){
                port_list.set(i, this);
            }
        }
        file.delete();
        try{
            file.createNewFile();
            for (Port port: port_list){
                inputPortIntoFile(file, port);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void load_containers_on_vehicle(Container container){
        containers.remove(container);
        ArrayList<Port> port_list = getPort();
        File file = new File("port.txt");
        for (int i =0; i<port_list.size(); i++){
            if (port_list.get(i) == this){
                port_list.set(i, this);
            }
        }
        file.delete();
        try{
            file.createNewFile();
            for (Port port: port_list){
                inputPortIntoFile(file, port);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public double distanceCalc(Port targetPort){
        double distance = 0.0;
        return distance;
    }





}
