import java.util.HashMap;
import java.util.Map;

class Store {
    private final Map<ProductType, Integer> stock;

    public Store(Map<ProductType, Integer> initialStock) {
        this.stock = new HashMap<>(initialStock);
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
}
