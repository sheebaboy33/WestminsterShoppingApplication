package org.westminsterShopping.Controller;

import org.westminsterShopping.Model.Clothing;
import org.westminsterShopping.Model.Electronics;
import org.westminsterShopping.Model.Product;
import org.westminsterShopping.Model.ShoppingCart;
import org.westminsterShopping.View.LoginWindow;
import org.westminsterShopping.View.ProductDetailsWindow;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.westminsterShopping.Controller.WestminsterShoppingManager.productsList;

/**
 * The main entry point for the console application.
 * **/
public class ConsoleApplication {
    static ShoppingManager manager = new WestminsterShoppingManager();
    public static Scanner input = new Scanner(System.in);


    public static void main(String[] args){
        manager.retrieveDataFromFile();

        // Display welcome message and start the menu loop
        System.out.println("*".repeat(52) + "\n* Welcome to the Westminster Shopping Application! *\n" + "*".repeat(52));

        menuLoop :
        while (true) {
            int choice;

            displayMenu();
            System.out.print("Select an option: \n>>>");

            try {
                // Prompt the user for input and handle their choice
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid option, Try again." );
                input.nextLine(); // skip the invalid line in the buffer
                continue ;
            }


            switch (choice) {
                case 100:
                    addProductToSystem();
                    break;

                case 101:
                    System.out.println("Enter the Product ID: ");
                    String productId = input.next();
                    System.out.println(manager.deleteProduct(productId));
                    break;

                case 102:
                    manager.displayProducts();
                    break;

                case 103:
                    manager.saveToFile();
                    break;

                case 104:

                    // Inorder to create a new GUI, both the product and login windows of the GUI must be closed
                    if (ProductDetailsWindow.getProductWindowState() == null && LoginWindow.getLoginInstanceState() == null) {
                        LoginWindow frame = LoginWindow.getInstance();
                        frame.setTitle("Login - Westminster Shopping Centre");
                        frame.setSize(500, 300);
                        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                        frame.setLocation(400, 250);

                    } else if (!ProductDetailsWindow.getProductsWindowInstance().isVisible()) { // To implement the GUI again from the hidden state
                        // Resetting the product cart to be empty
                        ShoppingCart.productsInCart.clear();
                        ShoppingCart.productQuantity.clear();
                        LoginWindow.getInstance().setVisible(true);
                    }

                    System.out.println("GUI Application is now open.");
                    break;

                case 105:
                    System.out.println("Thank you for using the system. Have a pleasant day!");
                    manager.saveToFile();
                    break menuLoop;

                default:
                    System.out.println("Invalid option.Please try again!");
                    break;
            }
        }
        input.close();
        System.exit(0);
    }


    /**
     * Displays the menu options for the user.
     */
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


    /**
     * Adds products to the system based on user input.
     */
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
                        Invalid input. Make sure the Product must be either 'Electronic' or 'Clothing'
                        """);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an Integer and try again.");

        }
    }


    /**
     * Validates and retrieves user input to create a new Product object.
     *
     * @return A new Product object based on user input.
     * @throws InputMismatchException If the input does not match the expected format.
     */
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
        productId = validateProductId(productId);


        input.nextLine(); // Skipping the extra line in the buffer

        System.out.print("Enter Product Name: \n>>>");
        String productName = input.nextLine();

        System.out.print("Enter Available Items: \n>>>");
        int availableItems = input.nextInt();
        availableItems = validateAvailableItems(availableItems);

        System.out.print("Enter Product Price: \n>>>");
        double price = input.nextDouble();
        price = validatePrice(price);


        switch (productType.toLowerCase().trim()) {
            case "electronic" -> {

                System.out.print("Enter Brand Name: \n>>>");
                String brand = input.next();

                System.out.print("Enter Warranty Period in Weeks: \n>>>");
                int warrantyPeriod = input.nextInt();
                warrantyPeriod = validateWarrantyPeriod(warrantyPeriod);


                product = new Electronics(productId, productName, availableItems, price, brand, warrantyPeriod);
            }
            case "clothing" -> {
                System.out.print("Enter Size (L/ M/ S): \n>>>"); // Validate the sizes
                String size = input.next();
                size = validateClothSize(size);

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

    public static int validateWarrantyPeriod(int warrantyPeriod) {
        if (warrantyPeriod >= 0) {
            return warrantyPeriod;
        }
        else {
            while (warrantyPeriod < 0) {
                System.out.print("Warranty Period in Weeks Must Be Greater Than or equals to Zero.\n>>>");
                warrantyPeriod = input.nextInt();
            }
        }
        return warrantyPeriod;
    }


    public static String validateClothSize(String size) {
        String formattedSize = size.toLowerCase().trim();

        while (!formattedSize.equals("l") && !formattedSize.equals("m") && !formattedSize.equals("s")) {
            System.out.println("Invalid size. Please enter L, M or S");

            System.out.print(">>> ");
            formattedSize = input.next().toLowerCase().trim();
        }
        return formattedSize.toUpperCase();
    }


    public static String validateProductId(String productId) {
        boolean correctFormat = checkFormat(productId);

        while (!correctFormat || !checkUniqueId(productId)) {
            System.out.println("Invalid ProductId. Please check for the following conditions,\n" +
                    "-Should start with an Uppercase letter followed by 3 numerals.\n" +
                    "-Must be Unique from existing products in the system.\nEx: A001");

            System.out.print(">>> ");
            productId = input.next();

            correctFormat = checkFormat(productId);
        }
        return productId;
    }

    public static boolean checkUniqueId(String productId) {
        for (Product checkProduct: productsList) {
            if (checkProduct.getProductId().equals(productId)) {
                // ProductId cannot be the same.
                return false;
            }
        }
        return true;

    }


    public static int validateAvailableItems(int availableItems) {
        if (availableItems > 0) {
            return availableItems;
        } else {

            while (availableItems <= 0) {
                System.out.print("Available Items Must Be Greater Than Zero.\n>>>");
                availableItems = input.nextInt();
            }
        }
        return availableItems;
    }


    public static boolean checkFormat(String productId){
        String requiredPattern = "[A-Z]\\d{3}"; // Required regular expression Ex: A001
        return productId.matches(requiredPattern);
    }

    public static double validatePrice(double price) {
        if (price > 0) {
            return price;
        } else {

            while (price <= 0) {
                System.out.print("Price Must Be Greater Than Zero.\n>>>");
                price = input.nextInt();
            }
        }
        return price;
    }
}
