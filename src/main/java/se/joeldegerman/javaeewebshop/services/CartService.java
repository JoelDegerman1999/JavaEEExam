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

    public void addToCart(Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cart.addCartItem(cartItem);
    }

    public void removeFromCart(Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cart.deleteCartItem(cartItem);
    }

    public CartViewModel getCart() {
        return new CartViewModel(cart.getCartItems(), cart.getGrandTotal());
    }

    public int getCartSize() {
        return cart.getCartItems().size();
    }

    public void clearCart() {
        cart.clearCart();
        cart.setGrandTotal(0);
    }

}
