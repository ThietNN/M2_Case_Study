package model.console;

import model.Console;

public class Xbox extends Console {
    private String type;

    public Xbox() {
    }

    public Xbox(int id, String name, int quantity, double price, boolean condition, String type) {
        super(id, name, quantity, price, condition);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Override
    public String toString(){
        return "ID: " + getId() + ", name: " + getName() + ", new = " + isCondition() + ", price = " + getPrice() + ", type: " + type + ", quantity: " + getQuantity();
    }
}
