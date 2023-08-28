import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class Container implements Serializable {
    private String Cid;
    private String name;
    private double weight;
    private double fuel_consumption_per_km_on_ship;
    private double fuel_consumption_per_km_on_truck;



    public Container(String Cid, String name, double weight){
        this.Cid = Cid;
        this.name = name;
        this.weight = weight;
    }

    public String toString(){
        return "Container id: " + this.Cid + "\n" + "Container name: " + this.name + "\n" + "Weight: " + this.weight + "\n" + "Per Km Fuel Consumption on Ship " + this.fuel_consumption_per_km_on_ship + "\n" + "Per Km Fuel Consumption on Truck: " + this.fuel_consumption_per_km_on_truck + "\n";}

    public static void inputContainerIntoFile(File file, Container container){
        if (file.length() == 0 ){
            try{
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("container.txt"));
                oos.writeObject(container);
                oos.close();

                oos.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            try{
                AppendObjectOutputStream oos = new AppendObjectOutputStream(new FileOutputStream("container.txt", true));
                oos.writeObject(container);
                oos.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void createNewContainer(){
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        System.out.println("Please enter container name");
        String name = scanner.nextLine();
        while (true){
            System.out.println("Choose 0 for Dry Storage, 1 for Open Top, 2 for Open Side, 3 for Refridgerated, 4 for Liquid");
            selection = scanner.nextInt();
            if (selection >=0 && selection <= 4){
                break;
            }
        }

        System.out.println("Please enter container weight");
        double weight = scanner.nextDouble();

        String Cid = "";
        Random random_id = new Random();
        for (int i =0; i<=10; i++){
            Cid = Cid + random_id.nextInt(10);
        }
        File file = new File("container.txt");

        if (selection == 0){
            Container new_container = new Container("DS" + Cid, name, weight);
            new_container.fuel_consumption_per_km_on_ship = 3.5;
            new_container.fuel_consumption_per_km_on_truck = 4.6;
            inputContainerIntoFile(file, new_container);
        }
        if (selection == 1){
            Container new_container = new Container("OT" + Cid, name, weight);
            new_container.fuel_consumption_per_km_on_ship = 2.8;
            new_container.fuel_consumption_per_km_on_truck = 3.2;
            inputContainerIntoFile(file, new_container);
        }
        if (selection == 2){
            Container new_container = new Container("DS" + Cid, name, weight);
            new_container.fuel_consumption_per_km_on_ship = 2.7;
            new_container.fuel_consumption_per_km_on_truck = 3.2;
            inputContainerIntoFile(file, new_container);
        }
        if (selection == 3){
            Container new_container = new Container("DS" + Cid, name, weight);
            new_container.fuel_consumption_per_km_on_ship = 4.5;
            new_container.fuel_consumption_per_km_on_truck = 5.4;
            inputContainerIntoFile(file, new_container);
        }
        if (selection == 4){
            Container new_container = new Container("DS" + Cid, name, weight);
            new_container.fuel_consumption_per_km_on_ship = 4.8;
            new_container.fuel_consumption_per_km_on_truck = 5.3;
            inputContainerIntoFile(file, new_container);
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

    public static ArrayList<Container> getContainer(){
        System.out.println("reading");
        ArrayList<Container> container_list = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("container.txt"));
            while (true){
                try {
                    Container container = (Container) ois.readObject();
                    container_list.add(container);
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
        return container_list;
    }

    public static Container queryContainerbyID(String Cid){
        for (Container container: getContainer() ){
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

