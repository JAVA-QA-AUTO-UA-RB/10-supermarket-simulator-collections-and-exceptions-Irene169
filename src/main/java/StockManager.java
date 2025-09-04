package main.java;

import java.util.Map;
import java.util.HashMap;

public class StockManager {
    private final Map<String, Integer> stock = new HashMap<>();

    // Add stock
    public void addStock(String product, int quantity) {
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("Назва товару не може бути порожньою!");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Кількість не може бути від'ємною для товару: " + product);
        }
        stock.put(product, stock.getOrDefault(product, 0) + quantity);
    }

    // Reduce stock
    public void reduceStock(String product) throws OutOfStockException {
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("Назва товару не може бути порожньою!");
        }
        Integer current = stock.get(product);
        if (current == null || current <= 0) {
            throw new OutOfStockException("Товар " + product + " закінчився! Спробуйте інший!");
        }
        stock.put(product, current - 1);
    }

    // Return stocks list
    public Map<String, Integer> getStock() {
        return new HashMap<>(stock);
    }
}