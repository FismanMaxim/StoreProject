import java.util.HashMap;
import java.util.Map;

class ShoppingCart {
    private final Map<ProductType, Integer> cart;

    public ShoppingCart() {
        this.cart = new HashMap<>();
    }

    public void addToCart(ProductType item, int quantity) {
        int currentQuantity = cart.getOrDefault(item, 0);
        cart.put(item, currentQuantity + quantity);
        System.out.println(quantity + " " + item + "(s) added to the cart.");
    }

    public void removeFromCart(ProductType item, int quantity) {
        int currentQuantity = cart.getOrDefault(item, 0);
        if (currentQuantity >= quantity) {
            cart.put(item, currentQuantity - quantity);
            System.out.println(quantity + " " + item + "(s) removed from the cart.");
        } else {
            throw new IllegalArgumentException("Not enough " + item + " in the cart.");
        }
    }

    public int getCartItem(ProductType item) {
        return cart.getOrDefault(item, 0);
    }

    public Map<ProductType, Integer> getCartItems() {
        return new HashMap<>(cart);
    }
}