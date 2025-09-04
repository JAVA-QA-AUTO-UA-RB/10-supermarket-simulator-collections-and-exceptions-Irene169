package main.java;

import java.util.HashSet;
import java.util.Set;

public class CategoryManager {
    private final Set<String> categories = new HashSet<>();

    // Adding category, exception if the category exists
    public void addCategory(String category) throws DuplicateCategoryException {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Назва категорії не може бути порожньою!");
        }
        if (!categories.add(category)) {
            throw new DuplicateCategoryException("Категорія " + category + " вже існує!");
        }
    }

    //Return set categories
    public Set<String> getCategories() {
        return new HashSet<>(categories);
    }
}