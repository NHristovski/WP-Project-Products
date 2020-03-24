package hristovski.nikola.product.service;

import hristovski.nikola.product.exception.CategoryNotFoundException;
import hristovski.nikola.product.exception.ProductNotFoundException;
import hristovski.nikola.product.model.Category;
import hristovski.nikola.product.model.product.AddProductRequest;
import hristovski.nikola.product.model.product.Product;
import hristovski.nikola.product.model.product.ProductResponse;
import hristovski.nikola.product.model.product.RateRequest;
import hristovski.nikola.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final RatingService ratingService;
    private final CategoryService categoryService;

    @Override
    public ProductResponse getById(Long productId, String username) throws ProductNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId.toString()));

        return mapProductToProductResponse(username, product);
    }

    @Override
    public Product getById(Long productId) throws ProductNotFoundException {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId.toString()));
    }

    private ProductResponse mapProductToProductResponse(String username, Product product) {
        try {
            return ratingService.getCurrentRating(product, username);
        } catch (Exception ex) {
            Double averageRating = ratingService.getAverageRating(product);
            Integer totalRatings = ratingService.getTotalRating(product);
            return new ProductResponse(product, null, averageRating, totalRatings);
        }
    }

    @Override
    public List<ProductResponse> getProducts(int from, int howMany, String username) {
        PageRequest pageRequest = PageRequest.of(from, howMany);
        Page<Product> page = productRepository.findAll(pageRequest);

        List<Product> products = page.getContent();

        return products.stream()
                .map(product -> mapProductToProductResponse(username, product))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductsSortedByRating(int from, int howMany, String username) {
        Iterable<Product> all = productRepository.findAll();

        List<ProductResponse> productResponses = new ArrayList<>();
        for (var p : all){
            ProductResponse productResponse = mapProductToProductResponse(username, p);
            productResponses.add(productResponse);
        }

        return productResponses.stream()
                .sorted(Comparator.comparing(ProductResponse::getAverageRating).reversed())
                .skip(from * howMany)
                .limit(howMany)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductsInCategory(int from, int howMany, String categoryName, String username) throws CategoryNotFoundException {

        Category category = categoryService.getByCategoryName(categoryName);

        PageRequest pageRequest = PageRequest.of(from, howMany);
        Page<Product> page = productRepository.queryProductsByCategoriesIsContaining(category, pageRequest);

        List<Product> products = page.getContent();

        return products.stream()
                .map(product -> mapProductToProductResponse(username, product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse rateProduct(RateRequest rateRequest) throws ProductNotFoundException {
        Product product = productRepository.findById(rateRequest.getId())
                .orElseThrow(() -> new ProductNotFoundException(rateRequest.getId().toString()));

        return ratingService.rate(product, rateRequest.getRating(), rateRequest.getUsername());
    }

    @Override
    public Product addProduct(AddProductRequest addProductRequest) {

        Product product = new Product(addProductRequest);

        return productRepository.save(product);
    }

    @Override
    public Product editProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
