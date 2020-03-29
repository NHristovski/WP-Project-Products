package hristovski.nikola.product.repository;

import hristovski.nikola.product.model.category.Category;
import hristovski.nikola.product.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Page<Product> queryProductsByCategoriesIsContaining(Category category, Pageable pageable);

    Page<Product> queryProductsByCategoriesContains(Category category, Pageable pageable);

    List<Product> findAllByIdEqualsOrTitleContains(Long id, String contains);

    long countProductsByCategoriesIsContaining(Category category);

}
