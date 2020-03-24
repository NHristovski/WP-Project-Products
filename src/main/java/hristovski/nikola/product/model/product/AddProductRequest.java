package hristovski.nikola.product.model.product;

import lombok.Data;
import lombok.NonNull;

@Data
public class AddProductRequest {

    @NonNull
    private String title;

    @NonNull
    private String imageLocation;

    @NonNull
    private String description;

    @NonNull
    private Double price;

    @NonNull
    private Integer stock;
}

