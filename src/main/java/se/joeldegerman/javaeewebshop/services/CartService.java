package se.joeldegerman.javaeewebshop.services;

import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.Cart;
import se.joeldegerman.javaeewebshop.models.CartItem;
import se.joeldegerman.javaeewebshop.models.CartViewModel;
import se.joeldegerman.javaeewebshop.models.Product;

@Service
public class CartService {

    private final Cart cart;

    public CartService(Cart cart) {
        this.cart = cart;
    }

    public void addToCart() {
        Product p1 = new Product();
        p1.setPrice(124);
        p1.setName("Test");

        CartItem cartItem = new CartItem();
        cartItem.setProduct(p1);
        cartItem.setQuantity(2);
        cart.addCartItem(cartItem);
    }

    public CartViewModel getCart() {
        return new CartViewModel(cart.getCartItems(), cart.getGrandTotal());
    }

}
