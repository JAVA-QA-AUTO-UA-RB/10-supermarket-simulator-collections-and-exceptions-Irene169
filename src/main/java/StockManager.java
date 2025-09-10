package main.java;

import main.java.exceptions.OutOfStockException;

import java.util.Map;
import java.util.HashMap;

public class StockManager {
    private final Map<String, Integer> stock = new HashMap<>();

    // Add stock
    public void addStock(String product, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Кількість не може бути від'ємною дл товару: " + product);
        }
        stock.put(product, stock.getOrDefault(product, 0) + quantity);
    }

    // Reduce stock
    public void reduceStock(String product) throws OutOfStockException {
        if (!stock.containsKey(product)) {
            throw new OutOfStockException("Товар " + product + " відсутній!");
        }
        int current = stock.get(product);
        if (current <= 0) {
            throw new OutOfStockException("Товар " + product + " закінчився!");
        }
        stock.put(product, current - 1);
    }

    // Return stocks list
    public Map<String, Integer> getStock() {
        return new HashMap<>(stock);
    }
}