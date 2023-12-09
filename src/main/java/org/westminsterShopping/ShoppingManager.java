package org.westminsterShopping;

public interface ShoppingManager {
    void addProduct(Product product);
    String deleteProduct(String productId);
    void displayProducts();
    void saveToFile();



}
