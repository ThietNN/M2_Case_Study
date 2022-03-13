package storage;

import model.Console;

import java.io.*;
import java.util.ArrayList;

public class ProductFile {
    public ArrayList<Console> readFile(){
        File file = new File("product.thiet");
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object result = ois.readObject();
            ArrayList<Console> consoleList = (ArrayList<Console>) result;
            ois.close();
            fis.close();
            return consoleList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void writeFile(ArrayList<Console> consoleList){
        try{
            FileOutputStream fos = new FileOutputStream("product.thiet");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(consoleList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }









}
