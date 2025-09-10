package main.java;

import main.java.exceptions.OutOfStockException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductInventory {
    private final List<String> products = new ArrayList<>();

    // Add products to list
    public void addProduct(String product) {
        products.add(product);
    }

    // Remove products or throw exception
    public void removeProduct(String name) throws OutOfStockException {
        if (!products.remove(name)) {
            throw new OutOfStockException("Товар " + name + " закінчився! Спробуйте інший!");
        }
    }

    // Sort products
    public void sortProducts() {
        Collections.sort(products);
    }

    // Get products
    public List<String> getProducts() {
        return new ArrayList<>(products);
    }
}