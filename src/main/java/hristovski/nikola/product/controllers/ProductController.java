package hristovski.nikola.product.controllers;

import hristovski.nikola.product.exception.CategoryNotFoundException;
import hristovski.nikola.product.exception.ProductNotFoundException;
import hristovski.nikola.product.model.product.AddProductRequest;
import hristovski.nikola.product.model.product.Product;
import hristovski.nikola.product.model.product.ProductResponse;
import hristovski.nikola.product.model.product.RateRequest;
import hristovski.nikola.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody AddProductRequest addProductRequest) {

        log.info("Adding product..");

        return ResponseEntity.ok(productService.addProduct(addProductRequest));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll(@RequestParam("from") Integer from,
                                                        @RequestParam("howMany") Integer howMany,
                                                        @RequestParam("username") String username) {
        return ResponseEntity.ok(productService.getProducts(from, howMany, username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getOne(@PathVariable Long id,
                                                  @RequestParam("username") String username)
            throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getById(id,username));
    }

    @PostMapping("/rate")
    public ResponseEntity<ProductResponse> rateProduct(@Valid @RequestBody RateRequest rateRequest)
            throws ProductNotFoundException {

        return ResponseEntity.ok(productService.rateProduct(rateRequest));
    }

    @GetMapping("/forCategory/{categoryName}")
    public ResponseEntity<List<ProductResponse>> getAll(@RequestParam("from") Integer from,
                                                        @RequestParam("howMany") Integer howMany,
                                                        @RequestParam("username") String username,
                                                        @PathVariable String categoryName) throws CategoryNotFoundException {

        if (categoryName.equals("Top Products")){
            return ResponseEntity.ok(productService.getProductsSortedByRating(from,howMany,username));
        }

        return ResponseEntity.ok(productService.getProductsInCategory(from, howMany, categoryName, username));
    }
}
