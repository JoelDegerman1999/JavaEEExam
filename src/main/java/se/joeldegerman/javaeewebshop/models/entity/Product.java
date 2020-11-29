package se.joeldegerman.javaeewebshop.models.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import se.joeldegerman.javaeewebshop.models.Category;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Product name need to be provided")
    private String name;
    @Min(value = 1, message = "Price cant be less than 1 SEK")
    private double price;
    @NotBlank(message = "Image url need to be provided")
    private String imgUrl;
    @NotBlank(message = "Description needs to be provided")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category")
    @NotNull(message = "Category must be chosen")
    private Category category;

    @Setter(AccessLevel.NONE)
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

//    @Version
//    @Setter(AccessLevel.NONE)
//    @Column(name = "product_version")
//    private long version;

    public Product() {
        dateCreated = LocalDateTime.now();
    }

}
