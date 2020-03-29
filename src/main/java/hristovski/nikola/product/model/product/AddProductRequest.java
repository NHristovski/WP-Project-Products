package hristovski.nikola.product.model.product;

import hristovski.nikola.product.model.category.Category;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class AddProductRequest {

    @NonNull
    private String title;

    @NonNull
    private String imageLocation;

    @NonNull
    private String description;

    private List<String> categoryNames;

    @NonNull
    private Double price;

    @NonNull
    private Integer stock;
}

