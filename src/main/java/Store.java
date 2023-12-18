import java.lang.reflect.GenericDeclaration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Store {
    private final Map<ProductType, Integer> stock;

    private final Lock lock;

    public Store(Map<ProductType, Integer> initialStock) {
        this.stock = new HashMap<>(initialStock);
        this.lock = new ReentrantLock();
    }

    public synchronized void addToStorage(ProductType item, int quantity) {
        int currentStock = stock.getOrDefault(item, 0);
        stock.put(item, currentStock + quantity);
        System.out.println(quantity + " " + item + "(s) removed from the cart.");
    }

    public synchronized void removeFromStorage(ProductType item, int quantity) {
        int currentStock = stock.getOrDefault(item, 0);
        if (currentStock >= quantity) {
            stock.put(item, currentStock - quantity);
            System.out.println(quantity + " " + item + "(s) added to the cart.");
        } else {
            throw new IllegalArgumentException("Not enough stock for " + item + ".");
        }
    }


    public int getAmountOf(ProductType item) {
        return stock.getOrDefault(item, 0);
    }

    public void displayStock() {
        lock.lock();
        try {
            System.out.println("Current stock:");
            for (Map.Entry<ProductType, Integer> entry : stock.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean buy(ShoppingCart shoppingCart) {
        lock.lock();
        try {
            Map<ProductType, Integer> cartItems = shoppingCart.getCartItems();
            for (Map.Entry<ProductType, Integer> entry : cartItems.entrySet()) {
                ProductType item = entry.getKey();
                int quantityInCart = entry.getValue();

                int currentStock = stock.getOrDefault(item, 0);

                if (currentStock < quantityInCart) {
                    System.out.println("Not enough stock for " + item + ". Purchase aborted.");
                    return true; // Если не хватает запасов, прекратить операцию
                    // покупки
                }
            }

            System.out.println("Purchase successful. Items bought:");
            for (Map.Entry<ProductType, Integer> entry : cartItems.entrySet()) {
                stock.put(entry.getKey(),
                        stock.getOrDefault(entry.getKey(), 0) - entry.getValue());
                ProductType item = entry.getKey();
                int quantityInCart = entry.getValue();
                System.out.println(quantityInCart + " " + item);
            }

            shoppingCart.getCartItems().clear(); // Очищаем корзину после успешной покупки
            return false;
        } finally {
            lock.unlock();
        }
    }
}
