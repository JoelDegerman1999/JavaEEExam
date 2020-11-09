package se.joeldegerman.javaeewebshop.models.viewmodels;

import lombok.Data;
import se.joeldegerman.javaeewebshop.models.CartItem;

import java.util.List;

@Data
public class CartViewModel {
    private List<CartItem> cartItems;
    private int grandTotal;

    public CartViewModel(List<CartItem> cartItems, int grandTotal) {
        this.cartItems = cartItems;
        this.grandTotal = grandTotal;
    }

    public int getCartSize() {
        int size = cartItems.stream().mapToInt(CartItem::getQuantity).sum();
        return size;
    }
}
