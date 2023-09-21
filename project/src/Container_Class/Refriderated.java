package Container_Class;

import Port.Port;
import FileIO.*;

import java.io.IOException;
import java.util.ArrayList;

public class Refriderated extends Container{
    private double fuel_consumption_per_km_on_ship = 4.5;
    private double fuel_consumption_per_km_on_truck = 5.4;

    public Refriderated(){

    }
    public Refriderated(String Cid, String name, double weight, Port port){
        super(Cid, name, weight, port);
    }
    public static ArrayList<Refriderated> getRefridgerated() throws IOException {
        return FileIOUtil.ReadRefridgeratedFromFile();
    }

    @Override
    public double getFuel_consumption_per_km_on_ship() {
        return fuel_consumption_per_km_on_ship;
    }

    public void setFuel_consumption_per_km_on_ship(double fuel_consumption_per_km_on_ship) {
        this.fuel_consumption_per_km_on_ship = fuel_consumption_per_km_on_ship;
    }

    @Override
    public double getFuel_consumption_per_km_on_truck() {
        return fuel_consumption_per_km_on_truck;
    }

    public void setFuel_consumption_per_km_on_truck(double fuel_consumption_per_km_on_truck) {
        this.fuel_consumption_per_km_on_truck = fuel_consumption_per_km_on_truck;
    }

    public static Refriderated queryRefridgeratedbyID(String Cid) throws IOException {
        for (Refriderated container: Refriderated.getRefridgerated()  ){
            if (container.getCid().equals(Cid)){
                return container;
            }
        }
        System.out.println("Container_Class.Container does not exist");
        return null;
    }

    public static void sortContainerby_Weight(ArrayList<Refriderated> container_list, boolean order){
        if (!order){
            for (int i =0; i < container_list.size(); i++){
                double max_weight = container_list.get(i).getWeight();
                int index = i;
                Refriderated temp = container_list.get(i);
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
                double min_weight = container_list.get(i).getWeight();
                int index = i;
                Refriderated temp = container_list.get(i);
                for (int k = i+1; k < container_list.size(); k++){
                    if (container_list.get(k).getWeight() < min_weight){
                        index = k;
                        min_weight = container_list.get(k).getWeight();
                    }
                }
                container_list.set(i, container_list.get(index));
                container_list.set(index, temp);
            }
        }

    }
}
