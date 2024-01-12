package org.westminsterShopping;

import javax.swing.*;
import java.awt.*;


public class Electronics extends Product{
    private String brand;
    private int warrantyPeriodInWeeks;

    public Electronics(String productId, String productName,int availableItems, double price,
                       String brand, int warrantyPeriodInWeeks) {
        super(productId, productName, availableItems, price);
        this.brand = brand;
        this.warrantyPeriodInWeeks = warrantyPeriodInWeeks;
    }

    public Electronics() {};

    public String getCategory() { return "Electronic"; }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriodInWeeks() {
        return warrantyPeriodInWeeks;
    }


    public void setWarrantyPeriodInWeeks(int warrantyPeriodInWeeks) {
        this.warrantyPeriodInWeeks = warrantyPeriodInWeeks;
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
        JLabel label3 = createLabel("Brand : " + this.brand);
        JLabel label4 = createLabel("Warranty Period In Weeks : " + this.warrantyPeriodInWeeks);
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

    private void addComponentsToPanel(JPanel panel, Component... components) { // Using Varargs
        for (Component component : components) {         // Iterating through the array of components
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
        return "Product Type: Electronics \n" +
                super.toString() +
                ", Brand: " + this.brand  +
                ", Warranty Period In Weeks: " + this.warrantyPeriodInWeeks;
    }
}
