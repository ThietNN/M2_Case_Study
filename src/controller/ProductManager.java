package controller;

import model.Product;
import model.console.NintendoSwitch;
import model.console.PlayStation;
import model.console.Xbox;
import storage.ProductFile;

import java.util.ArrayList;


public class ProductManager implements BST{
    private double principle;
    public ProductFile productFile = new ProductFile();
    public ArrayList<Product> productList = productFile.readFile();

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
        productList = productFile.readFile();
        if (!productList.isEmpty()) {
            for (Product product : productList) {
                System.out.println(product);
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

    public void addProduct(Product product) {
        productList = productFile.readFile();
        if (product != null) {
            productList.add(product);
            ProductFile.writeFile(productList);
        }
    }

    public int getPositionByID(int id){
        productList = productFile.readFile();
        int result = -1;
        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).getId() == id){
                result = i;
                break;
            }
        }
        return result;
    }

    public Product getProductByID(int id) {
        productList = productFile.readFile();
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product search(){
        CheckValidate checkValidate = new CheckValidate();
        int searchID = checkValidate.checkID();
        Product searchProduct = getProductByID(searchID);
        if (searchProduct == null){
            System.err.println("Product with ID = " + searchID + " can not be found");
        }
        return searchProduct;
    }

    public double getBuyPrice(Product product, int quantity){
        double price = product.getBuyPrice();
        return price * quantity;
    }
    public double getSellPrice(Product product, int quantity){
        double price = product.getSellPrice();
        return price * quantity;
    }

    public boolean sellable(Product product, int quantity){
        return product.getQuantity() >= quantity;
    }

    public void sellProduct(Product product, int quantity){
        double sellPrice = getSellPrice(product, quantity);
        principle = principle + sellPrice;
        int newQuantity = product.getQuantity() - quantity;
        product.setQuantity(newQuantity);
    }

    public void sell() {
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

    public boolean buyable(Product product, int quantity){
        double buyPrice = getBuyPrice(product, quantity);
        return principle >= buyPrice;
    }

    public void buyNewProduct(Product product, int quantity){
        double buyPrice = getBuyPrice(product, quantity);
        principle = principle - buyPrice;
        product.setQuantity(quantity);
    }
    public void buyExistProduct(Product product, int quantity){
        double buyPrice = getBuyPrice(product,quantity);
        principle = principle - buyPrice;
        quantity = product.getQuantity() + quantity;
        product.setQuantity(quantity);
    }

    public void buyProduct(boolean exist, Product product, int quantity){
        if (exist)
            buyExistProduct(product,quantity);
        else
            buyNewProduct(product,quantity);
    }

    public void buy() {
        productList = productFile.readFile();
        CheckValidate checkValidate = new CheckValidate();
        boolean exist;
        int id = checkValidate.checkID();
        Product product = getProductByID(id);
        int quantity = checkValidate.checkQuantity();
        if (product != null){
            exist = true;
            System.out.println("Product with this ID is already exist. Proceed to add quantity. ");
            if (!buyable(product, quantity)) {
                System.err.println("Not enough fund to buy");
                return;
            }
        }
        else {
            exist = false;
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
        buyProduct(exist,product,quantity);
        System.out.println("Transaction completed");
        ProductFile.writeFile(productList);
    }
    public boolean principleCheck(Product buyProduct, int buyQuantity, Product sellProduct, int sellQuantity){
        double buyPrice = getBuyPrice(buyProduct, buyQuantity);
        double sellPrice = getSellPrice(sellProduct, sellQuantity);
        double difference = buyPrice - sellPrice;
        double principleCheck = principle - difference;
        return principleCheck < 0;
    }
    public void tradeProduct(Product sellProduct, int sellQuantity, boolean buyProductExist, Product buyProduct, int buyQuantity){
        double sellPrice = getSellPrice(sellProduct, sellQuantity);
        principle = principle + sellPrice;
        int newSellQuantity = sellProduct.getQuantity() - sellQuantity;
        sellProduct.setQuantity(newSellQuantity);

        if (buyProductExist)
            buyExistProduct(buyProduct,buyQuantity);
        else
            buyNewProduct(buyProduct,buyQuantity);
    }
    public void trade(){
        productList = productFile.readFile();
        CheckValidate checkValidate = new CheckValidate();
        boolean buyProductExist;
        System.out.println("Product give: ");
        int sellID = checkValidate.checkID();
        int replaceSellID = getPositionByID(sellID);
        Product sellProduct = getProductByID(sellID);
        if (sellProduct == null){
            System.err.println("Product not found");
            return;
        }
        int sellQuantity = checkValidate.checkQuantity();
        if (!sellable(sellProduct,sellQuantity)){
            System.err.println("Not enough product to trade");
            return;
        }

        System.out.println("Product take: ");
        int buyID = checkValidate.checkID();
        Product buyProduct;
        if (sellID == buyID){
            buyProduct = sellProduct;
        }else
            buyProduct = getProductByID(buyID);
        if (buyProduct == null){
            buyProduct = checkValidate.checkProductType();
            buyProduct.setId(buyID);
            setPrice(buyProduct);
            buyProductExist = false;
        }else{
            buyProductExist = true;
        }
        int buyQuantity = checkValidate.checkQuantity();
        boolean principleCheck = principleCheck(buyProduct,buyQuantity,sellProduct,sellQuantity);
        if (principleCheck) {
            System.err.println("Not enough fund to trade. ");
        }

        if (!buyProductExist){
            setRest(buyProduct);
            addProduct(buyProduct);
        }
        tradeProduct(sellProduct,sellQuantity,buyProductExist,buyProduct,buyQuantity);
        productList.set(replaceSellID,sellProduct);
        System.out.println("Transaction completed");
        ProductFile.writeFile(productList);
    }


    public void setQuantity(Product product){
        CheckValidate checkValidate = new CheckValidate();
        int quantity = checkValidate.checkQuantity();
        product.setQuantity(quantity);
    }

    public void setPrice(Product product){
        CheckValidate checkValidate = new CheckValidate();
        double price = checkValidate.checkPrice();
        product.setPrice(price);
    }

    public void setRest(Product product){
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
    }

    public Product createNewProduct() {
        CheckValidate checkValidate = new CheckValidate();
        Product product = checkValidate.checkProductType();
        int id = checkValidate.checkID();
        product.setId(id);
        if (checkExistence(product)){
            System.err.println("Product is already exist!");
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

    public boolean checkExistence(Product product){
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


}
