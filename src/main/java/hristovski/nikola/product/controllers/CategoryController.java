package hristovski.nikola.product.controllers;

import hristovski.nikola.product.exception.CategoryNotFoundException;
import hristovski.nikola.product.model.Category;
import hristovski.nikola.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    //TODO add controller advice

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("{category}")
    public ResponseEntity<Category> getCategory(@PathVariable String category) throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.getByCategoryName(category));
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category)  {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @DeleteMapping
    public ResponseEntity deleteCategory(@PathVariable Long categoryId)  {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
