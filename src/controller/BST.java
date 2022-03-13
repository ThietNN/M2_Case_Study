package controller;

import model.Console;

public interface BST {

    void getInfo();

    double getPrinciple();
    void setPrinciple();
    void setPrinciple(int principle);

    Console search();
    void addProduct(Console console);

    void sell();
    void buy();
    void trade();
    Console createNewProduct();

}
