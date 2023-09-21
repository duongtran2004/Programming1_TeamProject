package Users;

import Container_Class.*;
import Port.Port;
import Trip.Trip;
import Vehicle_Classes.*;
import FileIO.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Utlity {
    //-------------------------------------------------------------Table Generation----------------------------------------------------//

    public static String manager_Table(ArrayList<Port_Manager> manager_list){
        String table = "-".repeat(60) + "\n" + "|" + " ".repeat(6) + "ID" + " ".repeat(6) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3) + "|" + " ".repeat(3) + "USERNAME" + " ".repeat(3) + "|" + " ".repeat(3) + "PASSWORD" + " ".repeat(2)+ "|\n" + "-".repeat(60) + "\n";
        for (Port_Manager manager: manager_list){
            table = table + "|" + " ".repeat(2) + manager.getEid() + " ".repeat(2);
            table = table + "|" + " ".repeat(2) + manager.getPort().getPid() + " ".repeat(2);
            if (manager.getUsername().length() % 2 == 0){
                table = table + "|" + " ".repeat((14 - manager.getUsername().length())/2) + manager.getUsername() + " ".repeat((14 - manager.getUsername().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - manager.getUsername().length())/2 + 1) + manager.getUsername() + " ".repeat((14 - manager.getUsername().length())/2);
            }
            if (manager.getUsername().length() % 2 == 0){
                table = table + "|" + " ".repeat((13 - manager.getPassword().length())/2 + 1) + manager.getPassword() + " ".repeat((13 - manager.getPassword().length())/2) + "|\n";
            }
            else {
                table = table + "|" + " ".repeat((13 - manager.getPassword().length())/2) + manager.getPassword() + " ".repeat((13 - manager.getPassword().length())/2) + "|\n";
            }
            table = table +"-".repeat(60) + "\n";

        }
        return table;
    }

    public static String admin_Table(ArrayList<System_Admin> admin_list){
        String table = "-".repeat(45) + "\n" + "|" + " ".repeat(6) + "ID" + " ".repeat(6) + "|" + " ".repeat(3) + "USERNAME" + " ".repeat(3) + "|" + " ".repeat(3) + "PASSWORD" + " ".repeat(2)+ "|\n" + "-".repeat(45);
        for (System_Admin admin: admin_list){
            table = table + "|" + " ".repeat(2) + admin.getEid() + " ".repeat(2);
            if (admin.getUsername().length() % 2 == 0){
                table = table + "|" + " ".repeat((14 - admin.getUsername().length())/2) + admin.getUsername() + " ".repeat((14 - admin.getUsername().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - admin.getUsername().length())/2 + 1) + admin.getUsername() + " ".repeat((14 - admin.getUsername().length())/2);
            }
            if (admin.getUsername().length() % 2 == 0){
                table = table + "|" + " ".repeat((13 - admin.getPassword().length())/2 + 1) + admin.getPassword() + " ".repeat((13 - admin.getPassword().length())/2) + "|\n";
            }
            else {
                table = table + "|" + " ".repeat((13 - admin.getPassword().length())/2) + admin.getPassword() + " ".repeat((13 - admin.getPassword().length())/2) + "|\n";
            }
            table = table +"-".repeat(45) + "\n";

        }
        return table;
    }
    public static String port_Table(ArrayList<Port> ports){
        String table = "-".repeat(79) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " "+ "LANDING"+ " "+ "|" +" LATITUDE " + "|" + "LONGITUDE" + "| CAPACITY |" + "\n" + "-".repeat(79) + "\n";
        for (Port port: ports){
            table = table + "|" + " ".repeat(2) + port.getPid() + " ".repeat(2);
            if (port.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - port.getName().length())/2) + port.getName() + " ".repeat((20 - port.getName().length())/2);
            }
            else {

                table = table + "|" + " ".repeat((20 - port.getName().length())/2 + 1) + port.getName() + " ".repeat((20 - port.getName().length())/2);
            }
            if (port.isLandingAbility()){
                table = table + "|" + " ".repeat(3) + "true" + " ".repeat((2));
            }
            else {
                table = table + "|" + " ".repeat(2) + "false" + " ".repeat((2));
            }
            if (Double.toString(port.getLatitude()).length() % 2 ==0){
                table = table + "|" + " ".repeat((10 - Double.toString(port.getLatitude()).length())/2) + Double.toString(port.getLatitude()) + " ".repeat((10 - Double.toString(port.getLatitude()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((10 - Double.toString(port.getLatitude()).length())/2 + 1) + Double.toString(port.getLatitude()) + " ".repeat((10 - Double.toString(port.getLatitude()).length())/2);
            }
            if (Double.toString(port.getLongitude()).length() % 2 ==0){
                table = table + "|" + " ".repeat((9 - Double.toString(port.getLongitude()).length())/2 + 1) + Double.toString(port.getLongitude()) + " ".repeat((9 - Double.toString(port.getLongitude()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((9 - Double.toString(port.getLongitude()).length())/2) + Double.toString(port.getLongitude()) + " ".repeat((9 - Double.toString(port.getLongitude()).length())/2);
            }
            if (Double.toString(port.getStoringCapacity()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((10 - Double.toString(port.getStoringCapacity()).length())/2) + port.getStoringCapacity() + " ".repeat((10 - Double.toString(port.getStoringCapacity()).length())/2) + "|\n"  ;
            }
            else {
                table = table + "|" + " ".repeat((10 - Double.toString(port.getStoringCapacity()).length())/2 + 1) + port.getStoringCapacity() + " ".repeat((10 - Double.toString(port.getStoringCapacity()).length())/2) + "|\n";
            }
            table = table + "-".repeat(80) + "\n";
        }
        return  table;

    }
    public static String vehicle_Table(ArrayList<Vehicle> vehicles){
        String table = "-".repeat(96) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(3) + "MAX FUEL" + " ".repeat(3) + "|" + " CURRENT FUEL " + "|" + " ".repeat(3) + "CAPACITY" + " ".repeat(2) + "|" + "\n" + "-".repeat(96) + "\n";
        for (Vehicle vehicle: vehicles){
            table = table + "|" + vehicle.getVid() + " ";
            if (vehicle.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2 + 1) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            table = table + "|" + " ".repeat(2) + vehicle.getCurrent_port() + " ".repeat(2);

            if (Double.toString(vehicle.getFuel_capacity()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2 + 1) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            if (Double.toString(vehicle.getCurrent_fuel()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2 + 1) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);
            }
            if (Double.toString(vehicle.getCarrying_capacity()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n"  ;
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2 + 1) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n";
            }
            table = table + "-".repeat(96) + "\n";
        }
        return  table;

    }
    public static String ship_Table(ArrayList<ship> vehicles){
        String table = "-".repeat(96) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(3) + "MAX FUEL" + " ".repeat(3) + "|" + " CURRENT FUEL " + "|" + " ".repeat(3) + "CAPACITY" + " ".repeat(2) + "|" + "\n" + "-".repeat(96) + "\n";
        for (ship vehicle: vehicles){
            table = table + "|" + vehicle.getVid() + " ";
            if (vehicle.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2 + 1) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            table = table + "|" + " ".repeat(2) + vehicle.getCurrent_port() + " ".repeat(2);

            if (Double.toString(vehicle.getFuel_capacity()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2 + 1) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            if (Double.toString(vehicle.getCurrent_fuel()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2 + 1) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);
            }
            if (Double.toString(vehicle.getCarrying_capacity()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n"  ;
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2 + 1) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n";
            }
            table = table + "-".repeat(96) + "\n";
        }
        return  table;

    }
    public static String basic_Table(ArrayList<basic_truck> vehicles){
        String table = "-".repeat(96) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(3) + "MAX FUEL" + " ".repeat(3) + "|" + " CURRENT FUEL " + "|" + " ".repeat(3) + "CAPACITY" + " ".repeat(2) + "|" + "\n" + "-".repeat(96) + "\n";
        for (basic_truck vehicle: vehicles){
            table = table + "|" + vehicle.getVid() + " ";
            if (vehicle.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2 + 1) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            table = table + "|" + " ".repeat(2) + vehicle.getCurrent_port() + " ".repeat(2);

            if (Double.toString(vehicle.getFuel_capacity()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2 + 1) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            if (Double.toString(vehicle.getCurrent_fuel()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2 + 1) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);
            }
            if (Double.toString(vehicle.getCarrying_capacity()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n"  ;
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2 + 1) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n";
            }
            table = table + "-".repeat(96) + "\n";
        }
        return  table;

    }
    public static String reefer_Table(ArrayList<reefer_truck> vehicles){
        String table = "-".repeat(96) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(3) + "MAX FUEL" + " ".repeat(3) + "|" + " CURRENT FUEL " + "|" + " ".repeat(3) + "CAPACITY" + " ".repeat(2) + "|" + "\n" + "-".repeat(96) + "\n";
        for (reefer_truck vehicle: vehicles){
            table = table + "|" + vehicle.getVid() + " ";
            if (vehicle.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2 + 1) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            table = table + "|" + " ".repeat(2) + vehicle.getCurrent_port() + " ".repeat(2);

            if (Double.toString(vehicle.getFuel_capacity()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2 + 1) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            if (Double.toString(vehicle.getCurrent_fuel()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2 + 1) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);
            }
            if (Double.toString(vehicle.getCarrying_capacity()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n"  ;
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2 + 1) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n";
            }
            table = table + "-".repeat(96) + "\n";
        }
        return  table;

    }
    public static String tanker_Table(ArrayList<tanker_truck> vehicles){
        String table = "-".repeat(96) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(3) + "MAX FUEL" + " ".repeat(3) + "|" + " CURRENT FUEL " + "|" + " ".repeat(3) + "CAPACITY" + " ".repeat(2) + "|" + "\n" + "-".repeat(96) + "\n";
        for (tanker_truck vehicle: vehicles){
            table = table + "|" + vehicle.getVid() + " ";
            if (vehicle.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - vehicle.getName().length())/2 + 1) + vehicle.getName() + " ".repeat((20 - vehicle.getName().length())/2);
            }
            table = table + "|" + " ".repeat(2) + vehicle.getCurrent_port() + " ".repeat(2);

            if (Double.toString(vehicle.getFuel_capacity()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2 + 1) + Double.toString(vehicle.getFuel_capacity()) + " ".repeat((14 - Double.toString(vehicle.getFuel_capacity()).length())/2);
            }
            if (Double.toString(vehicle.getCurrent_fuel()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2 + 1) + Double.toString(vehicle.getCurrent_fuel()) + " ".repeat((14 - Double.toString(vehicle.getCurrent_fuel()).length())/2);
            }
            if (Double.toString(vehicle.getCarrying_capacity()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n"  ;
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(vehicle.getCarrying_capacity()).length())/2 + 1) + vehicle.getCarrying_capacity() + " ".repeat((13 - Double.toString(vehicle.getCarrying_capacity()).length())/2) + "|\n";
            }
            table = table + "-".repeat(96) + "\n";
        }
        return  table;

    }


















    public static String container_Table(ArrayList<Container> containers){
        String table = "-".repeat(111) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(4) + "WEIGHT" + " ".repeat(4) + "|" + " ".repeat(3) + "SHIP L/KM" + " ".repeat(2) + "|" + " ".repeat(2) + "TRUCK L/KM" + " " +"|\n" + "-".repeat(111)+"\n";
        for (Container container: containers){
            table = table + "|" + container.getCid() + " ";
            if (container.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - container.getName().length())/2) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - container.getName().length())/2 + 1) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            table = table + "|" + " ".repeat(2) + container.getCurrent_port() + " ".repeat(2);

            if(container.getCurrent_vehicle() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else{
                table = table + "|" + container.getCurrent_vehicle() + " ";
            }

            if (Double.toString(container.getWeight()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2 + 1) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            if (Double.toString(container.getFuel_consumption_per_km_on_ship()).length() % 2 ==0){
                if (container.getCid().startsWith("DS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_ship()).length())/2) + ((Dry_Storage) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_ship()).length())/2);
                }
                else if (container.getCid().startsWith("OT")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_ship()).length())/2) + ((Open_Top) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_ship()).length())/2);
                }
                else if (container.getCid().startsWith("OS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_ship()).length())/2) + ((Open_Side) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_ship()).length())/2);
                }
                else if (container.getCid().startsWith("RE")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_ship()).length())/2) + ((Refriderated) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_ship()).length())/2);
                }
                else {
                    table = table + "|" + " ".repeat((14 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_ship()).length())/2) + ((Liquid) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_ship()).length())/2);
                }


            }
            else {
                if (container.getCid().startsWith("DS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_ship()).length())/2 + 1) + ((Dry_Storage) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_ship()).length())/2);
                }
                else if (container.getCid().startsWith("OT")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_ship()).length())/2 + 1) + ((Open_Top) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_ship()).length())/2);
                }
                else if (container.getCid().startsWith("OS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_ship()).length())/2 + 1) + ((Open_Side) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_ship()).length())/2);
                }
                else if (container.getCid().startsWith("RE")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_ship()).length())/2+ 1) + ((Refriderated) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_ship()).length())/2);
                }
                else {
                    table = table + "|" + " ".repeat((14 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_ship()).length())/2 + 1) + ((Liquid) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_ship()).length())/2);
                }
            }
            if (Double.toString(container.getFuel_consumption_per_km_on_truck()).length() % 2 == 0 ){
                if (container.getCid().startsWith("DS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_truck()).length())/2) + ((Dry_Storage) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_truck()).length())/2 ) + "|\n";
                }
                else if (container.getCid().startsWith("OT")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_truck()).length())/2) + ((Open_Top) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
                else if (container.getCid().startsWith("OS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_truck()).length())/2) + ((Open_Side) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
                else if (container.getCid().startsWith("RE")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_truck()).length())/2) + ((Refriderated) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
                else {
                    table = table + "|" + " ".repeat((14 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_truck()).length())/2) + ((Liquid) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
            }
            else {
                if (container.getCid().startsWith("DS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_truck()).length())/2 + 1) + ((Dry_Storage) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_truck()).length())/2 ) + "|\n";
                }
                else if (container.getCid().startsWith("OT")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_truck()).length())/2 + 1) + ((Open_Top) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
                else if (container.getCid().startsWith("OS")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_truck()).length())/2 + 1) + ((Open_Side) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
                else if (container.getCid().startsWith("RE")){
                    table = table + "|" + " ".repeat((14 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_truck()).length())/2 + 1) + ((Refriderated) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
                else {
                    table = table + "|" + " ".repeat((14 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_truck()).length())/2 + 1) + ((Liquid) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
                }
            }
            table = table + "-".repeat(111) + "\n";
        }
        return  table;

    }
    public static String DS_Table(ArrayList<Dry_Storage> containers){
        String table = "-".repeat(111) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(4) + "WEIGHT" + " ".repeat(4) + "|" + " ".repeat(3) + "SHIP L/KM" + " ".repeat(2) + "|" + " ".repeat(2) + "TRUCK L/KM" + " " +"|\n" + "-".repeat(111)+"\n";
        for (Dry_Storage container: containers){
            table = table + "|" + container.getCid() + " ";
            if (container.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - container.getName().length())/2) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - container.getName().length())/2 + 1) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            table = table + "|" + " ".repeat(2) + container.getCurrent_port() + " ".repeat(2);

            if(container.getCurrent_vehicle() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else{
                table = table + "|" + container.getCurrent_vehicle() + " ";
            }

            if (Double.toString(container.getWeight()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2 + 1) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            if (Double.toString(container.getFuel_consumption_per_km_on_ship()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_ship()).length())/2) + ((Dry_Storage) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_ship()).length())/2);

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_ship()).length())/2 + 1) + ((Dry_Storage) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_ship()).length())/2);

            }
            if (Double.toString(container.getFuel_consumption_per_km_on_truck()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_truck()).length())/2) + ((Dry_Storage) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_truck()).length())/2 ) + "|\n";

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_truck()).length())/2 + 1) + ((Dry_Storage) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Dry_Storage) container).getFuel_consumption_per_km_on_truck()).length())/2 ) + "|\n";

            }
            table = table + "-".repeat(111) + "\n";
        }
        return  table;

    }
    public static String OT_Table(ArrayList<Open_Top> containers){
        String table = "-".repeat(111) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(4) + "WEIGHT" + " ".repeat(4) + "|" + " ".repeat(3) + "SHIP L/KM" + " ".repeat(2) + "|" + " ".repeat(2) + "TRUCK L/KM" + " " +"|\n" + "-".repeat(111)+"\n";
        for (Open_Top container: containers){
            table = table + "|" + container.getCid() + " ";
            if (container.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - container.getName().length())/2) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - container.getName().length())/2 + 1) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            table = table + "|" + " ".repeat(2) + container.getCurrent_port() + " ".repeat(2);

            if(container.getCurrent_vehicle() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else{
                table = table + "|" + container.getCurrent_vehicle() + " ";
            }

            if (Double.toString(container.getWeight()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2 + 1) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            if (Double.toString(container.getFuel_consumption_per_km_on_ship()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_ship()).length())/2) + ((Open_Top) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_ship()).length())/2);



            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_ship()).length())/2 + 1) + ((Open_Top) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_ship()).length())/2);
            }
            if (Double.toString(container.getFuel_consumption_per_km_on_truck()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_truck()).length())/2) + ((Open_Top) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_truck()).length())/2 + 1) + ((Open_Top) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Open_Top) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";

            }
            table = table + "-".repeat(111) + "\n";
        }
        return  table;

    }
    public static String OS_Table(ArrayList<Open_Side> containers){
        String table = "-".repeat(111) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(4) + "WEIGHT" + " ".repeat(4) + "|" + " ".repeat(3) + "SHIP L/KM" + " ".repeat(2) + "|" + " ".repeat(2) + "TRUCK L/KM" + " " +"|\n" + "-".repeat(111)+"\n";
        for (Open_Side container: containers){
            table = table + "|" + container.getCid() + " ";
            if (container.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - container.getName().length())/2) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - container.getName().length())/2 + 1) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            table = table + "|" + " ".repeat(2) + container.getCurrent_port() + " ".repeat(2);

            if(container.getCurrent_vehicle() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else{
                table = table + "|" + container.getCurrent_vehicle() + " ";
            }

            if (Double.toString(container.getWeight()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2 + 1) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            if (Double.toString(container.getFuel_consumption_per_km_on_ship()).length() % 2 ==0){
                    table = table + "|" + " ".repeat((14 - Double.toString(container.getFuel_consumption_per_km_on_ship()).length())/2) + ((Open_Side) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_ship()).length())/2);
            }
            else {
                    table = table + "|" + " ".repeat((14 - Double.toString(container.getFuel_consumption_per_km_on_ship()).length())/2 + 1) + ((Open_Side) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_ship()).length())/2);

            }
            if (Double.toString(container.getFuel_consumption_per_km_on_truck()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_truck()).length())/2) + ((Open_Side) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";

            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_truck()).length())/2 + 1) + ((Open_Side) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Open_Side) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
            }
            table = table + "-".repeat(111) + "\n";
        }
        return  table;

    }
    public static String RE_Table(ArrayList<Refriderated> containers){
        String table = "-".repeat(111) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(4) + "WEIGHT" + " ".repeat(4) + "|" + " ".repeat(3) + "SHIP L/KM" + " ".repeat(2) + "|" + " ".repeat(2) + "TRUCK L/KM" + " " +"|\n" + "-".repeat(111)+"\n";
        for (Refriderated container: containers){
            table = table + "|" + container.getCid() + " ";
            if (container.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - container.getName().length())/2) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - container.getName().length())/2 + 1) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            table = table + "|" + " ".repeat(2) + container.getCurrent_port() + " ".repeat(2);

            if(container.getCurrent_vehicle() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else{
                table = table + "|" + container.getCurrent_vehicle() + " ";
            }

            if (Double.toString(container.getWeight()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2 + 1) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            if (Double.toString(container.getFuel_consumption_per_km_on_ship()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_ship()).length())/2) + ((Refriderated) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_ship()).length())/2);
            }

            else {
                table = table + "|" + " ".repeat((14 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_ship()).length())/2+ 1) + ((Refriderated) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_ship()).length())/2);

            }
            if (Double.toString(container.getFuel_consumption_per_km_on_truck()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_truck()).length())/2) + ((Refriderated) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_truck()).length())/2 + 1) + ((Refriderated) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Refriderated) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";

            }
            table = table + "-".repeat(111) + "\n";
        }
        return  table;

    }
    public static String LI_Table(ArrayList<Liquid> containers){
        String table = "-".repeat(111) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" + " ".repeat(8) + "NAME"+ " ".repeat(8) + "|" + " ".repeat(4)+ "PORT ID"+ " ".repeat(3)+ "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(4) + "WEIGHT" + " ".repeat(4) + "|" + " ".repeat(3) + "SHIP L/KM" + " ".repeat(2) + "|" + " ".repeat(2) + "TRUCK L/KM" + " " +"|\n" + "-".repeat(111)+"\n";
        for (Liquid container: containers){
            table = table + "|" + container.getCid() + " ";
            if (container.getName().length() % 2 == 0){
                table = table + "|" + " ".repeat((20 - container.getName().length())/2) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            else {
                table = table + "|" + " ".repeat((20 - container.getName().length())/2 + 1) + container.getName() + " ".repeat((20 - container.getName().length())/2);
            }
            table = table + "|" + " ".repeat(2) + container.getCurrent_port() + " ".repeat(2);

            if(container.getCurrent_vehicle() == null){
                table = table + "|" + " ".repeat(5) + "NULL" + " ".repeat(5);
            }
            else{
                table = table + "|" + container.getCurrent_vehicle() + " ";
            }

            if (Double.toString(container.getWeight()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(container.getWeight()).length())/2 + 1) + container.getWeight() + " ".repeat((14 - Double.toString(container.getWeight()).length())/2);
            }
            if (Double.toString(container.getFuel_consumption_per_km_on_ship()).length() % 2 ==0){
                table = table + "|" + " ".repeat((14 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_ship()).length())/2) + ((Liquid) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_ship()).length())/2);
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_ship()).length())/2 + 1) + ((Liquid) container).getFuel_consumption_per_km_on_ship() + " ".repeat((14 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_ship()).length())/2);
            }
            if (Double.toString(container.getFuel_consumption_per_km_on_truck()).length() % 2 == 0 ){
                table = table + "|" + " ".repeat((14 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_truck()).length())/2) + ((Liquid) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
            }
            else {
                table = table + "|" + " ".repeat((14 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_truck()).length())/2 + 1) + ((Liquid) container).getFuel_consumption_per_km_on_truck() + " ".repeat((12 - Double.toString(((Liquid) container).getFuel_consumption_per_km_on_truck()).length())/2) + "|\n";
            }
            table = table + "-".repeat(111) + "\n";
        }
        return  table;

    }

    public static String trip_Table(ArrayList<Trip> trip_list){
        String table = "-".repeat(134) + "\n" + "|" + " ".repeat(6) + "ID"+ " ".repeat(6) + "|" +" ".repeat(2) + "VEHICLE ID" + " ".repeat(2) + "|" + " ".repeat(3) + "D_PORT ID" + " ".repeat(2) + "|" + " ".repeat(3) + "D_PORT ID" + " ".repeat(2) + "|" + " ".repeat(6) + "DATE OF DEPARTURE" + " ".repeat(6) + "|" + " ".repeat(7) + "DATE OF ARRIVAL" + " ".repeat(6) +"|" + " ".repeat(3) + "STATUS" + " ".repeat(3) + "|\n"+  "-".repeat(134) + "\n";
        for (Trip trip: trip_list){
            table = table + "|" + " " + trip.getTid() + " ";
            table = table + "|" + trip.getVehicle() + " ";
            table = table + "|" + " ".repeat(2) + trip.getD_port() + " ".repeat(2);
            table = table + "|" + " ".repeat(2) + trip.getA_port() + " ".repeat(2);
            SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
            String Dod = dateFormatter.format(trip.getDate_of_departure());
            String Doa = dateFormatter.format(trip.getDate_of_arrival());
            if (Dod.length() % 2 == 0){
                table = table + "|" + " ".repeat(((29 - Dod.length())/2 + 1)) + Dod + " ".repeat((29 - Dod.length())/2);
            }
            else{
                table = table + "|" + " ".repeat(( 29 - Dod.length()/2)) + Dod + " ".repeat((29 - Dod.length())/2);
            }
            if (Doa.length() % 2 == 0){
                table = table + "|" + " ".repeat(((29 - Doa.length())/2 + 1)) + Doa + " ".repeat((29- Doa.length())/2);
            }
            else {
                table = table + "|" + " ".repeat(((29 - Doa.length())/2 + 1)) + Doa + " ".repeat((29 - Doa.length())/2);
            }
            table = table + "|" + " ".repeat((12 - trip.getStatus().length())/2 ) + trip.getStatus() + " ".repeat((12 - trip.getStatus().length())/2) + "|\n" + "-".repeat(134) + "\n";




        }
        return table;
    }




    //Y-N verification
    public static boolean yes_Or_no(String string){
        Scanner scanner = new Scanner(System.in);
        String rep = "";
        while (true){
            System.out.println(string);
            rep = scanner.nextLine();
            if (rep.equalsIgnoreCase("y") || rep.equalsIgnoreCase("n")){
                if (rep.equalsIgnoreCase("y")){
                    return true;
                }
                else {
                    return false;
                }

            }
            else {
                System.out.println("Option does not exist. Please enter again");
            }
        }

    }
    //--------------------------------------------Sorting Ports-----------------------------------------------------//
    public static void sortingPort(ArrayList<Port> ports){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the ports by storing capacity? (Y or N) ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("n")){
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                System.out.println("By what order would you like to sort the list? (true: ascending _ false: descending)");
                boolean order = scanner.nextBoolean();
                Port.sortPortbyStoringCapacity(ports, order);
                break;
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
    }
    //--------------------------------------------Sorting Vehicle_Classes.Vehicle---------------------------------------------------//
    public static void sortingVehicle(ArrayList<Vehicle> vehicles){
        int selection = 0;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list? (Y or N) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                while (true){
                    System.out.println("Please select the property you want to sort the list by:\n1: Max Carrying Capacity\n2: Fuel Capacity\n3: Current Fuel ");
                    selection = scanner.nextInt();
                    System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                    boolean order = scanner.nextBoolean();
                    if (selection == 1){
                        Vehicle.sortVehicleby_MaxCapacity(vehicles, order);
                        break;
                    }
                    else if (selection == 2){
                        Vehicle.sortVehicleby_FuelCapacity(vehicles, order);
                        break;

                    }
                    else if (selection == 3){
                        Vehicle.sortVehicleby_CurrentFuel(vehicles, order);
                        break;
                    }
                    else{
                        System.out.println("option does not exist. Please choose again");
                    }
                }
                scanner.nextLine();
                System.out.println("Would you like to continue sorting? Y-N");
                response = scanner.nextLine();
                if (response.equals("N")){
                    break;
                }

            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }

    }

    public static void sortingByShip(ArrayList<ship> vehicles){
        String selection = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list? (Y or N) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                while (true){
                    System.out.println("Please select the property you want to sort the list by:\na: Max Carrying Capacity\nb: Fuel Capacity\nc: Current Fuel\nq: Quit ");
                    selection = scanner.nextLine();
                    System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                    boolean order = scanner.nextBoolean();
                    scanner.nextLine();
                    if (selection.equalsIgnoreCase("q")){
                        break;
                    }
                    if (selection.equalsIgnoreCase("a")){
                        ship.sortby_MaxCapacity(vehicles, order);
                        System.out.println(Utlity.ship_Table(vehicles));
                        break;
                    }
                    else if (selection.equalsIgnoreCase("b")){
                        ship.sortby_FuelCapacity(vehicles, order);
                        System.out.println(Utlity.ship_Table(vehicles));
                        break;
                    }
                    else if (selection.equalsIgnoreCase("c")){
                        ship.sortby_CurrentFuel(vehicles, order);
                        System.out.println(Utlity.ship_Table(vehicles));
                        break;
                    }
                    else{
                        System.out.println("option does not exist. Please choose again");
                    }
                }
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }
    }

    public static void sortingBasicTruck(ArrayList<basic_truck> vehicles){
        String selection = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list? (Y or N) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                while (true){
                    System.out.println("Please select the property you want to sort the list by:\na: Max Carrying Capacity\nb: Fuel Capacity\nc: Current Fuel\nq: Quit ");
                    selection = scanner.nextLine();
                    System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                    boolean order = scanner.nextBoolean();
                    scanner.nextLine();
                    if (selection.equalsIgnoreCase("q")){
                        break;
                    }
                    if (selection.equalsIgnoreCase("a")){
                        basic_truck.sortby_MaxCapacity(vehicles, order);
                        System.out.println(Utlity.basic_Table(vehicles));
                        break;
                    }
                    else if (selection.equalsIgnoreCase("b")){
                        basic_truck.sortby_FuelCapacity(vehicles, order);
                        System.out.println(Utlity.basic_Table(vehicles));
                        break;
                    }
                    else if (selection.equalsIgnoreCase("c")){
                        basic_truck.sortby_CurrentFuel(vehicles, order);
                        System.out.println(Utlity.basic_Table(vehicles));
                        break;

                    }
                    else{
                        System.out.println("option does not exist. Please choose again");
                    }
                }
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }
    }


    public static void sortingReefer(ArrayList<reefer_truck> vehicles){
        String selection = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list? (Y or N) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                while (true){
                    System.out.println("Please select the property you want to sort the list by:\na: Max Carrying Capacity\nb: Fuel Capacity\nc: Current Fuel\nq: Quit ");
                    selection = scanner.nextLine();
                    System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                    boolean order = scanner.nextBoolean();
                    scanner.nextLine();
                    if (selection.equals("q")){
                        break;
                    }
                    if (selection.equalsIgnoreCase("a")){
                        reefer_truck.sortby_MaxCapacity(vehicles, order);
                        System.out.println(Utlity.reefer_Table(vehicles));
                        break;
                    }
                    else if (selection.equalsIgnoreCase("b")){
                        reefer_truck.sortby_FuelCapacity(vehicles, order);
                        System.out.println(Utlity.reefer_Table(vehicles));
                        break;
                    }
                    else if (selection.equalsIgnoreCase("c")){
                        reefer_truck.sortby_CurrentFuel(vehicles, order);
                        System.out.println(Utlity.reefer_Table(vehicles));
                        break;
                    }
                    else{
                        System.out.println("option does not exist. Please choose again");
                    }
                }
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }
    }

    public static void sortingTanker(ArrayList<tanker_truck> vehicles){
        String selection = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list? (Y or N) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                while (true){
                    System.out.println("Please select the property you want to sort the list by:\na: Max Carrying Capacity\nb: Fuel Capacity\nc: Current Fuel\nq: Quit ");
                    selection = scanner.nextLine();
                    System.out.println("What order you would like to sort your list? (true: ascending _ false: descending");
                    boolean order = scanner.nextBoolean();
                    scanner.nextLine();
                    if (selection.equalsIgnoreCase("q")){
                        break;
                    }
                    if (selection.equalsIgnoreCase("a")){
                        tanker_truck.sortby_MaxCapacity(vehicles, order);
                        System.out.println(Utlity.tanker_Table(vehicles));
                        break;
                    }
                    else if (selection.equalsIgnoreCase("b")){
                        tanker_truck.sortby_FuelCapacity(vehicles, order);
                        System.out.println(Utlity.tanker_Table(vehicles));
                        break;
                    }
                    else if (selection.equalsIgnoreCase("c")){
                        tanker_truck.sortby_CurrentFuel(vehicles, order);
                        System.out.println(Utlity.tanker_Table(vehicles));
                        break;
                    }
                    else{
                        System.out.println("option does not exist. Please choose again");
                    }
                }
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }
        }
    }

    //---------------------------------------Sorting Containers------------------------------------------//

    public static void sortingContainer(ArrayList<Container> containers){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list by weight? (Y or N) ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("n")){
                break;
            }
            else if (response.equalsIgnoreCase("y")) {

                while (true){
                    System.out.println("By what order would you like to sort the list? (t: ascending _ f: descending)");
                    String selection = scanner.nextLine();
                    if (selection.equalsIgnoreCase("t")){
                        Container.sortContainerByWeight(containers, true);
                        break;
                    }
                    else if (selection.equalsIgnoreCase("f")){
                        Container.sortContainerByWeight(containers, false);
                        break;
                    }
                    else {
                        System.out.println("Option does not exist. Please try again");

                    }
                }
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
    }

    public static void sortingDSContainer(ArrayList<Dry_Storage> containers){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list by weight? (Y or N) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                System.out.println("By what order would you like to sort the list? (true: ascending _ false: descending)");
                boolean order = scanner.nextBoolean();
                scanner.nextLine();
                Dry_Storage.sortContainerby_Weight(containers, order);
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }

    }
    public static void sortingOTContainer(ArrayList<Open_Top> containers){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list by weight? (Y or N) ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("n")){
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                System.out.println("By what order would you like to sort the list? (true: ascending _ false: descending)");
                boolean order = scanner.nextBoolean();
                scanner.nextLine();
                Open_Top.sortContainerby_Weight(containers, order);
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
    }
    public static void sortingOSContainer(ArrayList<Open_Side> containers){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list by weight? (Y or N) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                System.out.println("By what order would you like to sort the list? (true: ascending _ false: descending)");
                boolean order = scanner.nextBoolean();
                scanner.nextLine();
                Open_Side.sortContainerby_Weight(containers, order);
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
    }
    public static void sortingREContainer(ArrayList<Refriderated> containers){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list by weight? (Y or N) ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("n")){
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                System.out.println("By what order would you like to sort the list? (true: ascending _ false: descending)");
                boolean order = scanner.nextBoolean();
                scanner.nextLine();
                Refriderated.sortContainerby_Weight(containers, order);
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
    }
    public static void sortingLIContainer(ArrayList<Liquid> containers){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list by weight? (Y or N) ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("n")){
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                System.out.println("By what order would you like to sort the list? (true: ascending _ false: descending)");
                boolean order = scanner.nextBoolean();
                scanner.nextLine();
                Liquid.sortContainerby_Weight(containers, order);
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
    }

    //---------------------------------------------------Sorting Container_Class.Open_Top.Trip.Trip-------------------------------------------------------//
    public static void sortingTrip(ArrayList<Trip> trip_list){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Would you like to sort the list? (Y or M) ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("n")){
                break;
            }
            else if (response.equalsIgnoreCase("y")) {
                System.out.println("Please choose the property you would like to sort this list by:\n1: Date of Departure\n2: Date of Arrival\n0: Quit");
                while (true){
                    int selection = scanner.nextInt();
                    System.out.println("In which order would you like to sort this list? (true: ascending _ false: descending");
                    boolean order = scanner.nextBoolean();
                    if (selection == 0){
                        break;
                    }
                    else if (selection == 1 ){
                        Trip.sortTripbyDod(trip_list, order);
                    }
                    else if (selection == 2){
                        Trip.sortTripbyDod(trip_list, order);
                    }
                    else {
                        System.out.println("Option does not exist. Please choose again");
                    }
                }
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }

        }
    }

    //---------------------------------------------------------------Filter Methods------------------------------------------------------//

    public static boolean viewManager(ArrayList<Port_Manager> managers) throws IOException {
        System.out.println(Utlity.manager_Table(managers));
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Press p if you do want to filter by current port, s if you want to search manager by ID, and q to quit");
            String selection = scanner.nextLine();
            scanner.nextLine();
            if (selection.equalsIgnoreCase("q")){
                break;
            }
            else if (selection.equalsIgnoreCase("p")){
                Port_Manager.filteringManagerbyPort(managers);
                System.out.println(Utlity.manager_Table(managers));
            }
            else if (selection.equalsIgnoreCase("s")){
                System.out.println("Please enter manager ID");
                String Eid = scanner.nextLine();
                System.out.println(Port_Manager.queryManagerbyID(Eid));
            }
            else {
                System.out.println("Option does not exist. Please choose again.");
            }
        }
        return true;


    }

    public static boolean viewAdmin(ArrayList<System_Admin> admins){
        System.out.println(admin_Table(admins));
        return true;
    }

    public static boolean viewVehicle(ArrayList<Vehicle> vehicles) throws IOException {
        //Vehicle_Classes.Vehicle can be filtered by: type(Check the Vid, each type has a distinct id pattern)
        //Vehicle_Classes.Vehicle can be sorted by: fuel_capacity, current fuel, carrying capacity
        Scanner scanner = new Scanner(System.in);
        Utlity.sortingVehicle(vehicles);
        System.out.println(Utlity.vehicle_Table(vehicles));

        while (true){
            vehicles = Vehicle.getVehicles();
            System.out.println("Press t if you want to filter by type, p if you do want to filter by current port, s to search for vehicle by ID, and q to quit");
            String selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("q")){
                break;
            }
            else if (selection.equalsIgnoreCase("t")){
                Vehicle.filteringVehiclebyType();
            }
            else if (selection.equalsIgnoreCase("p")){
                Vehicle.filteringVehiclebyPortID(vehicles);
            }
            else if (selection.equalsIgnoreCase("s")){
                System.out.println("Please enter Vehicle ID");
                String Vid = scanner.nextLine();
                System.out.println(Vehicle.queryVehiclebyID(Vid));
            }
            else {
                System.out.println("option is not available. Please choose another option");
            }

        }
        return true;
    }

    public static boolean viewContainers(ArrayList<Container> container_list) throws IOException {
        //Container_Class.Container can be filtered by: type(Check the Cid, each type has a distinct id pattern)
        //Container_Class.Container can be sorted by: weight
        Scanner scanner = new Scanner(System.in);
        Utlity.sortingContainer(container_list);
        System.out.println(Utlity.container_Table(container_list));
        while (true){
            container_list = Container.getContainer();
            System.out.println("Press t if you want to filter by type, p if you do want to filter by current port, s if you want to search container by ID, and q to quit");
            String selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("q")){
                break;
            }
            else if (selection.equalsIgnoreCase("t")){
                Container.filteringContainerbyType();
            }
            else if (selection.equalsIgnoreCase("p")){
                Container.filteringContainerbyPortID(container_list);
            }
            else if (selection.equalsIgnoreCase("s")){
                System.out.println("Please enter container ID");
                String Cid = scanner.nextLine();
                System.out.println(Container.queryContainerbyID(Cid));
            }

            else {
                System.out.println("option is not available. Please choose another option");
            }

        }
        return true;
    }



    public static boolean viewPort(ArrayList<Port> port_list) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Utlity.sortingPort(port_list);
        System.out.println(Utlity.port_Table(port_list));
        while (true){
            System.out.println("Choose ft if you want to filter ports by their landing ability, s if you want to search Port by ID, and q if you do not");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("q")){
                break;
            }
            else if (response.equalsIgnoreCase("ft")){
                Port.filteringPortsbyLandingAbility(port_list);
                System.out.println(Utlity.port_Table(port_list));
            }
            else if (response.equalsIgnoreCase("s")){
                System.out.println("Please enter Port ID");
                String Pid = scanner.nextLine();
                System.out.println(Port.queryPortbyID(Pid));
            }
            else {
                System.out.println("Option does not exist. Please choose again");
            }

        }

        return true;
    }

    public static boolean viewTrips(ArrayList<Trip> filtered_list) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Utlity.trip_Table(filtered_list));
        while (true){
            filtered_list = FileIOUtil.ReadTripFromFile();
            System.out.println("Press f if you want to filter by type and q if you do not want to filter");
            String selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("q")){
                break;
            }
            else if (selection.equalsIgnoreCase("f")){
                while (true){
                    System.out.println("What would you like to filter by:\nv: Vehicle\npa: Port of Arrival\npd: Port of Departure\ns: ID\nst: status\nq: Quit");
                    selection = scanner.nextLine();
                    if (selection.equalsIgnoreCase("q")){
                        break;
                    }
                    else if (selection.equalsIgnoreCase("v")){
                        Trip.filteringTripsbyVehicle(filtered_list);
                        System.out.println(Utlity.trip_Table(filtered_list));
                    }
                    else if (selection.equalsIgnoreCase("pa")) {
                        Trip.filteringTripbyArrivalPort(filtered_list);
                        System.out.println(Utlity.trip_Table(filtered_list));

                    }
                    else if (selection.equalsIgnoreCase("pd")){
                        Trip.filteringTripbyDeparturePort(filtered_list);
                        System.out.println(Utlity.trip_Table(filtered_list));

                    }
                    else if (selection.equalsIgnoreCase("s")){
                        System.out.println("Please enter trip ID");
                        String Tid = scanner.nextLine();
                        System.out.println(Trip.queryByName(Tid));
                    }
                    else if (selection.equalsIgnoreCase("st")){
                        Trip.filteringTripbyStatus(filtered_list);
                        System.out.println(Utlity.trip_Table(filtered_list));
                    }
                    else {
                        System.out.println("Option does not exist. Please enter again");
                    }
                }
            }
            
        }

        return true;

    }




}

