package controller;

import model.Product;
import model.console.ConsoleFactory;


import java.util.Scanner;

public class CheckValidate {
    public int checkID(){
        Scanner scanner = new Scanner(System.in);
        int check;
        System.out.println("Enter products' ID");
        while (true){
            try {
                check = Integer.parseInt(scanner.nextLine());
                if (check <= 0)
                    System.out.println("ID can neither be zero nor negative number");
                else
                    break;
            }catch (Exception exception){
                System.err.println("ID must be a number! ");
            }
        }
        return check;
    }
    public String checkName(){
        Scanner scanner = new Scanner(System.in);
        String check;
        System.out.println("Enter products' name");
        while (true){
            check = scanner.nextLine();
            if (check.equals(""))
                System.err.println("Please don't leave this field empty. ");
            else
                break;
        }
        return check;
    }
    public int checkQuantity(){
        Scanner scanner = new Scanner(System.in);
        int check;
        System.out.println("Enter quantity: ");
        while (true){
            try{
                check = Integer.parseInt(scanner.nextLine());
                if (check < 0)
                    System.err.println("Quantity can't be a negative number! ");
                else
                    break;
            }catch (Exception exception){
                System.err.println("Quantity must be a number! ");
            }
        }
        return check;
    }
    public double checkPrice(){
        Scanner scanner = new Scanner(System.in);
        double check;
        System.out.println("Enter price: ");
        while (true){
            try{
                check = Double.parseDouble(scanner.nextLine());
                if (check < 0)
                    System.err.println("Price can't be a negative number! ");
                else if (check == 0)
                    System.err.println("It's business! You cannot just give away things");
                else
                    break;
            }catch (Exception exception){
                System.err.println("Price must be a number! ");
            }
        }
        return check;
    }
    public boolean checkCondition(){
        Scanner scanner = new Scanner(System.in);
        boolean check;
        String input;
        System.out.println("Enter condition: (new/used)");
        while (true) {
            input = scanner.nextLine();
            if (input.equals("new")){
                check = true;
                break;
            }
            else if (input.equals("used")) {
                check = false;
                break;
            }
            else
                System.err.println("Please enter the right condition of the product: ");
        }
        return check;
    }
//    public String checkColor(){
//        Scanner scanner = new Scanner(System.in);
//        String check;
//        System.out.println("Enter products' color");
//        while (true){
//            check = scanner.nextLine();
//            if (check.equals(""))
//                System.err.println("Please don't leave this field empty. ");
//            else
//                break;
//        }
//        return check;
//    }
    public Product checkProductType(){
        Scanner scanner = new Scanner(System.in);
        ConsoleFactory consoleFactory = new ConsoleFactory();
        Product product;
        String type;
        System.out.println("Type 'ps' for PlayStation, 'xbox' for Xbox, 'ns' for NintendoSwitch");

        type = scanner.nextLine();
        product = consoleFactory.getConsole(type);
        return product;
    }
    public int checkGeneration(){
        Scanner scanner = new Scanner(System.in);
        int check;
        System.out.println("Enter the PS generation: ");
        while(true){
            try {
                check = Integer.parseInt(scanner.nextLine());
                if (check < 0)
                    System.err.println("This generation of PS doesn't exist");
                else if (check > 5)
                    System.err.println("Are you a time traveller or something? ");
                else
                    break;
            }catch (Exception exception){
                System.err.println("Generation of a PS must be a number");
            }
        }
        return check;
    }
    public String checkEdition(){
        Scanner scanner = new Scanner(System.in);
        String check;
        System.out.println("Enter products' edition");
        while (true){
            check = scanner.nextLine();
            if (check.equals(""))
                System.err.println("Please don't leave this field empty. ");
            else
                break;
        }
        return check;
    }
    public String checkType(){
        Scanner scanner = new Scanner(System.in);
        String check;
        System.out.println("Enter products' type: (1/360/one/x/s)");
        while (true){
            check = scanner.nextLine();
            if (check.equals("1"))
                break;
            else if (check.equals("360"))
                break;
            else if (check.equals("one"))
                break;
            else if (check.equals("x"))
                break;
            else if (check.equals("s"))
                break;
            else if (check.equals(""))
                System.err.println("Please don't leave this field empty. ");
        }
        return check;
    }
    public boolean checkLite(){
        Scanner scanner = new Scanner(System.in);
        boolean check;
        String input;
        System.out.println("Enter condition: (standard/lite)");
        while (true) {
            input = scanner.nextLine();
            if (input.equals("lite")){
                check = true;
                break;
            }
            else if (input.equals("standard")) {
                check = false;
                break;
            }
            else
                System.err.println("Please enter the right condition of the product: ");
        }
        return check;
    }
    public double checkPrinciple(){
        Scanner scanner = new Scanner(System.in);
        double principle;
        System.out.println("Enter principle value: ");
        while (true) {
            try {
                principle = Double.parseDouble(scanner.nextLine());
                if (principle < 0)
                    System.err.println("Principle can not be a negative number");
                else
                    break;
            }catch (Exception exception){
                System.err.println("Principle must be a number");
            }
        }
        return principle;
    }

}
