package hristovski.nikola.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hristovski.nikola.product.model.product.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String categoryName;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private List<Product> products;
}
