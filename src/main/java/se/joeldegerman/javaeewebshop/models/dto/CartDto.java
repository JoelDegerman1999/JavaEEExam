package se.joeldegerman.javaeewebshop.models.dto;

import lombok.Data;
import se.joeldegerman.javaeewebshop.models.CartItem;

import java.util.List;

@Data
public class CartDto {
    private List<CartItem> cartItems;
    private int grandTotal;
}
