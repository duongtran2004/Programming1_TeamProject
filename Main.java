import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("admin.txt"));
            System_Admin admin = (System_Admin) ois.readObject();
        }   catch (FileNotFoundException e){
            e.printStackTrace();
        }   catch (IOException e){
            e.printStackTrace();

        }   catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}

