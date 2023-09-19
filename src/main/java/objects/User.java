package objects;
import interfaces.Buyer;
import interfaces.Shopper;
import java.util.HashMap;

public class User implements Buyer, Shopper {
    private String name;
    private HashMap<Product, Integer> cart;
    private Store store;
    private int cartSum;
    private int money;

    public User(String name, int money, Store store) {
        this.name = name;
        cart = new HashMap<>();
        this.money = money;
        this.store = store;
    }

    @Override
    public boolean addToCart(Product product, int amount) {
        if (store.hasProduct(product)) {
            if (!cart.containsKey(product)) {
                cart.put(product, amount);
            } else {
                cart.put(product, cart.get(product) + amount);
            }
            cartSum += amount * product.getPrice();
            return true;
        }
        return false;
    }

    @Override
    public boolean removeFromCart(Product product, int amountToDelete) {
        if (cart.containsKey(product)) {
            if (cart.get(product) > amountToDelete) {
                cart.put(product, cart.get(product) - amountToDelete);
                cartSum -= amountToDelete * product.getPrice();
            } else {
                cartSum -= cart.get(product) * product.getPrice();
                cart.remove(product);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean buy() {
        int total = 0;
        for (Product product : cart.keySet()) {
            total += product.getPrice() * cart.get(product);
        }

        if (money >= total) {
            money -= total;
            System.out.println();
            System.out.println("Вы приобрели товары на сумму " + total + " рублей");
            System.out.println("Ваш остаток: " + money);
            return true;
        }

        System.out.println();
        System.out.println("У вас недостаточно денег чтобы совершить эту покупку");
        System.out.println("Удалите некоторые продукты из вашей корзины");
        System.out.println();

        return false;
    }

    public int getCartSum() {
        return cartSum;
    }

    public int getMoney() {
        return money;
    }

    public Store getStore() {
        return store;
    }

    public String getName() {
        return name;
    }
}