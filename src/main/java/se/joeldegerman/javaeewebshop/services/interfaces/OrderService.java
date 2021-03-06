package se.joeldegerman.javaeewebshop.services.interfaces;

import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> createAndReturnOrder(User user);

    List<Order> getAllNonSentOrders();

    List<Order> getAllSentOrders();

    List<Order> getAll();
    List<Order> findOrderByUsername(String username);

    Optional<Order> getById(long id);

    Order save(Order order);

    Optional<Order> send(long id);
}
