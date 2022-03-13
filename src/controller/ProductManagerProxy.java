package controller;

import model.Console;
import storage.ProductFile;

import java.util.ArrayList;

public class ProductManagerProxy implements BST{
    private final ProductManager productManager;
    private double principle;
    public static ProductFile productFile = new ProductFile();
    public ArrayList<Console> consoleList = productFile.readFile();

    public ProductManagerProxy() {
        this.productManager = new ProductManager();
    }

    @Override
    public void getInfo() {
        if (consoleList.isEmpty()){
            throw new RuntimeException("No product found in file");
        }
        productManager.getInfo();
    }

    @Override
    public double getPrinciple() {
        if (principle < 0){
            throw new RuntimeException("Principle is a negative number atm. Please check asap");
        }
        return productManager.getPrinciple();
    }

    @Override
    public void setPrinciple() {
        productManager.setPrinciple();
        if (principle > Double.MAX_VALUE){
            principle = 0;
            throw new RuntimeException("Principle exceed max value. Principle set to 0");
        }

    }
    public void setPrinciple(int principle){
        this.principle = principle;
    }

    @Override
    public Console search() {
        return productManager.search();
    }

    @Override
    public void addProduct(Console console) {
        productManager.addProduct(console);
    }



    @Override
    public void sell() {
        productManager.sell();
    }

    @Override
    public void buy() {
        productManager.buy();
    }

    @Override
    public void trade() {
        productManager.trade();
    }

    @Override
    public Console createNewProduct() {
        return productManager.createNewProduct();
    }




}
