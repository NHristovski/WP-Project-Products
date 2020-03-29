package hristovski.nikola.product.service;

import hristovski.nikola.product.exception.CategoryNotFoundException;
import hristovski.nikola.product.exception.ProductNotFoundException;
import hristovski.nikola.product.model.category.Category;
import hristovski.nikola.product.model.product.*;
import hristovski.nikola.product.repository.CategoryRepository;
import hristovski.nikola.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final RatingService ratingService;
    private final CategoryRepository categoryRepository;

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
    public int getProductsCount(Integer howMany) {
        long count = productRepository.count();
        log.info(" $$$ the count is {}", count);
        double ceil = Math.ceil((count / (howMany * 1.0)));
        int maxPages = (int)ceil;
        log.info("$$$ max pages are {}", maxPages);
        return maxPages;
    }

    @Override
    public GetProductsResponse getProductsSortedByRating(int from, int howMany, String username) {
        Iterable<Product> all = productRepository.findAll();

        List<ProductResponse> productResponses = new ArrayList<>();
        for (var p : all) {
            ProductResponse productResponse = mapProductToProductResponse(username, p);
            productResponses.add(productResponse);
        }

        List<ProductResponse> collect = productResponses.stream()
                .sorted(Comparator.comparing(ProductResponse::getAverageRating).reversed())
                .skip(from * howMany)
                .limit(howMany)
                .collect(Collectors.toList());

        int productsCount = getProductsCount(howMany);
        return new GetProductsResponse(collect,productsCount);
    }

    @Override
    public GetProductsResponse getProductsInCategory(int from, int howMany, String categoryName, String username) throws CategoryNotFoundException {

        Category category = categoryRepository.getByCategoryName(categoryName);

        PageRequest pageRequest = PageRequest.of(from, howMany);
        Page<Product> page = productRepository.queryProductsByCategoriesIsContaining(category, pageRequest);

        List<Product> products = page.getContent();

        List<ProductResponse> collect = products.stream()
                .map(product -> mapProductToProductResponse(username, product))
                .collect(Collectors.toList());

        long count = productRepository.countProductsByCategoriesIsContaining(category);
        log.info(" $$$ the count for category {} is {}", categoryName, count);
        double ceil = Math.ceil((count / (howMany * 1.0)));
        int maxPages = (int)ceil;
        log.info("$$$ max pages are {}", maxPages);
        return new GetProductsResponse(collect,maxPages);
    }

    @Override
    public ProductResponse rateProduct(RateRequest rateRequest, String username) throws ProductNotFoundException {
        Product product = productRepository.findById(rateRequest.getId())
                .orElseThrow(() -> new ProductNotFoundException(rateRequest.getId().toString()));

        return ratingService.rate(product, rateRequest.getRating(), username);
    }

    @Override
    public Product addProduct(AddProductRequest addProductRequest) {

        List<Category> categories = addProductRequest.getCategoryNames()
                .stream()
                .map(categoryRepository::getByCategoryName)
                .collect(Collectors.toList());

        Product product = new Product(addProductRequest,categories);

        return productRepository.save(product);
    }

    @Override
    public Product editProduct(Long id, AddProductRequest productRequest) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));

        product.setStock(productRequest.getStock());
        product.setDescription(productRequest.getDescription());
        product.setImageLocation(productRequest.getImageLocation());
        product.setTitle(productRequest.getTitle());
        product.setPrice(productRequest.getPrice());

        List<Category> categories = productRequest.getCategoryNames()
                .stream()
                .map(categoryRepository::getByCategoryName)
                .collect(Collectors.toList());


        product.setCategories(categories);

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void removeProductsFromCategory(Category category) {
        Iterable<Product> all = productRepository.findAll();

        for (Product next : all) {
            log.info("product is: {} categoris are: {}", next.getId(), next.getCategories());

            List<Category> categories1 = next.getCategories();
            List<Category> newCategories = categories1.stream()
                    .filter(cat -> !cat.getId().equals(category.getId()))
                    .collect(Collectors.toList());

            if (categories1.size() != newCategories.size()) {
                log.info("categories1 is not the same as newCategories");
                next.setCategories(newCategories);
                productRepository.save(next);
                log.info("$$$ Now the product should now have the category");
            }
        }
    }

    @Override
    public List<Product> search(String term) {
        try {
            long l = Long.parseLong(term);
            return productRepository.findAllByIdEqualsOrTitleContains(l,term);
        }catch (Exception ignored){

        }
        return productRepository.findAllByIdEqualsOrTitleContains(-1L,term);
    }

}

