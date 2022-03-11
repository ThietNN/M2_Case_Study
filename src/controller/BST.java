package controller;

import model.Product;

public interface BST {
    void showMenu();
    void getInfo();
    double getPrinciple();
    void setPrinciple(int principle);
    void addProduct(Product product);
    Product getProductByID(int id);
    void sell(int id, int quantity);
    void buy(int id, int quantity);
    void trade(int idTake, int quantityTake, int idGive, int quantityGive);
    Product createNewProduct();
    boolean checkExistence(Product product);
    void checkDuplicate(Product product);
}
