package se.joeldegerman.javaeewebshop.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.joeldegerman.javaeewebshop.models.entity.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartTest {

    Cart cart;

    @BeforeEach
    public void init() {
        cart = new Cart();
    }

    @Test
    void addCartItem() {
        Product p = new Product("Test", 100, null, "", null);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(p);
        cart.addCartItem(cartItem);

        assertEquals(1, cart.getCartSize());
    }

    @Test
    void deleteCartItem() {
        Product p = new Product("Test", 100, null, "", null);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(p);
        cart.addCartItem(cartItem);
        assertEquals(1, cart.getCartSize());
        cart.deleteCartItem(cartItem);
        assertEquals(0, cart.getCartSize());
    }

    @Test
    void decreaseItemQuantity() {
        Product p = new Product("Test", 100, null, "", null);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(p);
        cart.addCartItem(cartItem);
        assertEquals(1, cart.getCartSize());
        cart.decreaseItemQuantity(cartItem);
        assertEquals(0, cart.getCartSize());
    }

    @Test
    void increaseItemQuantity() {
        Product p = new Product("Test", 100, null, "", null);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(p);
        cart.addCartItem(cartItem);
        assertEquals(1, cart.getCartSize());
        cart.increaseItemQuantity(cartItem);
        assertEquals(2, cart.getCartSize());
    }

    @Test
    void calculateGrandTotal() {
        Product p = new Product("Test", 100, null, "", null);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(p);
        cart.addCartItem(cartItem);
        assertEquals(100, cart.getGrandTotal());


        Product p1 = new Product("Test2", 1000, null, "", null);
        CartItem cartItem1 = new CartItem();
        cartItem1.setProduct(p1);
        cart.addCartItem(cartItem1);
        cart.increaseItemQuantity(cartItem1);
        assertEquals(2100, cart.getGrandTotal());

    }

    @Test
    void clearCart() {
        Product p = new Product("Test", 100, null, "", null);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(p);
        cart.addCartItem(cartItem);
        cart.clearCart();
        assertEquals(0, cart.getCartSize());
    }

    @Test
    void getCartSize() {
        Product p = new Product("Test", 100, null, "", null);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(p);
        cart.addCartItem(cartItem);
        assertEquals(1, cart.getCartSize());
    }
}