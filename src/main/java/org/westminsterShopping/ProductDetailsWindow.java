package org.westminsterShopping;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ProductDetailsWindow extends JFrame implements ActionListener{
    private static ProductDetailsWindow productsWindowInstance = null;
    static WestminsterShoppingManager manager = new WestminsterShoppingManager();
    static JTable productTable;
    JTableHeader header;
    JButton shoppingCartBtn, addToCartBtn;
    JLabel selectProduct;
    JComboBox<String> displayChoiceComboBox;
    JPanel productDetailsPanel;
    // JButton addToCartBtn;
    private OrderSummaryWindow summaryWindow;
    ShoppingCart shoppingCart = new ShoppingCart();
    AbstractTableModel cartTableModel, tableModel;


    // Constructor has way too much code. Reduce it using methods
    private ProductDetailsWindow() {


        // Add a condition to check if the window is null

        productDetailsPanel = new JPanel();
        productDetailsPanel.setLayout(new BorderLayout());

        productDetailsPanel.setBackground(new Color(254, 235,216));


        selectProduct = new JLabel("Select Product Category ");
        LoginWindow.designJLabel(selectProduct, Color.BLACK);
        selectProduct.setFont(new Font("Monospaced", Font.ITALIC, 12));

        String[] items ={"All", "Electronic", "Clothing"};
        displayChoiceComboBox = new JComboBox<>(items);
        displayChoiceComboBox.addActionListener(this);


        shoppingCartBtn = new JButton("Shopping Cart");
        shoppingCartBtn.setMargin(new Insets(5, 5, 5, 5));
        shoppingCartBtn.addActionListener(this); // This means, notify the frame
        LoginWindow.designJButton(shoppingCartBtn);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(new Insets(20, 20, 30, 20)));
        buttonPanel.setBackground(new Color(254, 235,216));



        buttonPanel.setLayout(new BorderLayout(0, 0));
        buttonPanel.add(shoppingCartBtn, BorderLayout.EAST);
        buttonPanel.setBorder(new EmptyBorder(new Insets(20,20,0,20)));

        tableModel = new ProductTableModel();
        productTable = new JTable(tableModel) {
            /**
             * @param renderer  the <code>TableCellRenderer</code> to prepare
             * @param row       the row of the cell to render, where 0 is the first row
             * @param column    the column of the cell to render,
             *                  where 0 is the first column
             * @return component with colored row
             */
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                String productId = (String) getModel().getValueAt(row, 0); // Change 4 to a variable
                int items = manager.extractAvailableItems(productId);

                if (items != -1 && items < 3) {
                    comp.setBackground(Color.RED);
                    comp.setForeground(Color.WHITE);
                } else {
                    comp.setBackground(getBackground());
                    comp.setForeground(getForeground());
                };
                return comp;
            }
        };

        //WARNING: row index is bigger than sorter's row count. Most likely this is a wrong sorter usage.
        productTable.setAutoCreateRowSorter(true); // Allows the user to sort the table

        addToCartBtn = new JButton("Add To Shopping Cart");
        addToCartBtn.addActionListener(this);


        productTable.addMouseListener(new MouseAdapter() {
            /**
             *
             * @param e the event to be processed
             */
            @Override
            public void mouseClicked(MouseEvent e) {

                int ID_COLUMN = 0;
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1) {
                    int selectedRowIndex = productTable.getSelectedRow();

                    // First column includes the product ID which is unique for each product
                    String productID = (String) productTable.getValueAt(selectedRowIndex, ID_COLUMN);

                    productDetailsPanel.removeAll();

                    JPanel detailsPanel = getProductDetailsPanel(productID);


                    // If the selected row is empty, getProductDetails() returns null
                    if (detailsPanel != null) {
                        productDetailsPanel.add(detailsPanel, BorderLayout.CENTER);

                        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));

                        addToCartBtn.setMargin(new Insets(5, 5, 5, 5));
                        LoginWindow.designJButton(addToCartBtn);

                        //buttonPanel1.setBackground(new Color(255, 255,255));
                        buttonPanel1.setBackground(new Color(254, 235,216));


                        buttonPanel1.add(addToCartBtn, BorderLayout.PAGE_END);
                        buttonPanel1.setBorder(new EmptyBorder(new Insets(20,20,30,20)));
                        productDetailsPanel.add(buttonPanel1, BorderLayout.SOUTH);


                        Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
                        productDetailsPanel.setBorder(blackLine);

                        productDetailsPanel.revalidate();
                        productDetailsPanel.repaint();
                    }
                }
            }
        });

        // Centering the values in each column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );


        for (int i = 0; i < 5; i++) {
            productTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        productTable.setRowHeight(35);
        productTable.setGridColor(Color.BLACK);

        productTable.setFont(new Font("Serif", Font.PLAIN, 13));
        header = productTable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));

        // Center header text
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        Dimension tableSize = new Dimension(900, 195);
        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setPreferredSize(tableSize);
        scrollPane.setVisible(true);

        JPanel p2 = new JPanel();

        p2.setLayout(new FlowLayout());

        p2.add(selectProduct);
        p2.add(displayChoiceComboBox);

        JPanel p1 = new JPanel();
        p2.setBackground(new Color(255, 248, 242));
        p1.setBackground(new Color(255, 248, 242));

        p1.setLayout(new FlowLayout());
        p1.add(scrollPane);
        p1.setBorder(new EmptyBorder(new Insets(30, 30,30,30)));

        JPanel combine = new JPanel();
        combine.setLayout(new BorderLayout());


        combine.add(p1, BorderLayout.CENTER);
        combine.add(p2, BorderLayout.NORTH);

        // For the combox
        JPanel p3 = new JPanel();

        p3.setLayout(new BorderLayout());
        p3.add(buttonPanel, BorderLayout.PAGE_START);
        p3.add(combine, BorderLayout.CENTER);

        p3.add(productDetailsPanel, BorderLayout.SOUTH);

        this.add(p3);
    }

    public static ProductDetailsWindow getProductsWindowInstance() {
        if (productsWindowInstance == null) {
            productsWindowInstance = new ProductDetailsWindow();
        }
        return productsWindowInstance;
    }

    public static ProductDetailsWindow getProductWindowState() {
        return productsWindowInstance;
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
            ((ProductTableModel) productTable.getModel()).updateData(filteredProducts);
            tableModel.fireTableDataChanged();
        }

        if (e.getSource() == addToCartBtn) {
            int selectedRowIndex = productTable.getSelectedRow();

            if (selectedRowIndex != -1) {

                int PRODUCT_ID_COLUMN = 0;
                String productId = (String) productTable.getValueAt(selectedRowIndex, PRODUCT_ID_COLUMN);
                shoppingCart.addToShoppingCart(productId);

                ProductTableModel model = (ProductTableModel) productTable.getModel();

                cartTableModel = new SummaryTableModel();
                cartTableModel.fireTableDataChanged();


                if (summaryWindow != null) {
                    //System.out.println(summaryWindow.getTableValue(cartTableModel));
                    summaryWindow.updateTotal(shoppingCart.getTotalPrice());

                    if (shoppingCart.getThreeItemsDiscount()) {
                        summaryWindow.updateThreeItemDiscount();
                    }

                    summaryWindow.updateFinalPrice();
                }
            }
        }

        if (e.getSource() == shoppingCartBtn) {
            if (summaryWindow == null) {

                summaryWindow = new OrderSummaryWindow();
                summaryWindow.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        summaryWindow = null; // Reset the reference when the window is closed
                    }
                });
            }
            summaryWindow.setVisible(true);
        }
    }

    public JPanel getProductDetailsPanel(String productID) {
        return manager.getDataFromID(productID);
    }
}

