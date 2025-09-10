package main.java;

import main.java.exceptions.DuplicateCategoryException;

import java.util.HashSet;
import java.util.Set;

public class CategoryManager {
    private final Set<String> categories = new HashSet<>();


    // Adding category, exception if the category already exists
    public void addCategory(String category) {
        if (!categories.add(category)) {
            throw new DuplicateCategoryException("Категорія " + category + " вже існує!");
        }
    }

    // return set categories
    public Set<String> getCategories() {
        return new HashSet<>(categories);
    }
}