import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class FileIOUtil {

    public static ArrayList<Object> ReadObjectFromFile(String file_name) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(file_name);
        ArrayList<Object> list = new ArrayList<Object>();
        list = mapper.readValue(file, new TypeReference<ArrayList<Object>>() {
        });
        return list;
    }
    public static ArrayList<Port> ReadPortFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("port.json");
        ArrayList<Port> list = new ArrayList<Port>();
        list = mapper.readValue(file, new TypeReference<ArrayList<Port>>() {});
        return list;
    }
    public static ArrayList<Vehicle> ReadVehicleFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("vehicle.json");
        ArrayList<Vehicle> list = new ArrayList<Vehicle>();
        list = mapper.readValue(file, new TypeReference<ArrayList<Vehicle>>() {});
        return list;
    }
    public static ArrayList<Container> ReadContainerFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("container.json");
        ArrayList<Container> list = new ArrayList<Container>();
        list = mapper.readValue(file, new TypeReference<ArrayList<Container>>() {});
        return list;
    }
    public static ArrayList<Trip> ReadTripFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("trip.json");
        ArrayList<Trip> list = new ArrayList<Trip>();
        list = mapper.readValue(file, new TypeReference<ArrayList<Trip>>() {});
        return list;
    }
    public static ArrayList<Port_Manager> ReadManagerFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("port_manager.json");
        ArrayList<Port_Manager> list = new ArrayList<Port_Manager>();
        list = mapper.readValue(file, new TypeReference<ArrayList<Port_Manager>>() {});
        return list;
    }




    public static void InputObjectIntoFile(Object object, String file_name) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(file_name);
        if (file.exists() ){
            ArrayList<Object> list_2 = FileIOUtil.ReadObjectFromFile(file_name);
            list_2.add(object);
            mapper.writeValue(file, list_2);
        }
        else {
            ArrayList<Object> list = new ArrayList<Object>();
            list.add(object);
            System.out.println(list);
            mapper.writeValue(file, list);
        }
    }



    public static void updateObjectFromFile(String file_name, Object object) throws IOException {
        File file = new File(file_name);
        if (object instanceof Port){
            ArrayList<Port> list = FileIOUtil.ReadPortFromFile();

            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Port) object)){
                    list.set(i, (Port) object);;
                    break;
                }
            }

            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (object instanceof Vehicle){
            ArrayList<Vehicle> list = FileIOUtil.ReadVehicleFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Vehicle) object)){
                    list.set(i, (Vehicle) object);
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (object instanceof Container){
            ArrayList<Container> list = FileIOUtil.ReadContainerFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Container) object)){
                    list.set(i, (Container) object);
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (object instanceof Trip){
            ArrayList<Trip> list = FileIOUtil.ReadTripFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Trip) object)){
                    list.set(i, (Trip) object);
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else {
            ArrayList<Port_Manager> list = FileIOUtil.ReadManagerFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Port_Manager) object)){
                    list.set(i, (Port_Manager) object);
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            FileIOUtil.InputObjectIntoFile(list, file_name);
        }

        }



    public static void removeObjectFromFile(Object object, String file_name) throws IOException {
        File file = new File(file_name);
        if (object instanceof Port){
            ArrayList<Port> list = FileIOUtil.ReadPortFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Port) object)){
                    list.remove(list.get(i));
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (object instanceof Vehicle){
            ArrayList<Vehicle> list = FileIOUtil.ReadVehicleFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Vehicle) object)){
                    list.remove(list.get(i));
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (object instanceof Container){
            ArrayList<Container> list = FileIOUtil.ReadContainerFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Container) object)){
                    list.remove(list.get(i));
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else if (object instanceof Trip){
            ArrayList<Trip> list = FileIOUtil.ReadTripFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Trip) object)){
                    list.remove(list.get(i));
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
        else {
            ArrayList<Port_Manager> list = FileIOUtil.ReadManagerFromFile();
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).equals((Port_Manager) object)){
                    list.remove(list.get(i));
                    break;
                }
            }
            file.delete();
            file.createNewFile();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(file, list);
        }
    }
}






