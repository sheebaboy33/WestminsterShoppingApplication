package org.westminsterShopping.Controller;

import org.westminsterShopping.Model.Product;

public interface ShoppingManager {
    void addProduct(Product product);
    String deleteProduct(String productId);
    void displayProducts();
    void saveToFile();
    void retrieveDataFromFile();


}
