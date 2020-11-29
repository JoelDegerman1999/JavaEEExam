package se.joeldegerman.javaeewebshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import se.joeldegerman.javaeewebshop.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
Optional<Category> findByCategoryName(String categoryName);
}
