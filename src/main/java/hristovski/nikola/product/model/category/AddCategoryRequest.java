package hristovski.nikola.product.model.category;

import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryRequest {
    @NonNull
    String categoryName;
}
