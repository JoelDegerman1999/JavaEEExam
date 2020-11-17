package se.joeldegerman.javaeewebshop.services.interfaces;

import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> createAndReturnOrder(User user);

    List<Order> getAllNonSentOrders();

    List<Order> getAllSentOrders();

    Order saveOrder(Order order);
}
