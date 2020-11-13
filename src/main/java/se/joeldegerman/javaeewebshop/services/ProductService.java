package se.joeldegerman.javaeewebshop.services;

import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.repositories.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
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

    public Product updateProduct(Product product, long id) {
        Product productById = getProductById(id);
        productById.setName(product.getName());
        productById.setPrice(product.getPrice());
        productById.setImgUrl(product.getImgUrl());
        productById.setDateModified(LocalDateTime.now());
        return repository.save(productById);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
