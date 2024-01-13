import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.westminsterShopping.Model.Electronics;
import org.westminsterShopping.Model.Product;
import org.westminsterShopping.Controller.WestminsterShoppingManager;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteProductTest {

    private WestminsterShoppingManager manager;
    Product product1;
    Product product2;

    @BeforeEach
    void createInstances() {
        manager = new WestminsterShoppingManager();
        product1 = new Electronics("E002", "Laptop", 3, 20, "MVP", 12);
        product2 = new Electronics("K002", "IPhone", 5, 50, "Apple", 10);
    }


    @Test
    void deleteProductTest() {

        WestminsterShoppingManager.productsList.add(product1);
        WestminsterShoppingManager.productsList.add(product2);

        String result = manager.deleteProduct(product1.getProductId());

        assertEquals("Details of the product removed \n" + product1 +
                "\nRemaining product count is " + WestminsterShoppingManager.productCount, result);
    }


    @Test
    void updateProductListTest() {

        WestminsterShoppingManager.productsList.add(product1);
        WestminsterShoppingManager.productsList.add(product2);

        manager.deleteProduct(product1.getProductId());

        assertEquals(1, WestminsterShoppingManager.productsList.size());
    }


    @Test
    void invalidProductIdTest() {


        WestminsterShoppingManager.productsList.add(product1);
        WestminsterShoppingManager.productsList.add(product2);

        String result = manager.deleteProduct("wrongId");

        assertEquals("Invalid Product Id. Please check & try again.", result);
    }

    @Test
    void extractAvailableItemsTest() {
        manager.extractAvailableItems("E002");

        assertEquals(3, product1.getAvailableItems());
    }
}
