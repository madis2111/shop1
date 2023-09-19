package objects;
import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<Integer, Product> productsMap;
    private int counter;

    public Store() {
        productsMap = new HashMap<>();
        counter = 1;
    }

    public void addProducts(Product... products) {
        for (Product product : products) {
            productsMap.put(counter, product);
            counter++;
        }
    }

    public boolean hasProduct(Product product) {
        return productsMap.values().contains(product);
    }

    public void printProducts() {
        int i = 1;
        for (Product product : productsMap.values()) {
            System.out.println(i + ". " + product.getName());
            i++;
        }
    }

    public Product getProductById(int id) {
        return productsMap.get(id);
    }
}