package org.westminsterShopping.Controller;

import org.westminsterShopping.Model.Product;
import org.westminsterShopping.Model.UserDetails;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class WestminsterShoppingManager implements ShoppingManager {
    public static ArrayList<Product> productsList = new ArrayList<>(50); // Product list of 50
    public static int productCount = 0;
    private final UserDetails USERINFO = new UserDetails();


    /**
     * Method is used to add products to the products list if it does not exceed 50
     * @param product product generated from user input
     */
    @Override
    public void addProduct(Product product) {
        if (productsList.size() < 50) {
            productsList.add(product);
            productCount++; // increase the product count

            System.out.println(product.getProductName() +
                    " has been successfully added to the product list.");
            System.out.println((50 - productCount) + " more Products can be added.");

        } else {
            System.out.println("Sorry, stock cannot exceed 50 products.");
        }
    }


    /**
     * Method is used to delete a product from the system
     * @param productId product ID provided as input
     * @return A message
     */
    @Override
    public String deleteProduct(String productId) {

        for (Product product : productsList) {
            if (product.getProductId().equals(productId)) {
                productsList.remove(product);
                productCount--; // reduce the product count
                return "Details of the product removed \n" + product +
                        "\nRemaining product count is " + productCount;
            }
        }
        return "Invalid Product Id. Please check & try again.";
    }


    /**
     * Displays the details of the products in the list and empty if it doesn't exist
     */
    @Override
    public void displayProducts() {
        if (productsList.isEmpty()) {
            System.out.println("Product list is Empty");

        } else {

            // Sort in alphabetical order based on the product ID
            productsList.sort(new SortByProductId());

            for (int i = 0; i < productsList.size(); i++) {
                System.out.println((i + 1) + ") " + productsList.get(i));
            }
            System.out.println("Current product count: " + productCount);
        }
    }


    /**
     * Saves the data to file
     */
    @Override
    public void saveToFile(){
        try {
            FileOutputStream fos = new FileOutputStream("WestminsterProductDetails.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Product product : productsList) {
                oos.writeObject(product);
            }
            System.out.println("Successfully Saved Details To The File.");

            USERINFO.saveUserToFile(); // Saving user information

            // closing the resources
            fos.close();
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage() + ", Try again.");
        }
    }


    /**
     * Retrieves the data back from the file
     */
    @Override
    public void retrieveDataFromFile(){

        // Retrieving user data back to the system
        USERINFO.retrieveDataFromFile();

        try {
            FileInputStream fis = new FileInputStream("WestminsterProductDetails.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            for (; ; ) {
                try {
                    Product product = (Product) ois.readObject();
                    productsList.add(product);
                    productCount++;

                } catch (EOFException e) {
                    break;
                }
            }
            // closing the resources
            fis.close();
            ois.close();

            System.out.println("Successfully Retrieved Data from Files.");
        } catch (Exception e) {
            //System.out.println();
        }
    }


    public JPanel getDataFromID(String productId) { // Change the name to give the meaning for the GUI
        for (Product product: productsList) {
            if (product.getProductId().equals(productId)) {
                return product.getDetailsForGUI();
            }
        }
        return null;
    }


    public ArrayList<Product> getProductsByCategory(String category) {
        ArrayList<Product> filteredProducts = new ArrayList<>();

        for (Product product : productsList) {
            if (product.getCategory().equals(category)) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }


    public int extractAvailableItems(String productId) {
        for (Product product: productsList) {
            if (product.getProductId().equals(productId)) {
                return product.getAvailableItems();
            }
        }
        return -1;
    }
}
