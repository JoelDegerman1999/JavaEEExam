package se.joeldegerman.javaeewebshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.joeldegerman.javaeewebshop.models.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
