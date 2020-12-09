package se.joeldegerman.javaeewebshop.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.joeldegerman.javaeewebshop.models.Cart;
import se.joeldegerman.javaeewebshop.models.CartItem;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.services.interfaces.CartService;

import java.util.List;

@Service
@SessionScope
public class CartServiceImpl implements CartService {

    private final Cart cart;

    public CartServiceImpl() {
        this.cart = new Cart();
    }

    @Override
    public void addToCart(Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cart.addCartItem(cartItem);
    }

    @Override
    public void removeFromCart(Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cart.deleteCartItem(cartItem);
    }

    @Override
    public void decreaseItemCount(Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cart.decreaseItemQuantity(cartItem);
    }

    @Override
    public void increaseItemCount(Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cart.increaseItemQuantity(cartItem);
    }

    @Override
    public Cart getCart() {
        return this.cart;
    }

    @Override
    public int getCartSize() {
        int size = 0;
        List<CartItem> cartItems = cart.getCartItems();
        size = cartItems.stream().mapToInt(CartItem::getQuantity).sum();
        return size;
    }

    @Override
    public void clearCart() {
        cart.clearCart();
        cart.setGrandTotal(0);
    }

    @Override
    public boolean isProductInCart(Product product) {
        for (CartItem cartItem : getCart().getCartItems()) {
            if(cartItem.getProduct().equals(product)) return true;
        }
        return false;
    }
}
