package org.westminsterShopping;

import java.util.ArrayList;

import static org.westminsterShopping.WestminsterShoppingManager.productsList;

public class ShoppingCart {
    ArrayList<Product> productCart = new ArrayList<>();


    public void addToShoppingCart(String productId) {
        for (Product product : productsList) {
            if (product.getProductId().equalsIgnoreCase(productId)) {
                productCart.add(product);
                displayShoppingCart();
                return ;
            }
        }
    }


    public void displayShoppingCart () {
        for (Product product : productCart) {
            System.out.println(product);
        }
    }

}
