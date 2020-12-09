package se.joeldegerman.javaeewebshop.servicemock;

import se.joeldegerman.javaeewebshop.models.Cart;
import se.joeldegerman.javaeewebshop.models.CartItem;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.models.viewmodels.CartViewModel;
import se.joeldegerman.javaeewebshop.services.interfaces.CartService;

import java.util.ArrayList;
import java.util.List;

public class CartServiceMockImpl implements CartService {

    List<CartItem> cartItemsDB = new ArrayList<>();

    @Override
    public void addToCart(Product product) {
        var cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setPrice(product.getPrice());
        cartItemsDB.add(cartItem);
    }

    @Override
    public void removeFromCart(Product product) {

    }

    @Override
    public void decreaseItemCount(Product product) {

    }

    @Override
    public void increaseItemCount(Product product) {

    }

    @Override
    public Cart getCart() {
        return null;
    }

    @Override
    public int getCartSize() {
        return 0;
    }

    @Override
    public void clearCart() {

    }

    @Override
    public boolean isProductInCart(Product product) {
        return false;
    }
}
