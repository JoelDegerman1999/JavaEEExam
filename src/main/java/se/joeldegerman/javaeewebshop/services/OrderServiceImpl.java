package se.joeldegerman.javaeewebshop.services;

import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.CartItem;
import se.joeldegerman.javaeewebshop.models.entity.User;
import se.joeldegerman.javaeewebshop.models.viewmodels.CartViewModel;
import se.joeldegerman.javaeewebshop.models.entity.OrderItem;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.repositories.OrderRepository;
import se.joeldegerman.javaeewebshop.services.interfaces.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private CartServiceImpl cartService;

    private OrderRepository orderRepository;

    public OrderServiceImpl(CartServiceImpl cartService, OrderRepository orderRepository) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
    }

    public Optional<Order> createAndReturnOrder(User user) {
        CartViewModel cart = cartService.getCartVM();
        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems.size() > 0) {
            Order order = new Order();
            for (CartItem item : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(item.getProduct());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setOrderItemTotal(item.getPrice());
                order.getOrderItems().add(orderItem);
            }
            order.setOrderGrandTotal(cart.getGrandTotal());
            order.setUser(user);
            cartService.clearCart();
            return Optional.of(order);
        }
        return Optional.empty();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}


