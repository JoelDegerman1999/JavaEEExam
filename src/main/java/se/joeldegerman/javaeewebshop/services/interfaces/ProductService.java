package se.joeldegerman.javaeewebshop.services.interfaces;

import org.springframework.data.domain.Page;
import se.joeldegerman.javaeewebshop.models.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> search(String keyword);

    List<Product> findAllByCategory(String categoryName);

    Optional<Product> getById(long id);

    Product create(Product product);

    void delete(long id);

    Product update(Product product);

    List<Product> getAll();

    Page<Product> findPaginated(int page, int size);
}
