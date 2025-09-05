import java.util.Map;
import java.util.HashMap;

public class PriceCatalog {
    private final Map<String, Double> prices = new HashMap<>();

    // Adding price
    public void addPrice(String product, double price) {
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("Назва категорії не може бути порожньою!");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Ціна не може бути від'ємною для товару: " + product);
        }
        prices.put(product, price);
    }

    // Get price
    public double getPrice(String product) throws NoSuchProductException {
        Double price = prices.get(product);
        if (price == null) {
            throw new NoSuchProductException("Товар " + product + " відсутній у каталозі цін!");
        }
        return price;
    }

    // Receiving copy of catalog with prices
    public Map<String, Double> getPrices() {
        return new HashMap<>(prices);
    }
}