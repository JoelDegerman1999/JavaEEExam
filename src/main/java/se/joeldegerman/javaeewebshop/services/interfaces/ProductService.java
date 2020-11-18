package se.joeldegerman.javaeewebshop.services.interfaces;

import se.joeldegerman.javaeewebshop.models.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> searchProducts(String keyword);

    List<Product> findAllProductsByCategory(String category);

    Product getProductById(long id);

    void createProduct(Product product);

    void deleteProduct(long id);

    Product updateProduct(Product product);

    List<Product> getAllProducts();
}
