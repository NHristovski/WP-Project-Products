package hristovski.nikola.product.service;

import hristovski.nikola.product.exception.CategoryNotFoundException;
import hristovski.nikola.product.model.Category;
import hristovski.nikola.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category editCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
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
