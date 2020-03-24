package hristovski.nikola.product.model.product;

import lombok.Data;
import lombok.NonNull;

@Data
public class RateRequest {
    @NonNull
    private Long id;
    @NonNull
    private Integer rating;
    @NonNull
    private String username;
}
