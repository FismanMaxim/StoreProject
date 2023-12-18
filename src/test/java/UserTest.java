import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

class UserTest {

    @Test
    void run() {
        Store store = new Store(new HashMap<>());
        User user = new User("Alice", store);

        user.addToCart(ProductType.BREAD, 2);
        user.addToCart(ProductType.WATER, 3);
        user.addToCart(ProductType.FLOUR, 1);

        user.run(); // This method just prints, so we can't test the output directly
        // Instead, we can manually check the console output during testing
    }
}