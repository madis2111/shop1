package objects;
import java.util.Objects;

public class Product {

    private String name;
    private int price;
    private double rating;
    private int numberOfRatings;

    public Product(String name, int price, int rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        numberOfRatings = 1;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public void addRating(int rating) {
        this.rating = (this.rating * numberOfRatings + rating) / (numberOfRatings+1);
        numberOfRatings++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}