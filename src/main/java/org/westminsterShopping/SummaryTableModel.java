package org.westminsterShopping;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SummaryTableModel extends AbstractTableModel {

    private final String[] COLUMN_NAMES = {"Product", "Quantity", "Price"};
    private final int MIN_ROWS = 2;
    //ShoppingCart cart = new ShoppingCart();
    List<Product> productCart = ShoppingCart.productsInCart;
    Map<String, Integer> quantity = ShoppingCart.productQuantity;


    @Override
    public int getRowCount() {
        return Math.max(productCart.size(), MIN_ROWS);
    }


    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < productCart.size()) {
            Product product = productCart.get(rowIndex);
            int productQuantity = quantity.getOrDefault(product.getProductId(), 0);

            if (product instanceof Electronics && columnIndex == 0) {
                return formatElectronicsInfo((Electronics) product);
            } else if (product instanceof Clothing && columnIndex == 0) {
                return formatClothingInfo((Clothing) product);
            } else if (columnIndex == 1) {
                return productQuantity;
            } else if (columnIndex == 2) {
                return product.getPrice() * productQuantity;
            }
        }
        return null;
    }

    private String formatElectronicsInfo(Electronics electronics) {
        return "<html>" +
                electronics.getProductId() + "<br/>" +
                electronics.getProductName() + "<br/>" +
                electronics.getBrand() + "<br/> Warranty Period " +
                electronics.getWarrantyPeriodInWeeks() +
                "</html>";    }

    private String formatClothingInfo(Clothing clothing) {
        return "<html>" +
                clothing.getProductId() + "<br/>" +
                clothing.getProductName() + "<br/>" +
                clothing.getSize() + ", " +
                clothing.getColor() +
                "</html>";
    }



    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }


    public Class getColumnClass(int col) {
        if (col == 1) {
            return Integer.class;
        } else if (col == 2) {
                return Double.class;
        } else {
            return String.class;
        }
    }


    public void updateCart(ArrayList<Product> products) {
        productCart = products;
        fireTableDataChanged(); // Notify the table that the data has changed
    }
}
