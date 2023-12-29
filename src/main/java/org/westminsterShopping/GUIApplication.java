package org.westminsterShopping;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUIApplication extends JFrame implements ActionListener{
    static WestminsterShoppingManager manager = new WestminsterShoppingManager();

    JTable table;
    JTableHeader header;
    JButton cart;
    JLabel selectProduct;
    JComboBox<String> displayChoiceComboBox;

    JPanel productDetail;


    // Constructor has way too much code. Reduce it using methods
    public GUIApplication() {

        productDetail = new JPanel();
        productDetail.setLayout(new BorderLayout());


        selectProduct = new JLabel("Select Product Category ");

        String[] items ={"All", "Electronic", "Clothing"};
        displayChoiceComboBox = new JComboBox<>(items);
        displayChoiceComboBox.addActionListener(this);


        cart = new JButton("Shopping Cart");
        cart.setMargin(new Insets(5, 5, 5, 5));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255,255));

        buttonPanel.setLayout(new BorderLayout(0, 0));
        buttonPanel.add(cart, BorderLayout.EAST);
        buttonPanel.setBorder(new EmptyBorder(new Insets(20,20,0,20)));

        ProductTableModel tableModel = new ProductTableModel();
        table = new JTable(tableModel);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int ID_COLUMN = 0;
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1) {
                    int selectedRowIndex = table.getSelectedRow();

                    // First column includes the product ID which is unique for each product
                    String productID = (String)table.getValueAt(selectedRowIndex, ID_COLUMN);

                    productDetail.removeAll();

                    JPanel detailsPanel = getProductDetails(productID);

                    // If the selected row is empty, getProductDetails() returns null
                    if (detailsPanel != null) {
                        productDetail.add(detailsPanel, BorderLayout.CENTER);


                        productDetail.revalidate();
                        productDetail.repaint();
                    }

                }
            }
        });

        // Centering the values in each column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );


        for (int i = 0; i < 5; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.setRowHeight(35);
        table.setGridColor(Color.BLACK);

        table.setFont(new Font("Serif", Font.PLAIN, 13));
        header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));

        // Center header text
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);


        Dimension tableSize = new Dimension(900, 195);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(tableSize);
        scrollPane.setVisible(true);

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.setBackground(new Color(255, 255,255));

        p2.add(selectProduct);
        p2.add(displayChoiceComboBox);


        // For the table
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(255, 255,255));

        p1.setLayout(new FlowLayout());
        p1.add(scrollPane);
        p1.setBorder(new EmptyBorder(new Insets(30, 30,30,30)));
        // add to the container and continue the code

        JPanel combine = new JPanel();
        combine.setLayout(new BorderLayout());

        combine.add(p1, BorderLayout.CENTER);
        combine.add(p2, BorderLayout.NORTH);


        // For the combox
        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        p3.add(buttonPanel, BorderLayout.PAGE_START);
        p3.add(combine, BorderLayout.CENTER);

        p3.add(productDetail, BorderLayout.SOUTH);


        //Border blackLine = BorderFactory.createLineBorder(Color.black);
        //p3.setBorder(blackLine);

        this.add(p3);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == displayChoiceComboBox) {
            String selectedCategory = (String) displayChoiceComboBox.getSelectedItem();

            ArrayList<Product> filteredProducts;

            if (selectedCategory.equals("All")) {
                filteredProducts = WestminsterShoppingManager.productsList;
            } else {
                filteredProducts = manager.getProductsByCategory(selectedCategory);
            }

            // Update table model with filtered products
            ((ProductTableModel) table.getModel()).updateData(filteredProducts);

        }

    }

    public JPanel getProductDetails(String productID) {
        return manager.getDataFromID(productID);
    }
}

