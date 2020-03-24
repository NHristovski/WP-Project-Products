package hristovski.nikola.product.service;

import hristovski.nikola.product.model.product.Product;
import hristovski.nikola.product.model.product.ProductResponse;

public interface RatingService {

    ProductResponse getCurrentRating(Product product, String username);
    Double getAverageRating(Product product);
    Integer getTotalRating(Product product);

    ProductResponse rate(Product product, Integer rating, String username);
}
