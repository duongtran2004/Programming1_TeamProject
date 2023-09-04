import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

@JsonIdentityInfo(generator =ObjectIdGenerators.UUIDGenerator.class, property = "@pid", scope = Port.class)
public class Port implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String pid;
    private double latitude;
    private double longtitude;
    private String name;
    private double storingCapacity;
    private boolean landingAbility;
    //@JsonManagedReference(value = "onsite_vehicles")
    private ArrayList<Vehicle> onsite_vehicles = new ArrayList<Vehicle>();
    //@JsonManagedReference(value = "onsite_containers")
    private ArrayList<Container> onsite_containers = new ArrayList<Container>();

    public boolean equals(Port port) {
        if (port == this) {
            System.out.println("true");
            return true;
        }

        return this.pid.equals(port.pid) && this.name.equals(port.name) && this.latitude == port.latitude  && this.longtitude == port.longtitude;


    }

    public  Port(){

    }

    public Port(String Pid, double latitude, double longtitude, String name, double storing_capacity, boolean landing_ability){
        this.pid = Pid;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.name = name;
        this.storingCapacity = storing_capacity;
        this.landingAbility = landing_ability;
    }
    public String toString(){
        return "Port id: " + this.pid + "\n" + "Port name: "+ this.name + "\n" + "Port latitude: " + this.latitude + "\n" + "Port longtitude: " + this.longtitude + "\n" + "Storing capacity: "+ this.storingCapacity + "\n" + "Landing ability: " + this.landingAbility + "\n" + "\n";}

    public static Port createNewPort(String name, double latitude, double longtitude, double storing_capacity, boolean landing_ability) throws IOException {
        String Pid = "";
        Random random = new Random();
        for (int i = 1; i<=10; i++){
            Pid = Pid + random.nextInt(10);
        }
        Port new_port = new Port(Pid, latitude, longtitude, name, storing_capacity, landing_ability);
        FileIOUtil.InputObjectIntoFile(new_port, "port.json");
        return new_port;

    }

    public static Port queryPortbyID(String Pid) throws IOException {
        for (Port port: FileIOUtil.ReadPortFromFile()){
            if (port.pid.equals(Pid)){
                return port;
            }
        }
        System.out.println("This port does not exist");
        return null;
    }
    //--------------------------------------------Getters-----------------------------------------//


    public String getPid() {
        return pid;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public String getName() {
        return name;
    }

    public double getStoringCapacity() {
        return storingCapacity;
    }

    public boolean isLandingAbility() {
        return landingAbility;
    }

    public ArrayList<Vehicle> getOnsite_vehicles() {
        return onsite_vehicles;
    }

    public ArrayList<Container> getOnsite_containers() {
        return onsite_containers;
    }

    //-----------------------------------------------------Setters---------------------------------------------//


    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStoringCapacity(double storingCapacity) {
        this.storingCapacity = storingCapacity;
    }

    public void setLandingAbility(boolean landingAbility) {
        this.landingAbility = landingAbility;
    }

    public void setOnsite_vehicles(ArrayList<Vehicle> onsite_vehicles) {
        this.onsite_vehicles = onsite_vehicles;
    }

    public void setOnsite_containers(ArrayList<Container> onsite_containers) {
        this.onsite_containers = onsite_containers;
    }

    public void accept_vehicles(Vehicle vehicle) throws IOException {
        this.onsite_vehicles.add(vehicle);
        FileIOUtil.updateObjectFromFile("port.json", this);

    }
    public void checkout_vehicles(Vehicle vehicle) throws IOException {
        for (int i =0; i < this.onsite_vehicles.size(); i++){
            if (this.onsite_vehicles.get(i).equals(vehicle)){
                this.onsite_vehicles.remove(onsite_vehicles.get(i));
            }
        }
        FileIOUtil.updateObjectFromFile("port.json", this);
    }

    public void accept_containers(Container container) throws IOException {
        this.onsite_containers.add(container);
        FileIOUtil.updateObjectFromFile("port.json", this);
    }
    public void checkout_containers(Container container) throws IOException {
        this.onsite_containers.remove(container);
        FileIOUtil.updateObjectFromFile("port.json", this);
    }

    public double distanceCalc(Port targetPort){
        double distance = (3440.1 * Math.acos((Math.sin(this.latitude * (Math.PI/180)) * Math.sin(targetPort.latitude * (Math.PI/180))) + Math.cos(this.latitude * (Math.PI/180)) * Math.cos(targetPort.latitude * (Math.PI/180)) * Math.cos(this.longtitude * (Math.PI/180) - targetPort.longtitude * (Math.PI/180)))) * 1.852 ;
        distance = (double) Math.round(distance * 100)/100;
        return distance;
    }



}
