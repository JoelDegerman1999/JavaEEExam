package se.joeldegerman.javaeewebshop.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.Cart;
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
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final CartServiceImpl cartService;

    private final OrderRepository orderRepository;

    public OrderServiceImpl(CartServiceImpl cartService, OrderRepository orderRepository) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
    }

    public Optional<Order> createAndReturnOrder(User user) {
        Cart cart = cartService.getCart();
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
    public List<Order> getAll() {
        return orderRepository.findAll(Sort.by("id"));
    }

    @Override
    public Order send(long id) {
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
    public Optional<Order> getById(long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder;
    }

    @Override
    public List<Order> findOrderByUsername(String username) {
        return orderRepository.findByUser(username);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}


