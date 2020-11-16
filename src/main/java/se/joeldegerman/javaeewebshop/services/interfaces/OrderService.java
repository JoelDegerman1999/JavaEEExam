package se.joeldegerman.javaeewebshop.services.interfaces;

import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.models.entity.User;

import java.util.Optional;

public interface OrderService {
    Optional<Order> createAndReturnOrder(User user);
    Order saveOrder(Order order);
}
