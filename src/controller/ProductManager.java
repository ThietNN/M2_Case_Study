package controller;

import model.Console;
import model.console.NintendoSwitch;
import model.console.PlayStation;
import model.console.Xbox;
import storage.ProductFile;

import java.util.ArrayList;


public class ProductManager implements BST{
    private double principle;
    public ProductFile productFile = new ProductFile();
    public ArrayList<Console> consoleList = productFile.readFile();

    public static void showMenu() {
        System.out.println("Menu: ");
        System.out.println("1. Show Menu");
        System.out.println("2. Show all product ");
        System.out.println("3. Show principle");
        System.out.println("4: Set principle");
        System.out.println("5. Add product ");
        System.out.println("6. Search product");
        System.out.println("7. Sell a product ");
        System.out.println("8. Buy a product ");
        System.out.println("9. Trade a product ");
        System.out.println("10. Sort product ");
        System.out.println("0. Exit");
    }

    public  void getInfo() {
        consoleList = productFile.readFile();
        if (!consoleList.isEmpty()) {
            for (Console console : consoleList) {
                System.out.println(console);
            }
        }
        else
            System.out.println("0 product found! ");
    }

    public double getPrinciple() {
        return principle;
    }

    public void setPrinciple() {
        CheckValidate checkValidate = new CheckValidate();
        principle = checkValidate.checkPrinciple();
    }
    public void setPrinciple(int principle){
        this.principle = principle;
    }

    public void addProduct(Console console) {
        consoleList = productFile.readFile();
        if (console != null) {
            consoleList.add(console);
            ProductFile.writeFile(consoleList);
        }
    }

    public int getPositionByID(int id){
        consoleList = productFile.readFile();
        int result = -1;
        for (int i = 0; i < consoleList.size(); i++){
            if (consoleList.get(i).getId() == id){
                result = i;
                break;
            }
        }
        return result;
    }

    public Console getProductByID(int id) {
        consoleList = productFile.readFile();
        for (Console console : consoleList) {
            if (console.getId() == id) {
                return console;
            }
        }
        return null;
    }

    public Console search(){
        CheckValidate checkValidate = new CheckValidate();
        int searchID = checkValidate.checkID();
        Console searchConsole = getProductByID(searchID);
        if (searchConsole == null){
            System.err.println("Product with ID = " + searchID + " can not be found");
        }
        return searchConsole;
    }

    public double getBuyPrice(Console console, int quantity){
        double price = console.getBuyPrice();
        return price * quantity;
    }
    public double getSellPrice(Console console, int quantity){
        double price = console.getSellPrice();
        return price * quantity;
    }

    public boolean sellable(Console console, int quantity){
        return console.getQuantity() >= quantity;
    }

    public void sellProduct(Console console, int quantity){
        double sellPrice = getSellPrice(console, quantity);
        principle = principle + sellPrice;
        int newQuantity = console.getQuantity() - quantity;
        console.setQuantity(newQuantity);
    }

    public void sell() {
        CheckValidate checkValidate = new CheckValidate();
        int id = checkValidate.checkID();
        Console console = getProductByID(id);
        if (console != null) {
            int quantity = checkValidate.checkQuantity();
            if (sellable(console,quantity)) {
                sellProduct(console,quantity);
                System.out.println("Transaction completed");
            } else
                System.err.println("Not much left to sell");
        }else
            System.err.println("Product not found");
        ProductFile.writeFile(consoleList);
    }

    public boolean buyable(Console console, int quantity){
        double buyPrice = getBuyPrice(console, quantity);
        return principle >= buyPrice;
    }

    public void buyNewProduct(Console console, int quantity){
        double buyPrice = getBuyPrice(console, quantity);
        principle = principle - buyPrice;
        console.setQuantity(quantity);
    }
    public void buyExistProduct(Console console, int quantity){
        double buyPrice = getBuyPrice(console,quantity);
        principle = principle - buyPrice;
        quantity = console.getQuantity() + quantity;
        console.setQuantity(quantity);
    }

    public void buyProduct(boolean exist, Console console, int quantity){
        if (exist)
            buyExistProduct(console,quantity);
        else
            buyNewProduct(console,quantity);
    }

    public void buy() {
        consoleList = productFile.readFile();
        CheckValidate checkValidate = new CheckValidate();
        boolean exist;
        int id = checkValidate.checkID();
        Console console = getProductByID(id);
        int quantity = checkValidate.checkQuantity();
        if (console != null){
            exist = true;
            System.out.println("Product with this ID is already exist. Proceed to add quantity. ");
            if (!buyable(console, quantity)) {
                System.err.println("Not enough fund to buy");
                return;
            }
        }
        else {
            exist = false;
            console = checkValidate.checkProductType();
            console.setId(id);
            setPrice(console);
            if (buyable(console, quantity)) {
                setRest(console);
                addProduct(console);
            }else {
                System.err.println("Not enough fund to buy");
                return;
            }
        }
        buyProduct(exist, console,quantity);
        System.out.println("Transaction completed");
        ProductFile.writeFile(consoleList);
    }
    public boolean principleCheck(Console buyConsole, int buyQuantity, Console sellConsole, int sellQuantity){
        double buyPrice = getBuyPrice(buyConsole, buyQuantity);
        double sellPrice = getSellPrice(sellConsole, sellQuantity);
        double difference = buyPrice - sellPrice;
        double principleCheck = principle - difference;
        return principleCheck < 0;
    }
    public void tradeProduct(Console sellConsole, int sellQuantity, boolean buyProductExist, Console buyConsole, int buyQuantity){
        double sellPrice = getSellPrice(sellConsole, sellQuantity);
        principle = principle + sellPrice;
        int newSellQuantity = sellConsole.getQuantity() - sellQuantity;
        sellConsole.setQuantity(newSellQuantity);

        if (buyProductExist)
            buyExistProduct(buyConsole,buyQuantity);
        else
            buyNewProduct(buyConsole,buyQuantity);
    }
    public void trade(){
        consoleList = productFile.readFile();
        CheckValidate checkValidate = new CheckValidate();
        boolean buyProductExist;
        System.out.println("Product give: ");
        int sellID = checkValidate.checkID();
        int replaceSellID = getPositionByID(sellID);
        Console sellConsole = getProductByID(sellID);
        if (sellConsole == null){
            System.err.println("Product not found");
            return;
        }
        int sellQuantity = checkValidate.checkQuantity();
        if (!sellable(sellConsole,sellQuantity)){
            System.err.println("Not enough product to trade");
            return;
        }

        System.out.println("Product take: ");
        int buyID = checkValidate.checkID();
        Console buyConsole;
        if (sellID == buyID){
            buyConsole = sellConsole;
        }else
            buyConsole = getProductByID(buyID);
        if (buyConsole == null){
            buyConsole = checkValidate.checkProductType();
            buyConsole.setId(buyID);
            setPrice(buyConsole);
            buyProductExist = false;
        }else{
            buyProductExist = true;
        }
        int buyQuantity = checkValidate.checkQuantity();
        boolean principleCheck = principleCheck(buyConsole,buyQuantity, sellConsole,sellQuantity);
        if (principleCheck) {
            System.err.println("Not enough fund to trade. ");
        }

        if (!buyProductExist){
            setRest(buyConsole);
            addProduct(buyConsole);
        }
        tradeProduct(sellConsole,sellQuantity,buyProductExist, buyConsole,buyQuantity);
        consoleList.set(replaceSellID, sellConsole);
        System.out.println("Transaction completed");
        ProductFile.writeFile(consoleList);
    }


    public void setQuantity(Console console){
        CheckValidate checkValidate = new CheckValidate();
        int quantity = checkValidate.checkQuantity();
        console.setQuantity(quantity);
    }

    public void setPrice(Console console){
        CheckValidate checkValidate = new CheckValidate();
        double price = checkValidate.checkPrice();
        console.setPrice(price);
    }

    public void setRest(Console console){
        CheckValidate checkValidate = new CheckValidate();
        String name = checkValidate.checkName();
        console.setName(name);
        boolean condition = checkValidate.checkCondition();
        console.setCondition(condition);
        if (console instanceof PlayStation) {
            int generation = checkValidate.checkGeneration();
            ((PlayStation) console).setGeneration(generation);
            String edition = checkValidate.checkEdition();
            ((PlayStation) console).setEdition(edition);
        } else if (console instanceof Xbox) {
            String type = checkValidate.checkType();
            ((Xbox) console).setType(type);
        } else if (console instanceof NintendoSwitch) {
            boolean lite = checkValidate.checkLite();
            ((NintendoSwitch) console).setLite(lite);
        }
    }

    public Console createNewProduct() {
        CheckValidate checkValidate = new CheckValidate();
        Console console = checkValidate.checkProductType();
        int id = checkValidate.checkID();
        console.setId(id);
        if (checkExistence(console)){
            System.err.println("Product is already exist!");
            return null;
        }
        else {
            setQuantity(console);
            setPrice(console);
            setRest(console);
            System.out.println("New product created successfully");
        }
        return console;
    }

    public boolean checkExistence(Console console){
        consoleList = productFile.readFile();
        boolean exist = false;
        for (Console eachConsole : consoleList) {
            if ((eachConsole.getId()) == (console.getId())) {
                exist = true;
                break;
            }
        }
        return exist;
    }


}
