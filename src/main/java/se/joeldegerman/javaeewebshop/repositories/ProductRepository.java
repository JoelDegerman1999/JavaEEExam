package se.joeldegerman.javaeewebshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.joeldegerman.javaeewebshop.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
