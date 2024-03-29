package org.westminsterShopping.Model;

import org.westminsterShopping.Controller.ShoppingManager;
import org.westminsterShopping.Controller.SummaryTableModel;
import org.westminsterShopping.Controller.WestminsterShoppingManager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.westminsterShopping.Controller.WestminsterShoppingManager.productsList;

public class ShoppingCart {
    public static Map<String, Integer> productQuantity = new HashMap<>();
    public static List<Product> productsInCart = new ArrayList<>();
    ShoppingManager manager = new WestminsterShoppingManager();
    SummaryTableModel summaryModel = new SummaryTableModel();


    public void addToShoppingCart(String productId) {
        for (Product product : productsList) {
            if (product.getProductId().equalsIgnoreCase(productId)) {
                // Check if the product is already in the cart
                if (productQuantity.containsKey(productId)) {
                    // If it is, increment the quantity by 1
                    int quantity = productQuantity.get(productId);
                    productQuantity.replace(productId, quantity, quantity + 1);

                } else {
                    // If it's not, add it with a quantity of 1
                    productQuantity.put(productId, 1);
                }

                if (!productsInCart.contains(product)){
                    productsInCart.add(product);
                }
            }
        }
        updateAvailableProducts(productId);
        //displayShoppingCart();
        summaryModel.updateCart((ArrayList<Product>) productsInCart);
    }

    public void updateAvailableProducts(String productID) {
        for (Product product : productsList) {
            if (product.getProductId().equals(productID)) {
                int currentItems = product.getAvailableItems();
                if (currentItems != 1) {
                    product.setAvailableItems(currentItems - 1);
                } else {
                    manager.deleteProduct(productID);
                }
                return;
            }
        }
    }

    public void displayShoppingCart() {
        for (Map.Entry<String, Integer> entry : productQuantity.entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();
            System.out.println("Product ID: " + productId + ", Quantity: " + quantity);
        }

        for (Product product : productsInCart) {
            System.out.println(product);
        }
    }

    public double getTotalPrice() {
        double total = 0;

        for (Product product: productsInCart) {
            total += product.getPrice() * getQuantity(product.getProductId());
        }
        return total;
    }

    public int getQuantity(String productId) {
        return productQuantity.get(productId);
    }

    public boolean getThreeItemsDiscount() {
        int clothingQuantity = 0;
        int electronicQuantity = 0;

        for (Product product : productsInCart) {
            if (product instanceof Clothing) {
                clothingQuantity += getQuantity(product.getProductId());
            } else if (product instanceof Electronics) {
                electronicQuantity += getQuantity(product.getProductId());
            }
        }

        return clothingQuantity >= 3 || electronicQuantity >= 3;
    }

}
