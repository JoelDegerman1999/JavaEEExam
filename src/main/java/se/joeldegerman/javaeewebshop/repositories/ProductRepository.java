package se.joeldegerman.javaeewebshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.joeldegerman.javaeewebshop.models.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<List<Product>> findAllByCategory(String category);

    @Query("SELECT p from Product p where lower(p.name) like %?1%")
    Optional<List<Product>> searchProduct(String keyword);
}
