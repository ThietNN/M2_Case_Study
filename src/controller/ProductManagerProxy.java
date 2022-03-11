package controller;

import model.Product;
import storage.ProductFile;

import java.util.ArrayList;

public class ProductManagerProxy implements BST{
    private final ProductManager productManager;
    private double principle;
    public static ProductFile productFile = new ProductFile();
    public ArrayList<Product> productList = productFile.readFile();

    public ProductManagerProxy() {
        this.productManager = new ProductManager();
    }

    @Override
    public void getInfo() {
        if (productList.isEmpty()){
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

    }
    public void setPrinciple(int principle){
        this.principle = principle;
    }

    @Override
    public Product search() {
        return productManager.search();
    }

    @Override
    public void addProduct(Product product) {
        productManager.addProduct(product);
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
    public Product createNewProduct() {
        return productManager.createNewProduct();
    }




}
