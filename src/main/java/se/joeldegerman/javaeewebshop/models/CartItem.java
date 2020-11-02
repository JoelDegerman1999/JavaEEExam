package se.joeldegerman.javaeewebshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class CartItem {
    private Product product;
    private int quantity;
}
