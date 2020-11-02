package se.joeldegerman.javaeewebshop.controllers;

import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.Product;
import se.joeldegerman.javaeewebshop.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductRestController {

    private final ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/api/products/{id}")
    public Product getProductById(@PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @GetMapping(value = "/api/products", params = "name")
    public Product getProductByName(@RequestParam String name) {
        Optional<Product> product = productRepository.findByName(name);
        return product.orElse(null);
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @DeleteMapping("api/products/{id}")
    public String deleteProduct(@PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) return "Product not found";
        productRepository.delete(product.get());
        return "Product deleted successfully";
    }
}
