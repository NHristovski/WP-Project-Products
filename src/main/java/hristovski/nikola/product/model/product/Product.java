package hristovski.nikola.product.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hristovski.nikola.product.model.Rating;
import hristovski.nikola.product.model.ShoppingCartItem;
import hristovski.nikola.product.model.category.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Product {

    public Product(AddProductRequest addProductRequest, List<Category> categories) {

        this.id = null;

        this.title = addProductRequest.getTitle();
        this.description = addProductRequest.getDescription();
        this.imageLocation = addProductRequest.getImageLocation();
        this.price = addProductRequest.getPrice();
        this.stock = addProductRequest.getStock();
        this.categories = categories;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String imageLocation;
    private String description;
    private double price;
    private int stock;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Category> categories;

//    @JsonIgnore
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    List<Rating> ratings;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    List<ShoppingCartItem> shoppingCartItems;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageLocation='" + imageLocation + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
