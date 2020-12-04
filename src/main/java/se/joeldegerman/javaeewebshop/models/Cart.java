package se.joeldegerman.javaeewebshop.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> cartItems;
    private int grandTotal;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public void addCartItem(CartItem item) {
        if (!cartItems.contains(item)) {
            item.setPrice(item.getProduct().getPrice());
            getCartItems().add(item);

        } else {
            getCartItems().forEach(cartItem -> {
                if (cartItem.equals(item)) cartItem.incrementQuantity();
            });
        }
        calculateGrandTotal();
    }

    public void deleteCartItem(CartItem item) {
        getCartItems().remove(item);
        calculateGrandTotal();
    }

    public void decreaseItemQuantity(CartItem item) {
        if (!getCartItems().contains(item)) return;
        for (CartItem cartItem : getCartItems()) {
            if (!item.equals(cartItem)) continue;
            if (cartItem.getQuantity() == 1) {
                deleteCartItem(item);
                break;
            }
            cartItem.decrementQuantity();
        }
        calculateGrandTotal();
    }

    public void increaseItemQuantity(CartItem item) {
        if (!getCartItems().contains(item)) return;
        for (CartItem cartItem : getCartItems()) {
            if (!cartItem.equals(item)) continue;
            cartItem.incrementQuantity();
        }
        calculateGrandTotal();
    }

    public void calculateGrandTotal() {
        setGrandTotal(getCartItems().stream().mapToInt(value -> (int) (value.getPrice())).sum());
    }

    public void clearCart() {
        getCartItems().clear();
    }

    public int getCartSize() {
        return getCartItems().stream().mapToInt(CartItem::getQuantity).sum();
    }

}
