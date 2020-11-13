package se.joeldegerman.javaeewebshop.services;

import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.CartItem;
import se.joeldegerman.javaeewebshop.models.viewmodels.CartViewModel;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.models.entity.OrderLine;

import java.util.List;

@Service
public class OrderLineService {

    private CartService cartService;

    public OrderLineService(CartService cartService) {
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
                order.setTotal(item.getPrice());
                orderLine.getOrders().add(order);
            }
            orderLine.setGrandTotal(cart.getGrandTotal());
            cartService.clearCart();
            return orderLine;
        }
        return null;
    }
}


