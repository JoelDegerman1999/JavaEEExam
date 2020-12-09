package se.joeldegerman.javaeewebshop.services.interfaces;

import se.joeldegerman.javaeewebshop.models.Cart;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.models.viewmodels.CartViewModel;

public interface CartService {
    void addToCart(Product product);

    void removeFromCart(Product product);

    void decreaseItemCount(Product product);

    void increaseItemCount(Product product);

    Cart getCart();

    int getCartSize();

    void clearCart();

    boolean isProductInCart(Product product);

}
