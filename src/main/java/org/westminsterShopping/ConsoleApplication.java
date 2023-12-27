package org.westminsterShopping;

import java.awt.*;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.westminsterShopping.WestminsterShoppingManager.productsList;

public class ConsoleApplication {
    static ShoppingManager manager = new WestminsterShoppingManager();
    static Scanner input = new Scanner(System.in);


    public static void main(String[] args){

        System.out.println("*".repeat(52) + "\n* Welcome to the Westminster Shopping Application! *\n" + "*".repeat(52));

        menuLoop :
        while (true) {

            // TODO: Validate input for integer
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
                    // GUI
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
                Menu Options \n
                100 - Add New Product to the System.
                101 - Delete a Product from the System.
                102 - Display the list of Products in the System.
                103 - Save to File.
                104 - Open GUI for Client.
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
                input.nextLine();
                System.out.print("Enter Brand Name: \n>>>");
                String brand = input.nextLine();

                System.out.print("Enter Warranty Period in Weeks: \n>>>");
                int warrantyPeriod = input.nextInt();
                product = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
            }
            case "clothing" -> {
                System.out.print("Enter Size (L/ M/ S): \n>>>"); // Validate the sizes
                String size = input.next();

                System.out.print("Enter Color (values must be in the range of 0 to 255): \n>>> R value: ");
                int red = input.nextInt();

                System.out.print(">>> G value: ");
                int green = input.nextInt();

                System.out.print(">>> B value: ");
                int blue = input.nextInt();

                if (isInvalidColor(red) || isInvalidColor(green) || isInvalidColor(blue)) {
                    return null;
                }
                product = new Clothing(productId, productName, availableItems, price, size, new Color(red, green, blue));
            }
            default -> {
                return null;
            }
        }
        return product;
    }

    private static boolean isInvalidColor(int value) {
        return !(value >= 0 && value <= 255);
    }
}

