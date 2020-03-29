package hristovski.nikola.product.model.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hristovski.nikola.product.model.product.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Slf4j
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String categoryName;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    @Override
    public boolean equals(Object o) {
        log.info("$$$ checking equals");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()){
            log.info("Other is null or not same class");
            log.info("other: " + o);
            log.info("this class {}, other class {}", getClass(), o.getClass());
            return false;
        }
        Category category = (Category) o;
        boolean equals = Objects.equals(id, category.id);
        log.info("Equals: {}",equals);
        return equals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
