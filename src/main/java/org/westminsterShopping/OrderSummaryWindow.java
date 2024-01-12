package org.westminsterShopping;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;


public class OrderSummaryWindow extends JFrame {

    static JTable cartTable;
    JTableHeader tableHeader;
    double total = 0;
    JLabel label2;
    JLabel label6;
    JLabel label8;
    double finalPrice;
    double firstPurchaseDiscount = 0;
    double threeItemsDiscount = 0;

    private ShoppingCart cartDetails = new ShoppingCart();

    public OrderSummaryWindow() {

        this.setLayout(new BorderLayout());

        this.setTitle("Shopping Cart");
        this.setSize(700, 600);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLocation(600, 300);

        AbstractTableModel cartTableModel = new SummaryTableModel();
        cartTable = new JTable(cartTableModel);

        // Centering the values in each column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < 3; i++) {
            cartTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


        cartTable.setRowHeight(100);
        cartTable.setGridColor(Color.BLACK);

        cartTable.setFont(new Font("Serif", Font.PLAIN, 13));
        tableHeader = cartTable.getTableHeader();
        tableHeader.setFont(new Font("SansSerif", Font.BOLD, 14));

        // Center header text
        ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        Dimension tableSize = new Dimension(850, 275);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBorder(new EmptyBorder(new Insets(30, 30, 30, 30)));
        panel.setBackground(new Color(255, 255, 255));


        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setPreferredSize(tableSize);
        scrollPane.setVisible(true);

        scrollPane.setBorder(new EmptyBorder(50, 100, 20, 100));
        scrollPane.setBackground(new Color(20, 100, 100));
        scrollPane.setWheelScrollingEnabled(true);

        panel.add(scrollPane);

        JPanel pricePanel = new JPanel(new GridLayout(4, 2));

        double total = cartDetails.getTotalPrice();

        // Create a JLabel with right-aligned text
        JLabel label1 = new JLabel("Total ");
        label1.setHorizontalAlignment(SwingConstants.RIGHT);

        label2 = new JLabel(String.valueOf(total) + "£");
        label2.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel label3 = new JLabel("First Purchase Discount (10%)");
        label3.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel label4;
        if (SignUpWindow.newCustomer) {
            firstPurchaseDiscount = total * 0.1;
            label4 = new JLabel(firstPurchaseDiscount + " £");
        } else {
            label4 = new JLabel("-");
        }
        label4.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel label5 = new JLabel("Three items the same category Discount (20%)");
        label5.setHorizontalAlignment(SwingConstants.RIGHT);



        if (cartDetails.getThreeItemsDiscount()) {
            threeItemsDiscount = total * 0.2;
            label6 = new JLabel(String.valueOf(threeItemsDiscount) + "£");
        } else {
            label6 = new JLabel("-");
        }
        label6.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel label7 = new JLabel("Final Total ");
        label7.setHorizontalAlignment(SwingConstants.RIGHT);


        finalPrice = total - (firstPurchaseDiscount + threeItemsDiscount);
        label8 = new JLabel(finalPrice + " £");
        label8.setHorizontalAlignment(SwingConstants.CENTER);


        pricePanel.add(label1);
        pricePanel.add(label2);
        pricePanel.add(label3);
        pricePanel.add(label4);
        pricePanel.add(label5);
        pricePanel.add(label6);
        pricePanel.add(label7);
        pricePanel.add(label8);

        this.add(panel, BorderLayout.CENTER);
        this.add(pricePanel, BorderLayout.SOUTH);

    }

    public void updateTotal(double newTotal) {
        // Update the total
        total = newTotal;

        // Update the text of label2 with the new total
        label2.setText(String.valueOf(total));

        // Repaint the label to reflect the changes
        label2.repaint();
    }

    public void updateThreeItemDiscount() {
        // Update the text of label6 with the new total
        label6.setText(String.valueOf(total * 0.2) + " £");

        // Repaint the label to reflect the changes
        label6.repaint();
    }

    public void updateFinalPrice() {

        if (cartDetails.getThreeItemsDiscount()){
            threeItemsDiscount = total * 0.2;
        } else if (SignUpWindow.newCustomer) {
            firstPurchaseDiscount = total * 0.1;
        }
        finalPrice = total - firstPurchaseDiscount - threeItemsDiscount;

        label8.setText(String.valueOf(finalPrice) + " £");

        // Repaint the label to reflect the changes
        label8.repaint();
    }

}




