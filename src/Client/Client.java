package Client;


import model.Product;
import model.console.PlayStation;
import model.console.Xbox;
import storage.ProductFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static controller.ProductManager.*;

public class Client {
    public static void main(String[] args) {
        ArrayList<Product> productList = new ArrayList<>();
        PlayStation playStation1 = new PlayStation(1,"PS",25,12000,true,4,"Persona 5");
        Xbox xbox1 = new Xbox(2,"Xbox",12,25000,false,"One");
        productList.add(playStation1);
        productList.add(xbox1);
        ProductFile.writeFile(productList);

        showMenu();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    showMenu();
                    break;
                case 2:
                    getInfo();
                    break;
                case 3:
                    double result = getPrinciple();
                    System.out.println("Principle = " + result);
                    break;
                case 4:
                    Product newProduct = createNewProduct();
                    addProduct(newProduct);
                    break;
                case 5:
                    Product searchProduct = search();
                    System.out.println(searchProduct);
                    break;
                case 6:
                    sell();
                    break;
                case 7:
                    buy();
                    break;
                case 8:
                    trade();
                    break;
                case 9:
                    System.out.println("Sort by ID: ");
                    Collections.sort(productList);
                    getInfo();
                    break;
            }
        }while (choice != 10);
    }
}
