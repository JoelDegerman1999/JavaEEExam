package se.joeldegerman.javaeewebshop.services;

import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.CartItem;
import se.joeldegerman.javaeewebshop.models.viewmodels.CartViewModel;
import se.joeldegerman.javaeewebshop.models.Order;
import se.joeldegerman.javaeewebshop.models.OrderLine;

import java.util.List;

@Service
public class OrderService {

    private CartService cartService;

    public OrderService(CartService cartService) {
        this.cartService = cartService;
    }

    public OrderLine createOrderLine() {
        CartViewModel cart = cartService.getCartVM();
        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems.size() > 0) {
            OrderLine orderLine = new OrderLine();
            for (CartItem item : cartItems) {
                Order order = new Order();
                order.setProduct(item.getProduct());
                order.setQuantity(item.getQuantity());
                order.setTotal(item.getTotalPrice());
                orderLine.getOrders().add(order);
            }
            cartService.clearCart();
            return orderLine;
        }
        return null;
    }
}


