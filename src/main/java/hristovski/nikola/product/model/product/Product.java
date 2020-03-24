package hristovski.nikola.product.model.product;

import hristovski.nikola.product.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Product {

    public Product(AddProductRequest addProductRequest) {

        this.id = null;

        this.title = addProductRequest.getTitle();
        this.description = addProductRequest.getDescription();
        this.imageLocation = addProductRequest.getImageLocation();
        this.price = addProductRequest.getPrice();
        this.stock = addProductRequest.getStock();
        this.categories = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String imageLocation;
    private String description;
    private double price;
    private int stock;

    @ManyToMany
    List<Category> categories;
}
