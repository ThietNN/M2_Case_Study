//package model.console;
//
//import model.Product;
//
//import java.io.Serializable;
//
//public abstract class Console extends Product implements Serializable {
//    private String color;
//
//    public Console() {
//    }
//
//    public Console(int id, String name, int quantity, double price, boolean condition, String color) {
//        super(id, name, quantity, price, condition);
//        this.color = color;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//
//    @Override
//    public String toString(){
//        return "ID: " + getId() + ", name: " + getName() + ", color: " + color + ",new = " + isCondition() + ", price = " + getPrice() + ", quantity: " + getQuantity();
//    }
//
//}
