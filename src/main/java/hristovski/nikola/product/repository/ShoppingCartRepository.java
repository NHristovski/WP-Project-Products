package hristovski.nikola.product.repository;

import hristovski.nikola.product.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

    ShoppingCart findByUsername(String username);
}
