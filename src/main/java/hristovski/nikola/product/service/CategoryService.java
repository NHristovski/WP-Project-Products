package hristovski.nikola.product.service;


import hristovski.nikola.product.exception.CategoryNotFoundException;
import hristovski.nikola.product.model.category.AddCategoryRequest;
import hristovski.nikola.product.model.category.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(AddCategoryRequest category);
    Category editCategory(Category category);
    void deleteCategory(Long categoryId);
    Category getByCategoryName(String categoryName) throws CategoryNotFoundException;

    List<Category> getAll();
}
