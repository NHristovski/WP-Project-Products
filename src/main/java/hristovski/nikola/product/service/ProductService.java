package hristovski.nikola.product.service;

import hristovski.nikola.product.exception.CategoryNotFoundException;
import hristovski.nikola.product.exception.ProductNotFoundException;
import hristovski.nikola.product.model.category.Category;
import hristovski.nikola.product.model.product.*;

import java.util.List;

public interface ProductService {

    ProductResponse getById(Long productId,String username) throws ProductNotFoundException;

    Product getById(Long productId) throws ProductNotFoundException;

    List<ProductResponse> getProducts(int from, int howMany, String username);

    GetProductsResponse getProductsSortedByRating(int from, int howMany, String username);

    GetProductsResponse getProductsInCategory(int from, int howMany, String category, String username) throws CategoryNotFoundException;

    ProductResponse rateProduct(RateRequest rateRequest, String username) throws ProductNotFoundException;

    Product addProduct(AddProductRequest product);
    Product editProduct(Long id, AddProductRequest product) throws ProductNotFoundException;
    void deleteProduct(Long productId);

    void removeProductsFromCategory(Category one);

    List<Product> search(String term);

    int getProductsCount(Integer howMany);
}
