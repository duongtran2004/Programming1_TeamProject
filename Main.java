import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        System_Admin testing_admin = new System_Admin("1234567890", "Testing_Admin", "1234567890", "admin");
        //System_Admin.addPort();
        //System_Admin.addPort();
        //System_Admin.addVehicle();
        //System_Admin.addVehicle();
        //System_Admin.addContainer();
        //System_Admin.addManager();
        Port_Manager manager = FileIOUtil.ReadManagerFromFile().get(0);
        //manager.commenceTrip();
        //manager.loadingContainersonVehicle();
        //manager.completeTrip();
        //manager.loadingContaineroffVehicle();
        //manager.RegisteringTrip();
        //System.out.println(FileIOUtil.ReadTripFromFile().get(0));


        //2679861123





    }
}

