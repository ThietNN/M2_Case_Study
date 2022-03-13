package Client;


import controller.ProductManagerProxy;
import model.Console;
import model.console.PlayStation;
import model.console.Xbox;
import storage.ProductFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static controller.ProductManager.*;

public class Client {
    public static void main(String[] args) {
        ProductManagerProxy productManagerProxy = new ProductManagerProxy();
        ArrayList<Console> consoleList = new ArrayList<>();

        productManagerProxy.setPrinciple(100000);
        PlayStation playStation1 = new PlayStation(1,"PS",25,12000,true,4,"Persona 5");
        Xbox xbox1 = new Xbox(2,"Xbox",12,25000,false,"One");
        consoleList.add(playStation1);
        consoleList.add(xbox1);
        ProductFile.writeFile(consoleList);

        showMenu();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> showMenu();
                case 2 -> productManagerProxy.getInfo();
                case 3 -> {
                    double result = productManagerProxy.getPrinciple();
                    System.out.println("Principle = " + result);
                }
                case 4 -> productManagerProxy.setPrinciple();
                case 5 -> {
                    Console newConsole = productManagerProxy.createNewProduct();
                    productManagerProxy.addProduct(newConsole);
                }
                case 6 -> {
                    Console searchConsole = productManagerProxy.search();
                    System.out.println(searchConsole);
                }
                case 7 -> productManagerProxy.sell();
                case 8 -> productManagerProxy.buy();
                case 9 -> productManagerProxy.trade();
                case 10 -> {
                    System.out.println("Sort by ID: ");
                    Collections.sort(consoleList);
                    productManagerProxy.getInfo();
                }
            }
        }while (choice != 0);
    }
}
