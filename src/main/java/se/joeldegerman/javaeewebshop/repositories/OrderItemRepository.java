package se.joeldegerman.javaeewebshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.joeldegerman.javaeewebshop.models.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
