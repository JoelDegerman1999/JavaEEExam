package se.joeldegerman.javaeewebshop.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category() {

    }
}

