package storage;

import model.Product;

import java.io.*;
import java.util.ArrayList;

public class ProductFile {
    public ArrayList<Product> readFile(){
        File file = new File("product.thiet");
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object result = ois.readObject();
            ArrayList<Product> productList = (ArrayList<Product>) result;
            ois.close();
            fis.close();
            return productList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void writeFile(ArrayList<Product> productList){
        try{
            FileOutputStream fos = new FileOutputStream("product.thiet");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(productList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }









}
