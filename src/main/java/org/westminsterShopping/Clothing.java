package org.westminsterShopping;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Clothing extends Product{
    private String size;
    private String color;


    public Clothing(String productId, String productName, int availableItems, double price,
                    String size, String color) {
        super(productId, productName, availableItems, price);
        this.size = size;
        this.color = color;
    }

    public Clothing() {};

    @Override
    public String getCategory() {
        return "Clothing";
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() { return color;}

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public JPanel getDetailsForGUI() {

        JLabel header = new JLabel("Selected Product - Details");
        header.setFont(new Font("SansSerif", Font.BOLD, 15));

        JLabel label = new JLabel("Product ID : " + super.getProductId());
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JLabel label1 = new JLabel("Category : " + this.getCategory());
        label1.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JLabel label2 = new JLabel("Name : " + super.getProductName());
        label2.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JLabel label3 = new JLabel("Size : " + this.size);
        label3.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JLabel label4 = new JLabel("Color : " + this.color);
        label4.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JLabel label5 = new JLabel("Items Available : " + super.getAvailableItems());
        label5.setFont(new Font("SansSerif", Font.PLAIN, 13));


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set layout to BoxLayout with Y_AXIS orientation
        panel.setBackground(new Color(255, 255, 255));
        panel.setPreferredSize(new Dimension(1000, 300));


        panel.add(Box.createRigidArea(new Dimension(10, 15)));
        panel.add(header);
        panel.add(Box.createRigidArea(new Dimension(10, 15)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(10, 15)));
        panel.add(label1);
        panel.add(Box.createRigidArea(new Dimension(10, 15)));
        panel.add(label2);
        panel.add(Box.createRigidArea(new Dimension(10, 15)));
        panel.add(label3);
        panel.add(Box.createRigidArea(new Dimension(10, 15)));
        panel.add(label4);
        panel.add(Box.createRigidArea(new Dimension(10, 15)));
        panel.add(label5);
        panel.add(Box.createRigidArea(new Dimension(10, 15)));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));


        mainPanel.add(panel, BorderLayout.CENTER);
        // Add the button panel to the main panel in the south

        return mainPanel;
    }


    @Override
    public String toString() {
        return "Product Type: Clothing \n" +
                super.toString() +
                ", Size: " + this.size  +
                ", Color: " + this.color;
    }
}
