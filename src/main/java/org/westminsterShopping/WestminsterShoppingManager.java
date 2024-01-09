package org.westminsterShopping;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class WestminsterShoppingManager implements ShoppingManager {
    static ArrayList<Product> productsList = new ArrayList<>(50);
    static int productCount = 0;
    UserDetails userInfo = new UserDetails();


    @Override
    public void addProduct(Product product) {
        if (productsList.size() < 50) {
            productsList.add(product);
            productCount++;
            System.out.println(productsList.size());

            System.out.println(product.getProductName() +
                    " has been successfully added to the product list.");
            System.out.println((50 - productCount) + " more Products can be added.");

        } else {
            System.out.println("Sorry, stock cannot exceed 50 products.");
        }
    }


    @Override
    public String deleteProduct(String productId) {

        for (Product product : productsList) {
            if (product.getProductId().equals(productId)) {
                productsList.remove(product);
                productCount--;
                return "Details of the product removed \n" + product +
                        "\nRemaining product count is " + productCount;
            }
        }
        return "Invalid Product Id. Please check & try again.";
    }


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


    @Override
    public void saveToFile(){
        try {
            FileOutputStream fos = new FileOutputStream("WestminsterProductDetails.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Product product : productsList) {
                oos.writeObject(product);
            }
            System.out.println("Product Details Successfully Saved to File.");

            fos.close();
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage() + ", Try again.");
        }
    }


    @Override
    public void retrieveDataFromFile(){

        // Retrieving user data back to the system
        userInfo.retrieveDataFromFile();

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
            fis.close();
            ois.close();

            System.out.println("Data has been successfully retrieved from the file.");
        } catch (Exception e) {
            System.out.println(e.getMessage() + ", Try again.");
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
        return -1; // TODO: Make sure when entering available items from the console the values must be grater than 1
    }
}

