import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.westminsterShopping.ConsoleApplication;
import org.westminsterShopping.Electronics;
import org.westminsterShopping.Product;
import org.westminsterShopping.WestminsterShoppingManager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class AddProductTest {
    private InputStream inputStream;
    private WestminsterShoppingManager manager;
    Product product1;
    Product product2;

    @BeforeEach
    void createInstances() {
        manager = new WestminsterShoppingManager();
        product1 = new Electronics("E002", "Laptop", 3, 20, "MVP", 12);
        product2 = new Electronics("K002", "IPhone", 5, 50, "Apple", 10);
    }

    @AfterEach
    void tearDown() {
        // Restore the original System.in to avoid interference with other tests
        System.setIn(inputStream);
    }


    @Test
    void CorrectProductInputTest() {

        String testInput = "Electronic\nK002\nIPhone\n5\n50\nApple\n10";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
        Scanner scanner = new Scanner(inputStream);
        ConsoleApplication.input = scanner;

        Product product = ConsoleApplication.getProductInput();

        // Assert the result
        assertEquals("K002", product.getProductId());
        assertEquals("IPhone", product.getProductName());
        assertEquals(5, product.getAvailableItems());
        assertEquals(50, product.getPrice());
        assertEquals("Apple", ((Electronics) product).getBrand());
        assertEquals(10, ((Electronics) product).getWarrantyPeriodInWeeks());

    }



    @Test
    void InCorrectProductCategoryTest(){

        String testInput = "Fruits\nK002\nIPhone\n5\n50\nApple\n10";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
        Scanner scanner = new Scanner(inputStream);
        ConsoleApplication.input = scanner;

        Product product = ConsoleApplication.getProductInput();

        // Assert the result
        assertEquals(null, product);

        scanner.close();
    }



    @Test
    void duplicateProductIdTest() {
        manager.addProduct(product1);

        assertFalse(ConsoleApplication.checkUniqueId("E002"));
    }

}
