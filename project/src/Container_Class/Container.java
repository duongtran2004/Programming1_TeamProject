package Container_Class;

import Port.Port;
import Vehicle_Classes.*;
import FileIO.*;
import Users.*;

import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

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
        return "Container_Class.Container id: " + this.cid + "\n" + "Container_Class.Container name: " + this.name + "\n" + "Current Port.Port: " + this.current_port.getPid() + "Weight: " + this.weight + "\n" + "Per Km Fuel Consumption on Ship " + this.fuel_consumption_per_km_on_ship + "\n" + "Per Km Fuel Consumption on Truck: " + this.fuel_consumption_per_km_on_truck + "\n" + "\n";}


    public static boolean createNewContainer(int type, String name, Port port, double weight) throws IOException {
        String Cid = "";
        Random random_id = new Random();
        for (int i =0; i<=10; i++){
            Cid = Cid + random_id.nextInt(10);
        }
        File file = new File("container.json");
        System.out.println(type);

        if (type == 0){
            Dry_Storage container = new Dry_Storage("DS" + Cid, name, weight, port);
            FileIOUtil.InputObjectIntoFile(container, "dry_storage.json");
            port.setNumberofContainersOnsite(port.getNumberofContainersOnsite() + 1.0);
            port.setCurrentCapacity(port.getCurrentCapacity() + weight);
            FileIOUtil.updatePortFromFile(port);
        }
        if (type == 1){
            Open_Top container = new Open_Top("OT" + Cid, name, weight, port);
            FileIOUtil.InputObjectIntoFile(container, "open_top.json");
            port.setNumberofContainersOnsite(port.getNumberofContainersOnsite() + 1.0);
            port.setCurrentCapacity(port.getCurrentCapacity() + weight);
            FileIOUtil.updatePortFromFile(port);
        }
        if (type == 2){
            Open_Side container = new Open_Side("OS" + Cid, name, weight, port );
            FileIOUtil.InputObjectIntoFile(container, "open_side.json");
            port.setNumberofContainersOnsite(port.getNumberofContainersOnsite() + 1.0);
            port.setCurrentCapacity(port.getCurrentCapacity() + weight);
            FileIOUtil.updatePortFromFile(port);
        }
        if (type == 3){
            Refriderated container = new Refriderated("RE" + Cid, name, weight, port);
            FileIOUtil.InputObjectIntoFile(container, "refridgerated.json");
            port.setNumberofContainersOnsite(port.getNumberofContainersOnsite() + 1.0);
            port.setCurrentCapacity(port.getCurrentCapacity() + weight);
            FileIOUtil.updatePortFromFile(port);
        }
        if (type == 4){
            Liquid container = new Liquid("LI" + Cid, name, weight, port);
            FileIOUtil.InputObjectIntoFile(container, "liquid.json");
            port.setNumberofContainersOnsite(port.getNumberofContainersOnsite() + 1.0);
            port.setCurrentCapacity(port.getCurrentCapacity() + weight);
            FileIOUtil.updatePortFromFile(port);
        }
        return true;

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

    public Vehicle getCurrent_vehicle() {
        return current_vehicle;
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

    public static ArrayList<Container> getContainer() throws IOException {
        ArrayList<Container> containers = new ArrayList<Container>();
        File DS_file = new File("dry_storage.json");
        if (DS_file.exists()){
            for (Dry_Storage container: FileIOUtil.ReadDryStorageFromFile()){
                containers.add((Container) container);
            }
        }
        File OT_file = new File("open_top.json");
        if (OT_file.exists()){
            for (Open_Top container: FileIOUtil.ReadOpen_TopFromFile()){
                containers.add((Container) container);
            }
        }

        File OS_file = new File("open_side.json");
        if (OS_file.exists()){
            for (Open_Side container: FileIOUtil.ReadOpen_SideFromFile()){
                containers.add((Container) container);
            }
        }

        File RE_file = new File("refridgerated.json");
        if (RE_file.exists()){
            for (Refriderated container: FileIOUtil.ReadRefridgeratedFromFile()){
                containers.add((Container) container);
            }
        }

        File LI_file = new File("liquid.json");
        if (LI_file.exists()){
            for (Liquid container: FileIOUtil.ReadLiquidFromFile()){
                containers.add((Container) container);
            }
        }





        return containers;
    }

    public static void sortContainerByWeight(ArrayList<Container> container_list, boolean order){
        if (!order){
            for (int i =0; i < container_list.size(); i++){
                double max_weight = container_list.get(i).getWeight();
                int index = i;
                Container temp = container_list.get(i);
                for (int k = i+1; k < container_list.size(); k++){
                    if (container_list.get(k).getWeight() > max_weight){
                        index = k;
                        max_weight = container_list.get(k).getWeight();
                    }
                }
                container_list.set(i, container_list.get(index));
                container_list.set(index, temp);
            }
        }
        else {
            for (int i =0; i < container_list.size(); i++){
                double min_fuelcapac = container_list.get(i).getWeight();
                int index = i;
                for (int k = i+1; k < container_list.size(); k++){
                    if (container_list.get(k).getWeight() < min_fuelcapac){
                        index = k;
                    }
                }
                container_list.set(i, container_list.get(index));
            }
        }
    }



    public static Container queryContainerbyID(String Cid) throws IOException {
        for (Container container: Container.getContainer() ){
            if (container.cid.equals(Cid)){
                return container;
            }
        }
        System.out.println("Container_Class.Container does not exist");
        return null;
    }

    public void enterPort(Port port) throws IOException {
        this.current_port = port;
        FileIOUtil.updateContainerFromFile(this);

    }

    public void leavePort() throws IOException {
        this.current_port = null;
        FileIOUtil.updateContainerFromFile(this);
    }


    public void loadedonVehicle(Vehicle vehicle) throws IOException {
        this.current_vehicle = vehicle;
        FileIOUtil.updateContainerFromFile(this);
    }

    public void loadedoffVehicle() throws IOException {
        this.current_vehicle = null;
        FileIOUtil.updateContainerFromFile(this);
    }

    public static void filteringContainerbyType() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Please select the vehicle type you want to filter:\nds: Dry Storage\not: Open Top\nos: Open Side\nre: Refridgerated\nli: Container_Class.Liquid\nq: Quit");
            String selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("q")){
                break;
            }
            if (selection.equalsIgnoreCase("ds")){
                ArrayList<Dry_Storage> containers = Dry_Storage.getDryStorage();
                Utlity.sortingDSContainer(containers);
                System.out.println(Utlity.DS_Table(containers));

            }
            else if (selection.equalsIgnoreCase("ot")){
                ArrayList<Open_Top> containers = Open_Top.getOpenTop();
                Utlity.sortingOTContainer(containers);
                System.out.println(Utlity.OT_Table(containers));

            }
            else if (selection.equalsIgnoreCase("os")){
                ArrayList<Open_Side> containers = Open_Side.getOpenSide();
                Utlity.sortingOSContainer(containers);
                System.out.println(Utlity.OS_Table(containers));

            }
            else if (selection.equalsIgnoreCase("re")) {
                ArrayList<Refriderated> containers = Refriderated.getRefridgerated();
                Utlity.sortingREContainer(containers);
                System.out.println(Utlity.RE_Table(containers));


            }
            else if (selection.equalsIgnoreCase("li")) {
                ArrayList<Liquid> containers = Liquid.getLiquid();
                Utlity.sortingLIContainer(containers);
                System.out.println(Utlity.LI_Table(containers));

            }
            else {
                System.out.println("option is not available. Please choose another option");
            }
        }
    }

    public static void filteringContainerbyPortID(ArrayList<Container> containers) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter port id");
        String Pid = scanner.nextLine();
        Port filter_port = Port.queryPortbyID(Pid);
        for (int i =0; i < containers.size(); i++){
            if (!filter_port.equals(containers.get(i).getCurrent_port())){
                containers.remove(containers.get(i));
            }
        }
        Utlity.sortingContainer(containers);
        System.out.println(containers);
    }

}



