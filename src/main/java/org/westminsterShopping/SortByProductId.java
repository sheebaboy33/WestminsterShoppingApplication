package org.westminsterShopping;

import java.util.Comparator;

public class SortByProductId implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getProductId().compareToIgnoreCase(o2.getProductId());
    }
}
