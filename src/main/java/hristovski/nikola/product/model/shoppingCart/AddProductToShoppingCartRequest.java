package hristovski.nikola.product.model.shoppingCart;

import lombok.Data;
import lombok.NonNull;

@Data
public class AddProductToShoppingCartRequest {

    @NonNull
    private Long productId;

    @NonNull
    private Integer quantity;

    @NonNull
    private String username;
}
