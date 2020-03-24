package hristovski.nikola.product.repository;

import hristovski.nikola.product.model.Rating;
import hristovski.nikola.product.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Rating getRatingByProductAndUsername(Product product,String username);
    List<Rating> queryAllByProduct(Product product);
    Rating findByProductAndUsername(Product product, String username);
}
