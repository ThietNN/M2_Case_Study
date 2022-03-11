package controller;

import model.Product;

import java.util.Comparator;

public class IDComparator implements Comparator<Product> {
    @Override
    public int compare (Product product1, Product product2){
        return Integer.compare(product1.getId(), product2.getId());
    }
}
