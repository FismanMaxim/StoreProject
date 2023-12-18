import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<ProductType, Integer> initialStock = new HashMap<>();
        initialStock.put(ProductType.BREAD, 100);
        initialStock.put(ProductType.WATER, 400);
        initialStock.put(ProductType.FLOUR, 50);

        Store store = new Store(initialStock);

        User user1 = new User("User 1", store);
        User user2 = new User("User 2", store);

        Thread thread1 = new Thread(user1);
        Thread thread2 = new Thread(user2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("After shopping:");
        store.displayStock();
    }
}