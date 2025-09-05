import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private final List<String> cart = new ArrayList<>();
    private final int CART_LIMIT = 10;

    // Adding product to cart, thrown an exception if the cart limit is reached
    public void addToCart(String product) throws CartLimitExceededException {
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("Назва товару не може бути порожньою!");
        }
        if (cart.size() >= CART_LIMIT) {
            throw new CartLimitExceededException("Кошик заповнений! Ліміт: " + CART_LIMIT + " товарів.");
        }
        cart.add(product);
    }

    // Checkout: calculate with tax and discount
    public double checkout(PriceCatalog catalog, double payment) throws NoSuchProductException, InsufficientFundsException {
        double total = 0.0;
        boolean hasMilk = false;

        // Calculate total price of products
        for (String product : cart) {
            double price = catalog.getPrice(product);
            total += price;
            if (product.equalsIgnoreCase("Milk")) {
                hasMilk = true;
            }
        }
// Calculate as 10% of the total
        double tax = total * 0.1;
        // Generate random discount between 5 and 15%
        double discount = Math.random() * 0.1 + 0.05;
        // Extra 5% discount if the cart contains milk
        if (hasMilk) discount += 0.05;
// Final amount
        double finalAmount = (total + tax) * (1 - discount);
        // Check if payment is sufficient
        if (payment < finalAmount) {
            throw new InsufficientFundsException("Недостатньо коштів! Потрібно: " + finalAmount + " маємо: " + payment);
        }
        return finalAmount;
    }

    // Return a copy of the cart
    public List<String> getCart() {
        return new ArrayList<>(cart);
    }

    // Print receipt
    public void printReceipt(PriceCatalog catalog) {
        double total = 0.0;
        boolean hasMilk = false;
        System.out.println("-----ЧЕК-----");
        for (String product : cart) {
            double price = catalog.getPrice(product);
            System.out.println(product + " : " + price + " $");
            total += price;
            if (product.equalsIgnoreCase("Milk")) hasMilk = true;
        }
        double tax = total * 0.1;
        double discount = Math.random() * 0.1 + 0.05;
        if (hasMilk) discount += 0.05;
        double finalAmount = (total + tax) * (1 - discount);
        System.out.println("Податок: " + tax + " $");
        System.out.println("Знижка: " + (discount * 100) + "%");
        System.out.println("До оплати: " + finalAmount + " $");
        System.out.println("---------");
    }
}