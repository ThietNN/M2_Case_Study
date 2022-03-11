package model.console;

import model.Product;

public class NintendoSwitch extends Product {
    private boolean lite;

    public NintendoSwitch() {
    }

    public NintendoSwitch(int id, String name, int quantity, double price, boolean condition, boolean lite) {
        super(id, name, quantity, price, condition);
        this.lite = lite;
    }

    public boolean isLite() {
        return lite;
    }

    public void setLite(boolean lite) {
        this.lite = lite;
    }
    @Override
    public String toString(){
        return "ID: " + getId() + ", name: " + getName() + ", new = " + isCondition() + ", price = " + getPrice() + ", lite =  " + lite + ", quantity: " + getQuantity();
    }
}
