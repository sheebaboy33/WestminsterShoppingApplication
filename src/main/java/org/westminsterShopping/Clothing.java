package org.westminsterShopping;

import java.awt.*;

public class Clothing extends Product{
    private String size;
    private Color color;


    public Clothing(String productId, String productName, int availableItems, double price,
                    String size, Color color) {
        super(productId, productName, availableItems, price);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Product Type: Clothing \n" +
                super.toString() +
                ", Size: " + this.size  +
                ", RGB Value of Color: Red: " + color.getRed() +
                " Green: " + color.getGreen() +
                " Blue: " + color.getBlue();
    }
}
