package hristovski.nikola.product.service;

import hristovski.nikola.product.exception.CategoryNotFoundException;
import hristovski.nikola.product.exception.ProductNotFoundException;
import hristovski.nikola.product.model.Category;
import hristovski.nikola.product.model.product.AddProductRequest;
import hristovski.nikola.product.model.product.Product;
import hristovski.nikola.product.model.product.ProductResponse;
import hristovski.nikola.product.model.product.RateRequest;

import java.util.List;

public interface ProductService {

    ProductResponse getById(Long productId,String username) throws ProductNotFoundException;

    Product getById(Long productId) throws ProductNotFoundException;

    List<ProductResponse> getProducts(int from, int howMany, String username);

    List<ProductResponse> getProductsSortedByRating(int from,int howMany, String username);

    List<ProductResponse> getProductsInCategory(int from, int howMany, String category, String username) throws CategoryNotFoundException;

    ProductResponse rateProduct(RateRequest rateRequest) throws ProductNotFoundException;

    Product addProduct(AddProductRequest product);
    Product editProduct(Product product);
    void deleteProduct(Long productId);
}
