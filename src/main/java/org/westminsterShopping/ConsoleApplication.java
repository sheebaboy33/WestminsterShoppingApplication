package org.westminsterShopping;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.westminsterShopping.WestminsterShoppingManager.productsList;

public class ConsoleApplication {
    static ShoppingManager manager = new WestminsterShoppingManager();
    static Scanner input = new Scanner(System.in);


    public static void main(String[] args){
        manager.retrieveDataFromFile();

        System.out.println("*".repeat(52) + "\n* Welcome to the Westminster Shopping Application! *\n" + "*".repeat(52));


        // Issue when inserting an invalid input as the option
        menuLoop :
        while (true) {

            displayMenu();

            System.out.print("Select an option: \n>>>");
            int choice = 0;

            try {
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage() + ", Try again." );
            }

            switch (choice) {
                case 100:
                    addProductToSystem();
                    break;

                case 101:
                    System.out.println("Enter the Product ID: \n");
                    String productId = input.next();
                    manager.deleteProduct(productId);
                    break;

                case 102:
                    manager.displayProducts();
                    break;

                case 103:
                    manager.saveToFile();
                    break;

                case 104:

                    //JFrame frame = new ProductDetailsWindow();
                    LoginWindow frame = LoginWindow.getInstance();
                    frame.setTitle("Login Page - Westminster Shopping Centre");
                    frame.setSize(500, 300);
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    frame.setLocation(400, 250);

                    System.out.println("GUI Application is now open.");
                    break;

                case 105:
                    System.out.println("Thank you for using the system. Have a pleasant day!");
                    manager.saveToFile();
                    break menuLoop;

                default:
                    System.out.println("Invalid option.Please try again!");
                    displayMenu();
                    break;
            }
        }
        input.close();
    }


    public static void displayMenu() {
        System.out.println("""
                \nMenu Options
                100 - Add New Product to the System.
                101 - Delete a Product from the System.
                102 - Display the list of Products in the System.
                103 - Save to File.
                104 - Open GUI for Customer.
                105 - Quit the Application.
                
                """);
    }


    public static void addProductToSystem() {
        try {
            Product product = getProductInput();


            if (product != null) {

                for (Product checkProduct: productsList) {
                    if (checkProduct.getProductId().equals(product.getProductId())) {
                        System.out.println("Product Id cannot be the same. Please Try again.");
                        return;
                    }
                }
                manager.addProduct(product);
            }
            else
                System.out.println("""
                        Invalid input. Please try again by checking for the errors below.
                        -Product must be either 'Electronic' or 'Clothing'
                        -RGB Colors should be in the range of 0-255
                        """);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an Integer and try again.");

        }
    }


    public static Product getProductInput() throws InputMismatchException{

        Product product;

        System.out.print("Enter Product Type (Electronic/ Clothing): \n>>>");
        String productType = input.next();

        if (!productType.equalsIgnoreCase("electronic") &&
                !productType.equalsIgnoreCase("clothing")) {
            return null;
        }

        System.out.print("Enter Product ID: \n>>>");
        String productId = input.next();

        input.nextLine();

        System.out.print("Enter Product Name: \n>>>");
        String productName = input.nextLine();

        System.out.print("Enter Available Items: \n>>>");
        int availableItems = input.nextInt();

        System.out.print("Enter Product Price: \n>>>");
        double price = input.nextDouble();


        switch (productType.toLowerCase().trim()) {
            case "electronic" -> {
                //input.nextLine();
                System.out.print("Enter Brand Name: \n>>>");
                String brand = input.next();

                System.out.print("Enter Warranty Period in Weeks: \n>>>");
                int warrantyPeriod = input.nextInt();
                product = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
            }
            case "clothing" -> {
                System.out.print("Enter Size (L/ M/ S): \n>>>"); // Validate the sizes
                String size = input.next();

                System.out.print("Enter Color : ");
                String color = input.next();

                product = new Clothing(productId, productName, availableItems, price, size, color);
            }
            default -> {
                return null;
            }
        }
        return product;
    }

}

