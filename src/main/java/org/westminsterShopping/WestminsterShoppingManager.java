package org.westminsterShopping;

import java.util.ArrayList;

public class WestminsterShoppingManager implements ShoppingManager{
    ArrayList<Product> products = new ArrayList<>();


    @Override
    public void addProduct(Product product) {
        if (products.size() < 50) {
            products.add(product);
            System.out.println(products.size());

            System.out.println(product.getProductName() + " has been successfully added to the list.");
        }
        else {
            System.out.println("Sorry, stock cannot exceed 50 products.");
        }
    }

    @Override
    public String deleteProduct(String productId) {
        return null;
    }

    @Override
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Product list is Empty");
        } else {
            // Sort it in alphabetical order based on the product ID
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    @Override
    public void saveToFile() {

    }
}
