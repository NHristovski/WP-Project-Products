package hristovski.nikola.product.model;

import hristovski.nikola.product.model.product.Product;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private Integer rating;

    @ManyToOne
    private Product product;
}
