package hristovski.nikola.product.service;

import hristovski.nikola.product.model.Rating;
import hristovski.nikola.product.model.product.Product;
import hristovski.nikola.product.model.product.ProductResponse;
import hristovski.nikola.product.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public ProductResponse getCurrentRating(Product product, String username) {
        Rating rating = ratingRepository.getRatingByProductAndUsername(product, username);
        List<Rating> ratings = ratingRepository.queryAllByProduct(product);

        Double average = ratings
                .stream()
                .mapToDouble(Rating::getRating)
                .average().orElse(0d);

        return new ProductResponse(product,rating.getRating(),average,ratings.size());
    }

    @Override
    public Double getAverageRating(Product product) {
        List<Rating> ratings = ratingRepository.queryAllByProduct(product);

        return ratings
                .stream()
                .mapToDouble(Rating::getRating)
                .average().orElse(0d);
    }

    @Override
    public Integer getTotalRating(Product product) {
        List<Rating> ratings = ratingRepository.queryAllByProduct(product);
        return ratings.size();
    }

    @Override
    public ProductResponse rate(Product product, Integer ratingScore, String username) {

        try {
            Rating oldRating = ratingRepository.findByProductAndUsername(product, username);
            if (oldRating != null){
                oldRating.setRating(ratingScore);
                ratingRepository.save(oldRating);
            }else{
                Rating rating = new Rating();
                rating.setProduct(product);
                rating.setRating(ratingScore);
                rating.setUsername(username);
                ratingRepository.save(rating);
            }
        }catch (Exception ex) {

            Rating rating = new Rating();
            rating.setProduct(product);
            rating.setRating(ratingScore);
            rating.setUsername(username);
            ratingRepository.save(rating);
        }

        return getCurrentRating(product,username);
    }
}
