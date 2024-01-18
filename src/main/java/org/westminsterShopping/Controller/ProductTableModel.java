package org.westminsterShopping.Controller;

import org.westminsterShopping.Model.Clothing;
import org.westminsterShopping.Model.Electronics;
import org.westminsterShopping.Model.Product;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductTableModel extends AbstractTableModel {

    private final String[] COLUMN_NAMES = {"Product ID", "Name", "Category", "Price", "Info"};
    private final int MIN_ROWS = 5;

    private List<Product> productsToDisplay = WestminsterShoppingManager.productsList;

    @Override
    public int getRowCount() {
        // Ensure the table shows a minimum number of rows
        return Math.max(productsToDisplay.size(), MIN_ROWS);
    }


    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;

        if (rowIndex < productsToDisplay.size()) {

            if (columnIndex == 0) {
                temp = productsToDisplay.get(rowIndex).getProductId();
            } else if (columnIndex == 1) {
                temp = productsToDisplay.get(rowIndex).getProductName();
            } else if (columnIndex == 2) {
                temp = productsToDisplay.get(rowIndex).getCategory();
            } else if (columnIndex == 3) {
                temp = (Double) productsToDisplay.get(rowIndex).getPrice();
            } else if (columnIndex == 4) {
                if (productsToDisplay.get(rowIndex) instanceof Electronics) {
                    temp = ((Electronics) productsToDisplay.get(rowIndex)).getBrand() + ", " + ((Electronics) productsToDisplay.get(rowIndex)).getWarrantyPeriodInWeeks() + " weeks warranty";
                } else if (productsToDisplay.get(rowIndex) instanceof Clothing) {
                    temp = ((Clothing) productsToDisplay.get(rowIndex)).getSize() + ", " + ((Clothing) productsToDisplay.get(rowIndex)).getColor();
                }
            }
        }
        return temp;
    }


    public void updateData(ArrayList<Product> products) {
        productsToDisplay = products;
        fireTableDataChanged(); // Notify the table that the data has changed
    }


    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }

    public Class getColumnClass(int col) {
        if (col == 3) {
            return Double.class;
        } else {
            return String.class;
        }
    }
}