import Users.Port_Manager;
import Users.System_Admin;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Adminstration {

    public static void program_Run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Who do you want to login as?\na: Port Manger\nb: System Admin");
            String selection = scanner.nextLine();
            if (selection.equalsIgnoreCase("a")){
                Port_Manager port_manager = Port_Manager.login();
                Portmanager_run(port_manager, false);

            }
            else if (selection.equalsIgnoreCase("b")){
                System_Admin.login();
                Admin_run(false);
            }
            else{
                System.out.println("Option does not exist. Please try again");
            }
        }


    }
    public static boolean Admin_run(boolean _exit) throws IOException {
        if (_exit) {
            System.out.println("Quiting application");
            return true;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do?\na: add Manager\nb: add Port.Port\nc: add Vehicle_Classes.Vehicle\nd: add Container_Class.Container\ne: view manager\nf: view ports\ng: view vehicles\nh: view containers\ni: update manager info\nj: update port info\nk: update vehicle info\nl: update container info\nm: remove manager\nn: remove port\no: remove vehicle\np: remove container\ns: view trip\nt: add System Admin\nu: view System Admin\nv: update System Admin\nw: remove System Admin\nq: quit");
        String selection = scanner.nextLine();
        if (selection.equalsIgnoreCase("a")) {
            System_Admin.addManager();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        } else if (selection.equalsIgnoreCase("b")) {
            System_Admin.addPort();
            System.out.println("Press 1 to return to the menu and 0 to quit the application");
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        } else if (selection.equalsIgnoreCase("c")) {
            System_Admin.addVehicle();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        } else if (selection.equalsIgnoreCase("d")) {
            System_Admin.addContainer();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }
        } else if (selection.equalsIgnoreCase("e")) {
            System_Admin.viewManager();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }
        } else if (selection.equalsIgnoreCase("f")) {
            System_Admin.viewPort();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }
        } else if (selection.equalsIgnoreCase("g")) {
            System_Admin.viewVehicle();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        } else if (selection.equalsIgnoreCase("h")) {
            System_Admin.viewContainers();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        } else if (selection.equalsIgnoreCase("i")) {
            System_Admin.updateManager();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }
        } else if (selection.equalsIgnoreCase("j")) {
            System_Admin.updatePort();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        } else if (selection.equalsIgnoreCase("k")) {
            System_Admin.updateVehicle();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        } else if (selection.equalsIgnoreCase("l")) {
            System_Admin.updateContainer();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        } else if (selection.equalsIgnoreCase("m")) {
            System_Admin.removeManager();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        } else if (selection.equalsIgnoreCase("n")) {
            System_Admin.removePort();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        } else if (selection.equalsIgnoreCase("o")) {
            System_Admin.removeVehicle();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        }

        else if (selection.equalsIgnoreCase("p")) {
            System_Admin.removeContainer();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        }
        else if(selection.equalsIgnoreCase("s")){
            System_Admin.viewAllTrips();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        }
        else if(selection.equalsIgnoreCase("w")){
            System_Admin.removeSystem_Admin();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        }
        else if(selection.equalsIgnoreCase("t")){
            System_Admin.addSystemAdmin();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        }
        else if(selection.equalsIgnoreCase("u")){
            System_Admin.viewAdmin();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        }
        else if(selection.equalsIgnoreCase("v")){
            System_Admin.updateAdmin();
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        }
        else if (selection.equalsIgnoreCase("q")){
            return Admin_run(true);
        }
        else {
            System.out.println("Option does not exist.");
            while (true) {
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")) {
                    return Admin_run(false);
                } else if (_quit.equalsIgnoreCase("q")) {
                    return Admin_run(true);
                } else {
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        }
    }






    public static boolean Portmanager_run(Port_Manager manager, boolean _exit) throws IOException {
        if (_exit){
            System.out.println("Quiting application");
            return true;
        }
        Scanner scanner = new Scanner(System.in);


        System.out.println("What would you like to do?\na: Commence Container_Class.Open_Top.Trip.Trip\nb: Complete Container_Class.Open_Top.Trip.Trip\nc: Loading Containers on Vehicle_Classes.Vehicle\nd: Loading Containers off Vehicle_Classes.Vehicle\ne: Incoming Trips Query\nf: Outgoing Trips Query\ng: Registering Trips\nt: update trip\nu: remove trip\nq: quit" );
        String selection = scanner.nextLine();
        if (selection.equalsIgnoreCase("a")){
            manager.commenceTrip();
            while (true){
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")){
                    return Portmanager_run(manager, false);
                }
                else if (_quit.equalsIgnoreCase("q")) {
                    return Portmanager_run(manager, true);
                }
                else{
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        }
        else if (selection.equalsIgnoreCase("b")){
            manager.completeTrip();
            System.out.println("Press 1 to return to the menu and 0 to quit the application");
            while (true){
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")){
                    return Portmanager_run(manager, false);
                }
                else if (_quit.equalsIgnoreCase("q")) {
                    return Portmanager_run(manager, true);
                }
                else{
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        }
        else if (selection.equalsIgnoreCase("c")){
            manager.loadingContainersonVehicle();
            while (true){
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")){
                    return Portmanager_run(manager, false);
                }
                else if (_quit.equalsIgnoreCase("q")) {
                    return Portmanager_run(manager, true);
                }
                else{
                    System.out.println("Option does not exit. Please choose again");
                }
            }
        }
        else if (selection.equalsIgnoreCase("d")){
            manager.loadingContaineroffVehicle();
            System.out.println("Press 1 to return to the menu and 0 to quit the application");
            while (true){
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")){
                    return Portmanager_run(manager, false);
                }
                else if (_quit.equalsIgnoreCase("q")) {
                    return Portmanager_run(manager, true);
                }
                else{
                    System.out.println("Option does not exit. Please choose again");
                }
            }
        }
        else if (selection.equalsIgnoreCase("e")){
            manager.incomingTrip_query();
            while (true){
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")){
                    return Portmanager_run(manager, false);
                }
                else if (_quit.equalsIgnoreCase("q")) {
                    return Portmanager_run(manager, true);
                }
                else{
                    System.out.println("Option does not exit. Please choose again");
                }
            }
        }
        else if (selection.equalsIgnoreCase("f")) {
            manager.outgoingTrip_query();
            while (true){
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")){
                    return Portmanager_run(manager, false);
                }
                else if (_quit.equalsIgnoreCase("q")) {
                    return Portmanager_run(manager, true);
                }
                else{
                    System.out.println("Option does not exit. Please choose again");
                }
            }
        }
        else if (selection.equalsIgnoreCase("g")) {
            manager.RegisteringTrip();
            while (true){
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")){
                    return Portmanager_run(manager, false);
                }
                else if (_quit.equalsIgnoreCase("q")) {
                    return Portmanager_run(manager, true);
                }
                else{
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        } else if (selection.equalsIgnoreCase("t")) {
            manager.updateTrip();
            while (true){
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")){
                    return Portmanager_run(manager, false);
                }
                else if (_quit.equalsIgnoreCase("q")) {
                    return Portmanager_run(manager, true);
                }
                else{
                    System.out.println("Option does not exit. Please choose again");
                }
            }

        } else if (selection.equalsIgnoreCase("u")) {
            manager.removeTrip();
            while (true){
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")){
                    return Portmanager_run(manager, false);
                }
                else if (_quit.equalsIgnoreCase("q")) {
                    return Portmanager_run(manager, true);
                }
                else{
                    System.out.println("Option does not exit. Please choose again");
                }
            }



        } else if (selection.equalsIgnoreCase("q")) {
            return Portmanager_run(manager, true);

        }
        else{
            while (true){
                System.out.println("Press r to return to the menu and q to quit the application");
                String _quit = scanner.nextLine();
                if (_quit.equalsIgnoreCase("r")){
                    return Portmanager_run(manager, false);
                }
                else if (_quit.equalsIgnoreCase("q")) {
                    return Portmanager_run(manager, true);
                }
                else{
                    System.out.println("Option does not exit. Please choose again");
                }
            }
        }

    }
}
