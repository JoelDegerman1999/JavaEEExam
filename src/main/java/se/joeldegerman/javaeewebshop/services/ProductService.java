package se.joeldegerman.javaeewebshop.services;

import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.Product;
import se.joeldegerman.javaeewebshop.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void createProduct(Product product) {
        repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
