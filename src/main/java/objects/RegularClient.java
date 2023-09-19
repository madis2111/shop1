package objects;
import interfaces.Rater;

public class RegularClient extends User implements Rater {

    public RegularClient(String name, int money, Store store) {
        super(name, money, store);
    }

    @Override
    public void rate(int id, int rating) {
        Product product = getStore().getProductById(id);
        product.addRating(rating);
    }
}