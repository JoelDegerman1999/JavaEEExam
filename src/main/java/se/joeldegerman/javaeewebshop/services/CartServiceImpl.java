package se.joeldegerman.javaeewebshop.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.joeldegerman.javaeewebshop.models.Cart;
import se.joeldegerman.javaeewebshop.models.CartItem;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.models.viewmodels.CartViewModel;
import se.joeldegerman.javaeewebshop.services.interfaces.CartService;

import java.util.List;

@Service
@SessionScope
public class CartServiceImpl  implements CartService {

    private final Cart cart;

    public CartServiceImpl() {
        this.cart = new Cart();
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

    public void decreaseItemCount(Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cart.decreaseItemQuantity(cartItem);
    }

    public void increaseItemCount(Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cart.increaseItemQuantity(cartItem);
    }

    public CartViewModel getCartVM() {
        return new CartViewModel(cart.getCartItems(), cart.getGrandTotal());
    }

    public int getCartSize() {
        int size = 0;
        List<CartItem> cartItems = cart.getCartItems();
        size = cartItems.stream().mapToInt(CartItem::getQuantity).sum();
        return size;
    }

    public void clearCart() {
        cart.clearCart();
        cart.setGrandTotal(0);
    }

}
