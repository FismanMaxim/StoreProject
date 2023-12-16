import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    private ShoppingCart cart;

    @BeforeEach
    void createCart() {
        cart = new ShoppingCart();
    }

    @Test
    void addProductsToCart() {
        final int water = 2;
        final int flour = 3;

        cart.addToCart(ProductType.WATER, water);
        cart.addToCart(ProductType.FLOUR, flour);

        assertEquals(2, cart.getCartItem(ProductType.WATER));
        assertEquals(3, cart.getCartItem(ProductType.FLOUR));
        assertEquals(0, cart.getCartItem(ProductType.BREAD));
    }

    @Test
    void removeProductsFromCart() {
        final int addAmount = 7;
        final int removeAmount = 4;

        cart.addToCart(ProductType.WATER, addAmount);
        cart.removeFromCart(ProductType.WATER,  removeAmount);

        assertEquals(addAmount - removeAmount, cart.getCartItem(ProductType.WATER));
    }

    @Test
    void cannotRemoveMoreThanExists() {
        assertThrows(IllegalArgumentException.class, () -> cart.removeFromCart(ProductType.WATER,  1));
    }

    @Test
    void testGetCartItems() {
        final int water = 2;
        final int flour = 3;

        cart.addToCart(ProductType.WATER, water);
        cart.addToCart(ProductType.FLOUR, flour);

        var contents = cart.getCartItems();

        assertEquals(Map.of(ProductType.WATER,  water, ProductType.FLOUR,  flour), contents);
    }
}