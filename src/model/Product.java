package model;

import java.io.Serializable;

public abstract class Product implements Comparable<Product>, Serializable {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private boolean condition;


    public Product() {
    }

    public Product(int id, String name, int quantity, double price, boolean condition) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSellPrice(){
        if (condition)
            return price * 120 / 100;
        else
            return price * 90 / 100;
    }

    public double getBuyPrice(){
        if (condition)
            return price * 70 / 100;
        else
            return price * 50 / 100;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    @Override
    public int compareTo(Product product){
        return Integer.compare(getId(),product.getId());
    }

}
