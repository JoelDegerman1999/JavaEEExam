package se.joeldegerman.javaeewebshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.joeldegerman.javaeewebshop.models.entity.OrderLine;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
}
