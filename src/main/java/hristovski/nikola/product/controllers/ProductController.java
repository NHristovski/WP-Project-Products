package hristovski.nikola.product.controllers;

import hristovski.nikola.product.exception.CategoryNotFoundException;
import hristovski.nikola.product.exception.ProductNotFoundException;
import hristovski.nikola.product.model.product.*;
import hristovski.nikola.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

        return ResponseEntity.ok(productService.addProduct(addProductRequest));
    }

    @GetMapping
    public ResponseEntity<GetProductsResponse> getAll(@RequestParam("from") Integer from,
                                                      @RequestParam("howMany") Integer howMany,
                                                      HttpServletRequest httpRequest) {

        String username = httpRequest.getHeader("username");

        List<ProductResponse> products = productService.getProducts(from, howMany, username);
        int maxPages = productService.getProductsCount(howMany);

        return ResponseEntity.ok(new GetProductsResponse(products, maxPages));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getOne(@PathVariable Long id,
                                                  HttpServletRequest httpRequest)
            throws ProductNotFoundException {

        String username = httpRequest.getHeader("username");

        return ResponseEntity.ok(productService.getById(id,username));
    }

    @PostMapping("/rate")
    public ResponseEntity<ProductResponse> rateProduct(@Valid @RequestBody RateRequest rateRequest,
                                                       HttpServletRequest httpRequest)
            throws ProductNotFoundException {

        String username = httpRequest.getHeader("username");
        return ResponseEntity.ok(productService.rateProduct(rateRequest,username));
    }

    @GetMapping("/forCategory/{categoryName}")
    public ResponseEntity<GetProductsResponse> getAll(@RequestParam("from") Integer from,
                                                        @RequestParam("howMany") Integer howMany,
                                                        @PathVariable String categoryName,
                                                        HttpServletRequest httpRequest) throws CategoryNotFoundException {

        String username = httpRequest.getHeader("username");

        if (categoryName.equals("Top Products")){
            return ResponseEntity.ok(productService.getProductsSortedByRating(from,howMany,username));
        }

        return ResponseEntity.ok(productService.getProductsInCategory(from, howMany, categoryName, username));
    }
}
