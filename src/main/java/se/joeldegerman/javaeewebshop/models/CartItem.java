package se.joeldegerman.javaeewebshop.models;

import lombok.Data;

import java.util.Objects;

@Data
public class CartItem {
    private Product product;
    private int quantity = 1;
    private double totalPrice;

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(product, cartItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}