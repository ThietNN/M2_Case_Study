package controller;

import model.Product;
import model.console.NintendoSwitch;
import model.console.PlayStation;
import model.console.Xbox;
import storage.ProductFile;

import java.util.ArrayList;


public class ProductManager {
    private static double principle = 100000;
    public static ProductFile productFile = new ProductFile();
    public static ArrayList<Product> productList = productFile.readFile();

    public static void showMenu() {
        System.out.println("Menu: ");
        System.out.println("1. Show Menu");
        System.out.println("2. Show all product ");
        System.out.println("3. Show principle");
        System.out.println("4. Add product ");
        System.out.println("5. Search product");
        System.out.println("6. Sell a product ");
        System.out.println("7. Buy a product ");
        System.out.println("8. Trade a product ");
        System.out.println("9. Sort product ");
        System.out.println("10. Exit");
    }

    public static void getInfo() {
        productList = productFile.readFile();
        if (!productList.isEmpty()) {
            for (Product product : productList) {
                System.out.println(product);
            }
        }
        else
            System.out.println("0 product found! ");
    }

    public static double getPrinciple() {
        return principle;
    }

    public static void setPrinciple(int number) {
        principle = number;
    }

    public static void addProduct(Product product) {
        productList = productFile.readFile();
        if (product != null) {
            productList.add(product);
            ProductFile.writeFile(productList);
        }
    }

    public static Product getProductByID(int id) {
        productList = productFile.readFile();
        Product product = null;
        for (Product eachProduct : productList) {
            if (eachProduct.getId() == id) {
                product = eachProduct;
            }
        }
        return product;
    }

    public static Product search(){
        CheckValidate checkValidate = new CheckValidate();
        int searchID = checkValidate.checkID();
        Product searchProduct = getProductByID(searchID);
        if (searchProduct == null){
            System.err.println("Product with ID = " + searchID + " can not be found");
        }
        return searchProduct;
    }

    public static double getBuyPrice(Product product, int quantity){
        double price = product.getBuyPrice();
        return price * quantity;
    }
    public static double getSellPrice(Product product, int quantity){
        double price = product.getSellPrice();
        return price * quantity;
    }

    public static boolean sellable(Product product, int quantity){
        if (product.getQuantity() >= quantity)
            return true;
        else
            return false;
    }

    public static void sellProduct(Product product, int quantity){
        double sellPrice = getSellPrice(product, quantity);
        principle = principle + sellPrice;
        quantity = product.getQuantity() - quantity;
        product.setQuantity(quantity);
    }

    public static void sell() {
        productList = productFile.readFile();
        CheckValidate checkValidate = new CheckValidate();
        int id = checkValidate.checkID();
        Product product = getProductByID(id);
        if (product != null) {
            int quantity = checkValidate.checkQuantity();
            if (sellable(product,quantity)) {
                sellProduct(product,quantity);
                System.out.println("Transaction completed");
            } else
                System.err.println("Not much left to sell");
        }else
            System.err.println("Product not found");
        ProductFile.writeFile(productList);
    }

    public static boolean buyable(Product product, int quantity){
        double buyPrice = getBuyPrice(product, quantity);
        if (principle >= buyPrice)
            return true;
        else
            return false;
    }

    public static void buyProduct(Product product, int quantity){
        double buyPrice = getBuyPrice(product, quantity);
        principle = principle - buyPrice;
        product.setQuantity(quantity);
    }


    public static void buy() {
        productList = productFile.readFile();
        CheckValidate checkValidate = new CheckValidate();
        int id = checkValidate.checkID();
        Product product = getProductByID(id);
        int quantity = checkValidate.checkQuantity();
        if (product != null){
            System.out.println("Product with this ID is already exist. Proceed to add quantity. ");
            if (buyable(product, quantity)) {
                quantity = product.getQuantity() + quantity;
            }else {
                System.err.println("Not enough fund to buy");
                return;
            }
        }
        else {
            product = checkValidate.checkProductType();
            product.setId(id);
            setPrice(product);
            if (buyable(product, quantity)) {
                setRest(product);
                addProduct(product);
            }else {
                System.err.println("Not enough fund to buy");
                return;
            }
        }
        buyProduct(product,quantity);
        System.out.println("Transaction completed");
        ProductFile.writeFile(productList);
    }
// try singleton
    public static void trade(){
        CheckValidate checkValidate = new CheckValidate();
        boolean check = false;
        System.out.println("Product give: ");
        int sellID = checkValidate.checkID();
        Product sellProduct = getProductByID(sellID);
        if (sellProduct != null){
            int sellQuantity = checkValidate.checkQuantity();
            check = true;
        }else{
            System.err.println("Product not found");
            return;
        }
        System.out.println("Product take: ");
        int buyID = checkValidate.checkID();
        Product buyProduct = getProductByID(buyID);
        if (buyProduct == null){
            buyProduct = checkValidate.checkProductType();
            buyProduct.setId(buyID);
            setPrice(buyProduct);
        }
        int buyQuantity = checkValidate.checkQuantity();
        double difference = getBuyPrice(buyProduct,buyQuantity) +

    }

    public static Product setQuantity(Product product){
        CheckValidate checkValidate = new CheckValidate();
        int quantity = checkValidate.checkQuantity();
        product.setQuantity(quantity);
        return product;
    }

    public static Product setPrice(Product product){
        CheckValidate checkValidate = new CheckValidate();
        double price = checkValidate.checkPrice();
        product.setPrice(price);
        return product;
    }

    public static Product setRest(Product product){
        CheckValidate checkValidate = new CheckValidate();
        String name = checkValidate.checkName();
        product.setName(name);
        boolean condition = checkValidate.checkCondition();
        product.setCondition(condition);
        if (product instanceof PlayStation) {
            int generation = checkValidate.checkGeneration();
            ((PlayStation) product).setGeneration(generation);
            String edition = checkValidate.checkEdition();
            ((PlayStation) product).setEdition(edition);
        } else if (product instanceof Xbox) {
            String type = checkValidate.checkType();
            ((Xbox) product).setType(type);
        } else if (product instanceof NintendoSwitch) {
            boolean lite = checkValidate.checkLite();
            ((NintendoSwitch) product).setLite(lite);
        }
        return product;
    }

    public static Product createNewProduct() {
        CheckValidate checkValidate = new CheckValidate();
        Product product = checkValidate.checkProductType();
        int id = checkValidate.checkID();
        product.setId(id);
        if (checkExistence(product)){
            System.err.println("Product is already exist!");
            product = null;
            return null;
        }
        else {
            setQuantity(product);
            setPrice(product);
            setRest(product);
            System.out.println("New product created successfully");
        }
        return product;
    }

    public static boolean checkExistence(Product product){
        productList = productFile.readFile();
        boolean exist = false;
        for (Product eachProduct : productList) {
            if ((eachProduct.getId()) == (product.getId())) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public static void addDuplicate(Product product) {
        productList = productFile.readFile();
        for (Product eachProduct : productList) {
            if ((eachProduct.getId()) == (product.getId())) {
                int newQuantity = eachProduct.getQuantity() + product.getQuantity();
                eachProduct.setQuantity(newQuantity);
                ProductFile.writeFile(productList);
                break;
            }
        }
    }
}
