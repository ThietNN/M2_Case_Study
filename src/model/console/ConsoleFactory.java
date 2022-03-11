package model.console;

import model.Product;


public class ConsoleFactory {
    public Product getConsole(String type){

        if (type.equals("ps")){
            return new PlayStation();
        }
        else if (type.equals("ns")){
            return new NintendoSwitch();
        }
        else if (type.equals("xbox")){
            return new Xbox();
        }
        else{
            throw new RuntimeException("Can't create this type of product");
        }
    }
}
