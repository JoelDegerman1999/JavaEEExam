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
        if (cartItems.contains(item)) {
            for (CartItem cartItem : cartItems) {
                if (item.equals(cartItem)) {
                    cartItem.incrementQuantity();
                    cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
                }
            }
        } else {
            item.setPrice(item.getProduct().getPrice());
            cartItems.add(item);
        }
        calculateGrandTotal();
    }

    public void deleteCartItem(CartItem item) {
        cartItems.remove(item);
        calculateGrandTotal();
    }

    public void decreaseItemQuantity(CartItem item) {
        if (cartItems.contains(item)) {
            for (CartItem cartItem : cartItems) {
                if (item.equals(cartItem)) {
                    if (cartItem.getQuantity() == 1) {
                        deleteCartItem(item);
                        break;
                    } else {
                        cartItem.decrementQuantity();
                        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
                    }
                }
            }
        }
        calculateGrandTotal();
    }

    public void increaseItemQuantity(CartItem item) {
        if (cartItems.contains(item)) {
            for (CartItem cartItem : cartItems) {
                if (item.equals(cartItem)) {
                    cartItem.incrementQuantity();
                    cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
                }
            }
        }
        calculateGrandTotal();
    }

    public void calculateGrandTotal() {
        grandTotal = 0;
        for (CartItem item : cartItems) {
            for (int i = 0; i < item.getQuantity(); i++) {
                grandTotal += item.getProduct().getPrice();
            }
        }
    }

    public void clearCart() {
        cartItems.clear();
    }

    public int getCartSize() {
        return cartItems.size();
    }

}
