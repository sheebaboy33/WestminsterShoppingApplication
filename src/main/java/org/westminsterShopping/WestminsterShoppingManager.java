package org.westminsterShopping;

import java.util.ArrayList;

public class WestminsterShoppingManager implements ShoppingManager{
    public static ArrayList<Product> productsList = new ArrayList<>();
    public static int productCount = 0;

    @Override
    public void addProduct(Product product) {
        if (productsList.size() < 50) {
            productsList.add(product);
            productCount++;
            System.out.println(productsList.size());

            System.out.println(product.getProductName() + " has been successfully added to the product list.");
        }
        else {
            System.out.println("Sorry, stock cannot exceed 50 products.");
        }
    }

    @Override
    public String deleteProduct(String productId) {

            for (Product product: productsList) {
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
        }
    }

    @Override
    public void saveToFile() {

    }
}
