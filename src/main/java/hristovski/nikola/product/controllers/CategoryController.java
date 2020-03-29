package hristovski.nikola.product.controllers;

import hristovski.nikola.product.exception.CategoryNotFoundException;
import hristovski.nikola.product.model.category.Category;
import hristovski.nikola.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("{category}")
    public ResponseEntity<Category> getCategory(@PathVariable String category) throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.getByCategoryName(category));
    }

}
