package se.joeldegerman.javaeewebshop.models;

import lombok.Data;

import java.util.List;

@Data
public class CartViewModel {
    private List<CartItem> cartItems;
    private int grandTotal;

    public CartViewModel(List<CartItem> cartItems, int grandTotal) {
        this.cartItems = cartItems;
        this.grandTotal = grandTotal;
    }
}
