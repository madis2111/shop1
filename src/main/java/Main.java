import interfaces.Rater;
import objects.Product;
import objects.RegularClient;
import objects.Store;
import objects.User;

import java.util.Scanner;

public class Main {
    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {

        Store store = new Store();
        Product tomatoes = new Product("Помидоры", 150, 5);
        Product cucumbers = new Product("Огурцы", 100, 4);
        Product potatoes = new Product("Картошка", 50, 5);
        store.addProducts(tomatoes, cucumbers, potatoes);

        User me = new User("Я", 1000, store);
        User regularClient = new RegularClient("Регулярный клиент", 2000, store);

        System.out.println("Добро пожаловать!");

        while (true) {
            System.out.println("Что вы хотите сделать? Введите число");
            System.out.println("1. Что-нибудь купить");
            System.out.println("2. Оставить отзыв о товаре (если вы постоянный клиент)");
            System.out.println("3. Выйти");

            int input = s.nextInt();

            if (input == 1) {
                startShopping(me, store);
                startShopping(regularClient, store);
            } else if (input == 2) {
                leaveRating((Rater) regularClient, store);
            } else if (input == 3) {
                break;
            }
        }
        System.out.println();
        System.out.println("До скорых встреч!");
    }

    private static void startShopping(User user, Store store) {

        System.out.println();
        System.out.println(user.getName() + " взял новую корзину.");
        System.out.println("Ваш баланс: " + user.getMoney());

        do {
            // бесконечный цикл покупок внутри метода шоп
            // метод шоп прекращается как только введён 0
            shop(user, store);
            // цикл продолжается, пока buy() не вернёт true, т.е. если денег достаточно
        } while (!user.buy());
    }

    private static void shop(User me, Store store) {
        vneshniyTsikl:
        while (true) {
            System.out.println("Что вы хотите сделать? Введите число");
            System.out.println("1. Добавить в корзину");
            System.out.println("2. Удалить из корзины");
            System.out.println("3. Оплатить");

            int input = s.nextInt();

            if (input == 1) {
                System.out.println("Список продуктов:");
                store.printProducts();
                System.out.println("Что вы хотите купить? Введите id продукта");
                System.out.println("Или введите 0 для оплаты заказа");
                int id = s.nextInt();       // todo invalid input exception

                if (id == 0) {
                    break;
                }
                System.out.println("Введите количество");
                int amount = s.nextInt();

                Product productToAdd = store.getProductById(id);

                if (me.addToCart(productToAdd, amount)) {
                    System.out.println("Продукт \"" + productToAdd.getName() + "\" добавлен в корзину в количестве " + amount);
                    System.out.println("В вашей корзине продуктов на " + me.getCartSum() + " руб.");
                    System.out.println();
                } else {
                    System.out.println("Такого продукта в магазине нет");
                    System.out.println();
                }
            } else if (input == 2) {
                System.out.println("Список продуктов:");
                store.printProducts();
                System.out.println("Что вы хотите удалить из корзины? Введите id продукта");
                System.out.println("Или введите 0 для оплаты заказа");
                int id = s.nextInt();

                if (id == 0) {
                    break;
                }

                System.out.println("Введите количество");
                int amount = s.nextInt();

                Product productToRemove = store.getProductById(id);

                if (me.removeFromCart(productToRemove, amount)) {
                    System.out.println("Продукт \"" + productToRemove.getName() + "\" удалён из корзины в количестве " + amount);
                    System.out.println("В вашей корзине продуктов на " + me.getCartSum() + " руб.");
                    System.out.println();
                } else {
                    System.out.println("Такого продукта в корзине нет");
                }

            } else if (input == 3) {
                break;
            }
        }
    }

    private static void leaveRating(Rater rater, Store store) {
        System.out.println("Выберите продукт, чтобы оставить отзыв:");
        store.printProducts();
        int id = s.nextInt();

        System.out.println("Введите оценку из 5 баллов");

        int rating = s.nextInt();

        rater.rate(id, rating);
        System.out.println("Вы оценили " + store.getProductById(id).getName() + " на " + rating);
        System.out.println("Теперь оценка продукта " + store.getProductById(id).getName() +
                " - " + Math.round(store.getProductById(id).getRating()));
        System.out.println("Спасибо за ваш отзыв!");
        System.out.println();
    }
}