package se.joeldegerman.javaeewebshop.services.interfaces;

import org.springframework.data.domain.Page;
import se.joeldegerman.javaeewebshop.models.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> searchProducts(String keyword);

    List<Product> findAllProductsByCategory(String categoryName);

    Product getProductById(long id);

    Product createProduct(Product product);

    void deleteProduct(long id);

    Product updateProduct(Product product);

    List<Product> getAllProducts();

    Page<Product> findPaginated(int page, int size);
}
