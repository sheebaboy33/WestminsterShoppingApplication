package org.westminsterShopping;

import java.io.IOException;

public interface ShoppingManager {
    void addProduct(Product product);
    String deleteProduct(String productId);
    void displayProducts();
    void saveToFile(String fileName) throws IOException;
    void retrieveDataFromFile(String fileName) throws IOException, ClassNotFoundException;


}
