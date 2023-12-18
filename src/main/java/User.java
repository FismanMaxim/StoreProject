class User implements Runnable {
    private String name;
    private Store store;
    private ShoppingCart cart;

    public User(String name, Store store) {
        this.name = name;
        this.store = store;
        this.cart = new ShoppingCart();
    }

    public void addToCart(ProductType item, int quantity) {
        cart.addToCart(item, quantity);
    }

    public void removeFromCart(ProductType item, int quantity) {
        cart.removeFromCart(item, quantity);
    }

    public void run() {
        System.out.println(name + " started shopping.");
        addToCart(ProductType.BREAD, 1);
        addToCart(ProductType.WATER, 2);
        store.addToCart(ProductType.BREAD, 2);
        store.addToCart(ProductType.WATER, 5);
        store.addToCart(ProductType.FLOUR, 1);
        store.displayStock();
    }
}