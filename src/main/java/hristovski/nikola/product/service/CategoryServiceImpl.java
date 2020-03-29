package hristovski.nikola.product.service;

import hristovski.nikola.product.exception.CategoryNotFoundException;
import hristovski.nikola.product.model.category.AddCategoryRequest;
import hristovski.nikola.product.model.category.Category;
import hristovski.nikola.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    @Override
    public Category addCategory(AddCategoryRequest request) {

        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        category.setId(null);
        category.setProducts(new ArrayList<>());

        return categoryRepository.save(category);
    }

    @Override
    public Category editCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {

        Category one = categoryRepository.getOne(categoryId);
        log.info("Removing cat: " + one.getCategoryName());

        productService.removeProductsFromCategory(one);

        log.info("$$$ Now delete the category");
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category getByCategoryName(String categoryName) throws CategoryNotFoundException {
        try {
            return categoryRepository.getByCategoryName(categoryName);
        }catch (Exception ex){
            log.error("Failed to get category with name {}",categoryName,ex);
        }

        throw new CategoryNotFoundException(categoryName);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
