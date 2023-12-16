import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StoreTest {
    private Store store;

    @BeforeEach
    void setStore() {
        store = new Store(Map.of(ProductType.WATER, 100, ProductType.BREAD, 200, ProductType.FLOUR, 300));
    }

    @Test
    void addToStorage() {
        store.addToStorage(ProductType.WATER, 10);

        assertEquals(110, store.getAmountOf(ProductType.WATER));
    }

    @Test
    void removeFromStorage() {
        store.removeFromStorage(ProductType.WATER, 10);

        assertEquals(90, store.getAmountOf(ProductType.WATER));
    }

    @Test
    void cannotRemoveMoreThanExists() {
        assertThrows(IllegalArgumentException.class, () -> store.removeFromStorage(ProductType.WATER, 1000));
    }
}