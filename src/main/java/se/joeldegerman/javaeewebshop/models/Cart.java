package se.joeldegerman.javaeewebshop.models;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@SessionScope
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
                    cartItem.setTotalPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
                }
            }
        } else {
            item.setTotalPrice(item.getProduct().getPrice());
            cartItems.add(item);
        }
        calculateGrandTotal();
    }

    public void deleteCartItem(CartItem item) {
        cartItems.remove(item);
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

}
