package se.joeldegerman.javaeewebshop.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
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
        cartItems.add(item);
    }
}
