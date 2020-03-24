package hristovski.nikola.product.repository;

import hristovski.nikola.product.model.Category;
import hristovski.nikola.product.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Page<Product> queryProductsByCategoriesIsContaining(Category category, Pageable pageable);

    Page<Product> queryProductsByCategoriesContains(Category category, Pageable pageable);
}
