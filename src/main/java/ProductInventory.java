import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductInventory {
    private final List<String> products = new ArrayList<>();

    // Add products to list
    public void addProduct(String product) {
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("Назва товару не може бути порожньою!");
        }
        products.add(product);
    }

    // Sort products
    public void sortProducts() {
        Collections.sort(products);
    }

    // Get products
    public List<String> getProducts() {
        return new ArrayList<>(products);
    }

    // Remove products
    public void removeProduct(String name) throws OutOfStockException {
        if (!products.remove(name)) {
            throw new OutOfStockException("Товар " + name + " закінчився! Спробуйте інший!");
        }
    }
}