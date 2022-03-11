package controller;

import model.Product;

public interface BST {

    void getInfo();

    double getPrinciple();
    void setPrinciple();
    void setPrinciple(int principle);

    Product search();
    void addProduct(Product product);

    void sell();
    void buy();
    void trade();
    Product createNewProduct();

}
