package main.java;

import main.java.exceptions.NoSuchProductException;

import java.util.Map;
import java.util.HashMap;

public class PriceCatalog {
    private final Map<String, Double> prices = new HashMap<>();

    // Adding price
    public void addPrice(String product, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Ціна не може бути від'ємною для товара: " + product);
        }
        prices.put(product, price);
    }

    // Get price
    public double getPrice(String product) throws NoSuchProductException {
        if (!prices.containsKey(product)) {
            throw new NoSuchProductException("Товар " + product + " відсутній у каталозі");
        }
        return prices.get(product);
    }

    // Receiving copy of catalog with prices
    public Map<String, Double> getPrices() {
        return new HashMap<>(prices);
    }
}