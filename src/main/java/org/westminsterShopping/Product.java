package org.westminsterShopping;

public abstract class Product {

    static int availableItems = 0; // wrong, change to instance
    private String productId;
    private String productName;
    private double price;


    public Product(String productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        availableItems++;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    @Override
    public String toString() {
        return "ProductId: '" + productId + '\'' +
                ", ProductName: '" + productName + '\'' +
                ", Price: " + price;
    }
}
