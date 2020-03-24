package hristovski.nikola.product.model.shoppingCart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityRequest {
    @NonNull
    private Long id;
}
