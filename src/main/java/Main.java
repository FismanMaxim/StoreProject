import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
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
        saveStoreStateToFile(store, "store_state.txt");
    }

    private static void saveStoreStateToFile(Store store, String filePath) {
        try {
            Path path = Path.of(filePath);
            Files.write(path, ("Final Store State:\n" + store.toString()).getBytes(), StandardOpenOption.CREATE);
            System.out.println("Store state saved to: " + path.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}