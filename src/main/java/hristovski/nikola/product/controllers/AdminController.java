package hristovski.nikola.product.controllers;

import hristovski.nikola.product.exception.ProductNotFoundException;
import hristovski.nikola.product.model.category.AddCategoryRequest;
import hristovski.nikola.product.model.category.Category;
import hristovski.nikola.product.model.product.AddProductRequest;
import hristovski.nikola.product.model.product.Product;
import hristovski.nikola.product.service.CategoryService;
import hristovski.nikola.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody AddProductRequest addProductRequest) {

        return ResponseEntity.ok(productService.addProduct(addProductRequest));
    }

    @PutMapping("/editProduct/{id}")
    public ResponseEntity<Product> editProduct(@Valid @RequestBody AddProductRequest editProductRequest,
                                               @PathVariable Long id) throws ProductNotFoundException {

        return ResponseEntity.ok(productService.editProduct(id, editProductRequest));
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody AddCategoryRequest category)  {
        return ResponseEntity.ok(categoryService.addCategory(category));
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Long categoryId)  {
        log.info("$$$ Inside deleteCategory");
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/allCategories")
    public ResponseEntity<List<Category>> getAllCat(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/search/{term}")
    public ResponseEntity<List<Product>> search(@PathVariable String term){
        List<Product> result = productService.search(term);
        log.info("Found {}",result);
        return ResponseEntity.ok(result);
    }
}
