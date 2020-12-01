package se.joeldegerman.javaeewebshop.models.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

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
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category")
    @NotNull(message = "Category must be chosen")
    private Category category;

    @Setter(AccessLevel.NONE)
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

    public Product(@NotBlank(message = "Product name need to be provided") String name,
                   @Min(value = 1, message = "Price cant be less than 1 SEK") double price,
                   @NotBlank(message = "Image url need to be provided") String imgUrl,
                   @NotBlank(message = "Description needs to be provided") String description,
                   @NotNull(message = "Category must be chosen") Category category) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.description = description;
        this.category = category;
    }

    public Product() {
        dateCreated = LocalDateTime.now();
    }

}
