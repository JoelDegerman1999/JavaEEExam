package se.joeldegerman.javaeewebshop.models.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String name;
    private double price;
    private String imgUrl;

    @Setter(AccessLevel.NONE)
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;

    public Product() {
        dateCreated = LocalDateTime.now();
    }

}
