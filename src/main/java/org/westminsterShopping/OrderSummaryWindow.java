package org.westminsterShopping;

import javax.swing.*;

public class OrderSummaryWindow extends JFrame{

    JLabel check;

    public OrderSummaryWindow() {

        this.setTitle("Shopping Cart");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        this.setLocation(600, 300);
        check = new JLabel("Hello darkness my old friend....");
        this.add(check);


    }

}
