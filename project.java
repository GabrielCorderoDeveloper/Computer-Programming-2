// Code explained at the bottom
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

// ---------- Product (base) + subclasses ----------
abstract class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    public abstract String getCategory();
}

class Electronics extends Product {
    public Electronics(String name, double price) {
        super(name, price);
    }
    public String getCategory() { return "Electronics"; }
}

class Clothing extends Product {
    public Clothing(String name, double price) {
        super(name, price);
    }
    public String getCategory() { return "Clothing"; }
}

class Grocery extends Product {
    public Grocery(String name, double price) {
        super(name, price);
    }
    public String getCategory() { return "Grocery"; }
}

// ---------- User (base) + subclasses ----------
abstract class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() { return username; }
}

class Customer extends User {
    public Customer(String username) {
        super(username);
    }
}

class Admin extends User {
    public Admin(String username) {
        super(username);
    }
}

// ---------- Order (cart + totals) ----------
class Order {
    private List<Product> items = new ArrayList<>();

    public void add(Product p) {
        items.add(p);
    }

    public double subtotal() {
        return items.stream().mapToDouble(Product::getPrice).sum();
    }

    public double totalWith(Function<Double, Double> taxCalculator,
                            Function<Double, Double> shippingCalculator) {
        double sub = subtotal();
        double taxAmount = taxCalculator.apply(sub);
        double ship = shippingCalculator.apply(sub);
        return sub + taxAmount + ship;
    }
}

// ---------- Main ----------
public class EcommerceApp {
    public static void main(String[] args) {

        // Product listings
        List<Product> products = Arrays.asList(
                new Electronics("Laptop", 1200),
                new Electronics("Phone", 900),
                new Clothing("Jacket", 150),
                new Grocery("Milk", 4)
        );

        // Users
        Customer customer = new Customer("gabriel");
        Admin admin = new Admin("admin1");

        // Order
        Order order = new Order();

        // Lambda filter
        Predicate<Product> isElectronics =
                p -> p.getCategory().equals("Electronics");

        products.stream()
                .filter(isElectronics)
                .forEach(order::add);

        // Sort by price
        List<Product> sortedByPrice = products.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());

        // Lambdas for tax and shipping
        Function<Double, Double> tax = sub -> sub * 0.08;
        Function<Double, Double> shipping = sub -> (sub >= 1000) ? 0.0 : 25.0;

        System.out.println("Customer: " + customer.getUsername());
        System.out.println("Admin: " + admin.getUsername());

        System.out.println("\nSorted product list (low to high):");
        for (Product p : sortedByPrice) {
            System.out.println(p.getCategory() + " - " +
                               p.getName() + " - $" +
                               p.getPrice());
        }

        System.out.println("\nCart subtotal: $" + order.subtotal());
        System.out.println("Final total (subtotal + tax + shipping): $" +
                           order.totalWith(tax, shipping));
    }
}

/*
In this project I created a simple e-commerce prototype using Java. I started by creating a Product abstract class that stores the name and price of a product. From this base class I created Electronics, Clothing, and Grocery. These subclasses inherit from Product and each one overrides the getCategory() method. This allows different product types to share the same structure while still being identified as different categories.

I also created a User abstract class with two subclasses: Customer and Admin. This was done to show inheritance for different types of users, even though they share the same basic information.

The Order class represents a shopping cart. It stores a list of products and calculates the subtotal using Java streams. The total is calculated by passing lambda expressions that apply tax and shipping. This makes the calculation flexible and shows the use of functional programming.

Lambda expressions are used to filter products by category, calculate tax, and calculate shipping fees. Streams are used to filter products, sort them by price, and sum product prices. This makes the code cleaner and avoids using loops.

In the main method I created sample products, users, and an order. I filtered products, added them to the cart, sorted products by price, and printed the final total. This shows how all the classes work together in a simple e-commerce flow.
*/
