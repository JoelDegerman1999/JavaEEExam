package se.joeldegerman.javaeewebshop.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.CartItem;
import se.joeldegerman.javaeewebshop.models.entity.User;
import se.joeldegerman.javaeewebshop.models.viewmodels.CartViewModel;
import se.joeldegerman.javaeewebshop.models.entity.OrderItem;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.repositories.OrderRepository;
import se.joeldegerman.javaeewebshop.services.interfaces.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final CartServiceImpl cartService;

    private final OrderRepository orderRepository;

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

    @Override
    public List<Order> getAllNonSentOrders() {
        Optional<List<Order>> optionalNonSentOrders = orderRepository.getNonSentOrders();
        if(optionalNonSentOrders.isPresent()) return optionalNonSentOrders.get();
        return new ArrayList<>();
    }

    @Override
    public List<Order> getAllSentOrders() {
        Optional<List<Order>> optionalSentOrders = orderRepository.getSentOrders();
        if(optionalSentOrders.isPresent()) return optionalSentOrders.get();
        return new ArrayList<>();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll(Sort.by("id"));
    }

    @Override
    public Order sendOrder(long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if(order.isOrderSent() == false) {
                order.setOrderSent(true);
            }
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Order getOrderById(long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()) return optionalOrder.get();
        return null;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}


