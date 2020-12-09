package se.joeldegerman.javaeewebshop.models.dto;

import lombok.Data;
import se.joeldegerman.javaeewebshop.models.entity.Category;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {
    @NotBlank(message = "Product name need to be provided")
    private String name;
    @Min(value = 1, message = "Price cant be less than 1 SEK")
    private double price;
    @NotBlank(message = "Image url need to be provided")
    private String imgUrl;
    @NotBlank(message = "Description needs to be provided")
    private String description;
    @NotNull(message = "Category must be chosen")
    private CategoryDto category;

    public ProductDto() {
    }
}
