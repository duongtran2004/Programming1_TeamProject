import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
@JsonIdentityInfo(generator =ObjectIdGenerators.UUIDGenerator.class, property = "@cid", scope = Container.class)
public class Container implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String cid;
    private String name;
    private double weight;
    //@JsonBackReference (value = "onsite_containers")
    private  Port current_port;
    //@JsonBackReference (value = "vehicles")
    private Vehicle current_vehicle = null;
    private double fuel_consumption_per_km_on_ship;
    private double fuel_consumption_per_km_on_truck;

    public Container(){

    }

    public Container(String Cid, String name, double weight, Port port){
        this.cid = Cid;
        this.name = name;
        this.weight = weight;
        this.current_port = port;
    }


    public boolean equals(Container container) {
        if (container == this) {
            System.out.println("true");
            return true;
        }

        return this.cid.equals(container.cid) && this.name.equals(container.name);


    }

    public String toString(){
        return "Container id: " + this.cid + "\n" + "Container name: " + this.name + "\n" + "Weight: " + this.weight + "\n" + "Per Km Fuel Consumption on Ship " + this.fuel_consumption_per_km_on_ship + "\n" + "Per Km Fuel Consumption on Truck: " + this.fuel_consumption_per_km_on_truck + "\n" + "\n";}


    public static Container createNewContainer(int type, String name, Port port, double weight) throws IOException {
        String Cid = "";
        Random random_id = new Random();
        for (int i =0; i<=10; i++){
            Cid = Cid + random_id.nextInt(10);
        }
        File file = new File("container.json");
        Container new_container = new Container();
        System.out.println(type);

        if (type == 0){
            new_container = new Container("DS" + Cid, name, weight, port);
            new_container.fuel_consumption_per_km_on_ship = 3.5;
            new_container.fuel_consumption_per_km_on_truck = 4.6;
            FileIOUtil.InputObjectIntoFile(new_container, "container.json");
        }
        if (type == 1){
            new_container = new Container("OT" + Cid, name, weight, port);
            new_container.fuel_consumption_per_km_on_ship = 2.8;
            new_container.fuel_consumption_per_km_on_truck = 3.2;
            FileIOUtil.InputObjectIntoFile(new_container, "container.json");
        }
        if (type == 2){
            new_container = new Container("OS" + Cid, name, weight, port );
            new_container.fuel_consumption_per_km_on_ship = 2.7;
            new_container.fuel_consumption_per_km_on_truck = 3.2;
            FileIOUtil.InputObjectIntoFile(new_container, "container.json");
        }
        if (type == 3){
            new_container = new Container("RE" + Cid, name, weight, port);
            new_container.fuel_consumption_per_km_on_ship = 4.5;
            new_container.fuel_consumption_per_km_on_truck = 5.4;
            FileIOUtil.InputObjectIntoFile(new_container, "container.json");
        }
        if (type == 4){
            new_container = new Container("LI" + Cid, name, weight, port);
            new_container.fuel_consumption_per_km_on_ship = 4.8;
            new_container.fuel_consumption_per_km_on_truck = 5.3;
            FileIOUtil.InputObjectIntoFile(new_container, "container.json");
        }
        return new_container;

    }

    public String getName(){
        return this.name;
    }
    public String getCid(){
        return this.cid;
    }

    public Port getCurrent_port(){
        return this.current_port;
    }
    public void setCurrent_vehicle(Vehicle vehicle){
        this.current_vehicle = vehicle;
    }

    public double getWeight(){
        return this.weight;
    }

    public double getFuel_consumption_per_km_on_ship(){
        return this.fuel_consumption_per_km_on_ship;
    }

    public double getFuel_consumption_per_km_on_truck(){
        return this.fuel_consumption_per_km_on_truck;
    }



    public static Container queryContainerbyID(String Cid) throws IOException {
        for (Container container: FileIOUtil.ReadContainerFromFile() ){
            if (container.cid.equals(Cid)){
                return container;
            }
        }
        System.out.println("Container does not exist");
        return null;
    }

    public void enterPort(Port port) throws IOException {
        this.current_port = port;
        FileIOUtil.updateObjectFromFile("container.json", this);

    }

    public void leavePort() throws IOException {
        this.current_port = null;
        FileIOUtil.updateObjectFromFile("container.json", this);
    }


    public void loadedonVehicle(Vehicle vehicle) throws IOException {
        this.current_vehicle = vehicle;
        FileIOUtil.updateObjectFromFile("container.json", this);
    }

    public void loadedoffVehicle() throws IOException {
        this.current_vehicle = null;
        FileIOUtil.updateObjectFromFile("container.json", this);
    }

}
