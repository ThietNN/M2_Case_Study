package controller;

import model.Product;
import storage.ProductFile;

import java.util.ArrayList;

public class ProductManagerProxy implements BST{
    private final ProductManager productManager;
    private final double principle = 100000000;
    public static ProductFile productFile = new ProductFile();
    public ArrayList<Product> productList = productFile.readFile();

    public ProductManagerProxy() {
        this.productManager = new ProductManager();
    }

    @Override
    public void showMenu() {

    }

    @Override
    public void getInfo() {

    }

    @Override
    public double getPrinciple() {
        return 0;
    }

    @Override
    public void setPrinciple(int principle) {

    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public Product getProductByID(int id) {
        return null;
    }

    @Override
    public void sell(int id, int quantity) {

    }

    @Override
    public void buy(int id, int quantity) {

    }

    @Override
    public void trade(int idTake, int quantityTake, int idGive, int quantityGive) {

    }

    @Override
    public Product createNewProduct() {
        return null;
    }

    @Override
    public boolean checkExistence(Product product) {
        return false;
    }

    @Override
    public void checkDuplicate(Product product) {

    }

}
