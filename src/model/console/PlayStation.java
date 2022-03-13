package model.console;

import model.Console;

public class PlayStation extends Console {
    private int generation;
    private String edition;

    public PlayStation() {
    }

    public PlayStation(int id, String name, int quantity, double price, boolean condition, int generation, String edition) {
        super(id, name, quantity, price, condition);
        this.generation = generation;
        this.edition = edition;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public String toString(){
        return "ID: " + getId() + ", name: " + getName()  + ", new = " + isCondition() + ", price = " + getPrice() + ", generation " + generation + ", quantity: " + getQuantity() + ", " + edition + " edition. ";
    }
}
