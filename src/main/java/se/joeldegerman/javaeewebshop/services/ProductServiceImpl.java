package se.joeldegerman.javaeewebshop.services;

import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.repositories.ProductRepository;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    public Product getProductById(long id)
    {
        Optional<Product> product = repository.findById(id);
        if(product.isPresent()) return product.get();
        return null;
    }

    public void createProduct(Product product) {
        repository.save(product);
    }

    public void deleteProduct(long id) {
        repository.delete(getProductById(id));
    }

    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        List<Product> products = repository.findAll();
        products.sort(Comparator.comparing(Product::getId));
        return products;
    }
}
