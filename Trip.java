import java.lang.reflect.Array;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Trip {
    private Vehicle vehicle;
    private String Tid;
    private String status = "Incomplete";
    private Port D_port;
    private Port A_port;
    private Date date_of_departure;
    private Date date_of_arrival;
    private ArrayList<Container> containers_to_be_delivered = new ArrayList<Container>();
    private static  ArrayList<Trip> trip_list = new ArrayList<Trip>();


    public Trip(Vehicle vehicle, String Tid, Port D_port, Port A_port, Date date_of_departure, Date date_of_arrival){
        this.vehicle = vehicle;
        this.Tid = Tid;
        this.D_port = D_port;
        this.A_port = A_port;
        this.date_of_arrival = date_of_arrival;
        this.date_of_departure = date_of_departure;
    }

    public String getTid(){
        return this.Tid;
    }

    public Vehicle getVehicle(){
        return  this.vehicle;
    }
    public String getStatus(){
        return  this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public Port getD_port(){
        return this.D_port;
    }
    public Port getA_port(){
        return this.A_port;
    }
    public Date getDate_of_departure(){
        return  date_of_departure;
    }
    public Date getDate_of_arrival(){
        return date_of_arrival;
    }

    public static ArrayList<Trip> getTrip_list(){
        return trip_list;
    }

    public static ArrayList<Vehicle> checking_vehicle_eligibility(Port port, Port port_of_arrival, Date date_of_arrival, Date date_of_departure, ArrayList<Container>container_list){
        //this method returns a list of vehicle available to be deployed on the trip. Vehicle eligibility is evaluated based on the arrival base's landing ability (False means only accessible to ships) whether a vehicle is capable of finishing a trip given a list of containers and trip length (by calling the successAssement method from the Vehicle Class), whether it is on another trip on the given trip duration.

        ArrayList<Vehicle> deployable_vehicles = new ArrayList<Vehicle>();
        ArrayList<Vehicle> unavailable_vehicles = new ArrayList<Vehicle>();
        double trip_length = port.distanceCalc(port_of_arrival);

        for (Trip trip: Trip.trip_list){
            if ((trip.date_of_arrival.after(date_of_departure) && trip.date_of_arrival.before(date_of_arrival)) || ((trip.date_of_departure.after(date_of_departure) && trip.date_of_departure.before(date_of_arrival)))){
                unavailable_vehicles.add(trip.vehicle);
            }
        }
        for (Vehicle vehicle: Vehicle.getVehicles()){
            if (port_of_arrival.getLandingAbility()){
                if (!unavailable_vehicles.contains(vehicle) && vehicle.successAssessment(container_list, trip_length)){
                    deployable_vehicles.add(vehicle);
                }
            }
            else {
                if (!unavailable_vehicles.contains(vehicle) && vehicle.successAssessment(container_list, trip_length) && vehicle.getName().startsWith("SH")){
                    deployable_vehicles.add(vehicle);
                }
            }

        }
        return deployable_vehicles;
    }

    //this method will register a trip into the system. It prompts the user to input date and port of departure and arrival, it will then have them choose from a list of available vehicles returned by the checking_vehicle_eligibility function. The id of the trip will be randomly generated
    public static void registeringTrip(Port port){
        Scanner scanner = new Scanner(System.in);
        // Enter date of departure
        System.out.println("Please input the year of the trip");
        int year = scanner.nextInt();
        System.out.println("Please input the month of the trip");
        int month = scanner.nextInt();
        System.out.println("Please input the date of the trip");
        int date = scanner.nextInt();
        Date date_of_departure = new Date(year, month, date);

        // Enter date of arrival
        System.out.println("Please input the year of the trip");
        year = scanner.nextInt();
        System.out.println("Please input the month of the trip");
        month = scanner.nextInt();
        System.out.println("Please input the date of the trip");
        date = scanner.nextInt();
        Date date_of_arrival = new Date(year, month, date);

        //Choose the destination port
        System.out.println("Please enter the Id of the destination port");
        String Pid = scanner.nextLine();
        Port port_of_arrival = Port.queryPortbyID(Pid);


        //Choose the containers to be delivered
        ArrayList<Container> selected_container_list = new ArrayList<Container>();


        while (true){
            System.out.println("Please select the container you would like to load");
            String Cid = scanner.nextLine();
            Container container = Container.queryContainerbyID(Cid);
            selected_container_list.add(container);
            System.out.println("Would you like to add more containers? (Y-N)");
            String rep = scanner.nextLine();
            if (rep.equals("N")){
                break;
            }
        }

        //Check vehicle availability
        ArrayList<Vehicle> deployable_vehicles = checking_vehicle_eligibility(port, port_of_arrival, date_of_arrival, date_of_departure, selected_container_list);

        //Choose Vehicle
        String prompt = "Please select from the following vehicles ";
        for (int i =0; i <deployable_vehicles.size(); i++){
            prompt = prompt + i + ": " + deployable_vehicles.get(i).getName();
        }
        System.out.println(prompt);
        int selection = scanner.nextInt();
        Vehicle selected_vehicle = deployable_vehicles.get(selection);

        //Randomly generating trip id
        Random generating_id = new Random();
        String Tid = "t";
        for (int i =1; i<=5; i++){
            Tid = Tid + generating_id.nextInt(10);
        }
        Trip new_trip = new Trip(selected_vehicle, Tid, port, port_of_arrival, date_of_departure, date_of_arrival);
        trip_list.add(new_trip);
    }

    public static Trip queryByName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the Id of the trip");
        String Cid = scanner.nextLine();
        for (Trip trip: trip_list){
            if (trip.getTid().equals(Cid)){
                return trip;
            }
        }
        return null;
    }
    //This method allows Port Manager to view the outgoing trips after/before inputted dates
    public static void outgoingTrip_query(Port port){
        //Have the users input the interval they would like to query
        Date after = null;
        Date before = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to filter by date after? (Y-N)");
        if (scanner.nextLine().equals("Y")){
            System.out.println("Please enter the year");
            int year = scanner.nextInt();
            System.out.println("Please enter the month");
            int month = scanner.nextInt();
            System.out.println("Please enter the date");
            int date = scanner.nextInt();
            after = new Date(year,month,date);
        }

        System.out.println("Would you like to filter by date before? (Y-N)");
        if (scanner.nextLine().equals("Y")){
            System.out.println("Please enter the year");
            int year = scanner.nextInt();
            System.out.println("Please enter the month");
            int month = scanner.nextInt();
            System.out.println("Please enter the date");
            int date = scanner.nextInt();
            before = new Date(year,month,date);
        }

        ArrayList<Trip> trip_from_port = new ArrayList<Trip>();

        //querying
        //if the user does not enter both before and after filtering dates, the function will return all outgoing trips
        if (after == null && before == null){
            for (Trip trip: Trip.trip_list){
                if (trip.D_port == port){
                    trip_from_port.add(trip);
                }
            }
        }

        //if the user only enter the after filtering date, the function will return all the outgoing trip after that date
        else if (before == null && !(after==null)){
            for (Trip trip: Trip.trip_list){
                if (trip.date_of_departure.after(after) && trip.D_port == port){
                    trip_from_port.add(trip);
                }
            }
        }

        //if the user only enter the after filtering date, the function will return all the outgoing trip before that date
        else if (after == null && !(before == null)){
            for (Trip trip: Trip.trip_list){
                if (trip.date_of_departure.before(before) && trip.D_port == port){
                    trip_from_port.add(trip);
                }
            }
        }
        else {
            for (Trip trip: Trip.trip_list){
                if (trip.date_of_departure.before(before) && trip.date_of_departure.after(after) && trip.D_port == port){
                    trip_from_port.add(trip);
                }
            }
        }
        System.out.println(trip_from_port);
    }


    //This method allows Port Manager to see the incoming trip after/before inputted dates.
    public static void incomingTrip_query(Port port){
        //Have the users input the interval they would like to query
        Date after = null;
        Date before = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to filter by date after? (Y-N)");
        if (scanner.nextLine().equals("Y")){
            System.out.println("Please enter the year");
            int year = scanner.nextInt();
            System.out.println("Please enter the month");
            int month = scanner.nextInt();
            System.out.println("Please enter the date");
            int date = scanner.nextInt();
            after = new Date(year,month,date);
        }
        System.out.println("Would you like to filter by date before? (Y-N)");
        if (scanner.nextLine().equals("Y")){
            System.out.println("Please enter the year");
            int year = scanner.nextInt();
            System.out.println("Please enter the month");
            int month = scanner.nextInt();
            System.out.println("Please enter the date");
            int date = scanner.nextInt();
            before = new Date(year,month,date);
        }

        ArrayList<Trip> trip_from_port = new ArrayList<Trip>();

        //querying
        //if the user does not enter both before and after filtering dates, the function will return all incoming trips
        if (after == null && before == null){
            for (Trip trip: Trip.getTrip_list()){
                if (trip.getA_port() == port){
                    trip_from_port.add(trip);
                }
            }
        }
        //if the user only enter the after filtering date, the function will return all the outgoing trip after that date
        else if (before == null && !(after==null)){
            for (Trip trip: Trip.getTrip_list()){
                if (trip.getDate_of_arrival().after(after) && trip.getA_port() == port){
                    trip_from_port.add(trip);
                }
            }
        }

        //if the user only enter the after filtering date, the function will return all the outgoing trip before that date
        else if (after == null && !(before == null)){
            for (Trip trip: Trip.getTrip_list()){
                if (trip.getDate_of_arrival().before(before) && trip.getA_port() == port){
                    trip_from_port.add(trip);
                }
            }
        }
        else{
            for (Trip trip: Trip.trip_list){
                if (trip.date_of_arrival.before(before) && trip.date_of_arrival.after(after) && trip.D_port == port){
                    trip_from_port.add(trip);
                }
            }
        }
        System.out.println(trip_from_port);
    }
}
