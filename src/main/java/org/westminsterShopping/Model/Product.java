package org.westminsterShopping.Model;

import javax.swing.*;
import java.io.Serializable;


public abstract class Product implements Serializable{

    private int availableItems;
    private String productId;
    private String productName;
    private double price;

    public Product(String productId, String productName, int availableItems, double price) {
        this.productId = productId;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
    }

    public Product() {};

    public abstract String getCategory();
    public abstract JPanel getDetailsForGUI();

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(int availableItems) {
        this.availableItems = availableItems;
    }

    @Override
    public String toString() {
        return "ProductId: " + this.productId +
                ", ProductName: " + this.productName +
                ", AvailableItems: " + this.availableItems +
                ", Price: " + this.price ;
    }

}
