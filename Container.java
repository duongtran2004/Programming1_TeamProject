import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class Container {
    private String Cid;
    private String name;
    private double weight;
    private double fuel_consumption_per_km_on_ship;
    private double fuel_consumption_per_km_on_truck;
    private static ArrayList<Container> container_list = new ArrayList<Container>();




    public Container(String Cid, String name, double weight){
        this.Cid = Cid;
        this.name = name;
        this.weight = weight;
    }

    public static void createNewContainer(){
        Scanner scanner = new Scanner(System.in);
        int selection = 0;

        while (true){
            System.out.println("Choose 0 for Dry Storage, 1 for Open Top, 2 for Open Side, 3 for Refridgerated, 4 for Liquid");
            selection = scanner.nextInt();
            if (selection >=0 && selection <= 4){
                break;
            }
        }

        System.out.println("Please enter container name");
        String name = scanner.nextLine();

        System.out.println("Please enter container weight");
        double weight = scanner.nextDouble();

        String Cid = "";
        Random random_id = new Random();
        for (int i =0; i<=10; i++){
            Cid = Cid + random_id.nextInt(10);
        }

        if (selection == 0){
            Dry_Storage new_container = new Dry_Storage("DS" + Cid, name, weight);
            container_list.add(new_container);
        }
        if (selection == 1){
            Open_Top new_container = new Open_Top("OT" + Cid, name, weight);
            container_list.add(new_container);
        }
        if (selection == 2){
            Open_Side new_container = new Open_Side("OS" + Cid, name, weight);
            container_list.add(new_container);
        }
        if (selection == 3){
            Refridgerated new_container = new Refridgerated("RE" + Cid, name, weight);
            container_list.add(new_container);
        }
        else{
            Liquid new_container = new Liquid("LI" + Cid, name, weight);
            container_list.add(new_container);
        }

    }

    public String getName(){
        return this.name;
    }

    public double getWeight(){
        return this.weight;
    }

    public double getFuel_consumption_per_km_on_ship(){
        return this.getFuel_consumption_per_km_on_ship();
    }

    public double getFuel_consumption_per_km_on_truck(){
        return this.getFuel_consumption_per_km_on_truck();
    }

    public static Container queryContainerbyID(String Cid){
        for (Container container: container_list){
            if (container.Cid.equals(Cid)){
                return container;
            }
        }
        System.out.println("Container does not exist");
        return null;
    }





}

class Dry_Storage extends Container{
    double fuel_consumption_per_km_on_ship = 3.5;
    double fuel_consumption_per_km_on_truck = 4.6;

    public Dry_Storage(String Cid, String name, double weight){
        super(Cid, name, weight);
    }
}

class Open_Top extends Container{
      double fuel_consumption_per_km_on_ship = 2.8;
      double fuel_consumption_per_km_on_truck = 3.2;

      public Open_Top(String Cid, String name, double weight){
        super(Cid, name, weight);
    }
}

class Open_Side extends Container{
    double fuel_consumption_per_km_on_ship = 2.7;
    double fuel_consumption_per_km_on_truck = 3.2;

    public Open_Side(String Cid, String name, double weight){
        super(Cid, name, weight);
    }
}

class Refridgerated extends Container{
    double fuel_consumption_per_km_on_ship = 4.5;
    double fuel_consumption_per_km_on_truck = 5.4;

    public Refridgerated(String Cid, String name, double weight){
        super(Cid, name, weight);
    }
}

class Liquid extends Container{
    double fuel_consumption_per_km_on_ship = 4.8;
    double fuel_consumption_per_km_on_truck = 5.3;

    public Liquid(String Cid, String name, double weight){
        super(Cid, name, weight);
    }
}