package org.westminsterShopping;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JLabel header = new JLabel("Selected Product - Details");
        header.setFont(new Font("SansSerif", Font.BOLD, 15));

        JLabel label = new JLabel("Product ID : " + super.getProductId());
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JLabel label1 = new JLabel("Category : " + this.getCategory());
        label1.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JLabel label2 = new JLabel("Name : " + super.getProductName());
        label2.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JLabel label3 = new JLabel("Brand : " + this.brand);
        label3.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JLabel label4 = new JLabel("Warranty Period In Weeks : " + this.warrantyPeriodInWeeks);
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
