package org.westminsterShopping;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;


public class OrderSummaryWindow extends JFrame {

    JLabel check;
    JTable cartTable;
    JTableHeader tableHeader;

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

        this.add(panel, BorderLayout.CENTER);


        JPanel pricePanel = new JPanel();

    }
}




