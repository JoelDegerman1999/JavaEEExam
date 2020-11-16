package se.joeldegerman.javaeewebshop.models.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private double price;

    public ProductDto() {
    }
}
