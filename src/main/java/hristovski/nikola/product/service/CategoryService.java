package hristovski.nikola.product.service;


import hristovski.nikola.product.exception.CategoryNotFoundException;
import hristovski.nikola.product.model.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);
    Category editCategory(Category category);
    void deleteCategory(Long categoryId);
    Category getByCategoryName(String categoryName) throws CategoryNotFoundException;

    List<Category> getAll();
}
