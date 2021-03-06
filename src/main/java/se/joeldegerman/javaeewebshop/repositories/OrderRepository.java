package se.joeldegerman.javaeewebshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.joeldegerman.javaeewebshop.models.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o from Order o WHERE o.orderSent = false")
    Optional<List<Order>> getNonSentOrders();

    @Query("SELECT o from Order o WHERE o.orderSent = true")
    Optional<List<Order>> getSentOrders();

    @Query("select o from Order o where o.user.username = ?1")
    List<Order> findByUser(String username);
}
