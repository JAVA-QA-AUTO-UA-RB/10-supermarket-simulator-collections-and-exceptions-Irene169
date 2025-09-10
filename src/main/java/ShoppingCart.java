import exceptions.CartLimitExceededException;
import exceptions.InsufficientFundsException;
import exceptions.NoSuchProductException;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<String> cart = new ArrayList<>();
    private final int CART_LIMIT = 10;
    private double finalSubtotal;
    private double finalTax;
    private double finalDiscount;
    private double finalAmount;

    // Adding product to cart. throw an exception if the cart list is reached
    public void addToCart(String product) throws CartLimitExceededException {
        if (cart.size() >= CART_LIMIT) {
            throw new CartLimitExceededException("Кошик переповнений! Ліміт: " + CART_LIMIT + " товарів");
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

        finalSubtotal = total;
        finalTax = total * 0.1;
        finalDiscount = Math.random() * 0.1 + 0.05;

        if (hasMilk) finalDiscount += 0.05;
        finalAmount = (finalSubtotal + finalTax) * (1 - finalDiscount);
        if (payment < finalAmount) {
            throw new InsufficientFundsException("Недостатньо коштів! Потрібно: " + finalAmount + " маємо: " + payment);
        }
        return finalAmount;
    }

    public List<String> getCart() {
        return new ArrayList<>(cart);

        // Print receipt
    }

    public void printReceipt(PriceCatalog catalog) {
        System.out.println("-------- ЧЕК --------");
        for (String product : cart) {
            double price = catalog.getPrice(product);
            System.out.println(product + ": " + String.format("%.2f", price) + " грн");
        }
        System.out.println("---------------------");
        System.out.println("Підсумок: " + String.format("%.2f", finalSubtotal) + " грн");
        System.out.println("Податок (10%): " + String.format("%.2f", finalTax) + " грн");
        System.out.println("Знижка: " + String.format("%.2f", finalDiscount * 100) + "%");
        System.out.println("До сплати: " + String.format("%.2f", finalAmount) + " грн");
        System.out.println("---------------------");
    }
}