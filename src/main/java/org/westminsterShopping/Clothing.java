package org.westminsterShopping;

import javax.swing.*;
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
        JPanel panel = createPanelWithDetails();
        return createMainPanel(panel);
    }

    private JPanel createPanelWithDetails() {
        JLabel header = createHeaderLabel();
        JLabel label = createLabel("Product ID : " + super.getProductId());
        JLabel label1 = createLabel("Category : " + this.getCategory());
        JLabel label2 = createLabel("Name : " + super.getProductName());
        JLabel label3 = createLabel("Size : " + this.size);
        JLabel label4 = createLabel("Color : " + this.color);
        JLabel label5 = createLabel("Items Available : " + super.getAvailableItems());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 255, 255));
        panel.setPreferredSize(new Dimension(1000, 300));

        addComponentsToPanel(panel, header, label, label1, label2, label3, label4, label5);
        return panel;
    }

    private JLabel createHeaderLabel() {
        JLabel header = new JLabel("Selected Product - Details");
        header.setFont(new Font("SansSerif", Font.BOLD, 15));
        return header;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
        return label;
    }

    private void addComponentsToPanel(JPanel panel, Component... components) {
        for (Component component : components) {
            panel.add(Box.createRigidArea(new Dimension(10, 15)));
            panel.add(component);
        }
    }

    private JPanel createMainPanel(JPanel panel) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.add(panel, BorderLayout.CENTER);
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
