package se.joeldegerman.javaeewebshop.controllers.rest;

import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/api/products/{id}")
    public Product getProductById(@PathVariable long id) {
        Optional<Product> product = productService.getById(id);
        return product.orElse(null);
    }


    @PostMapping("/api/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @DeleteMapping("api/products/{id}")
    public String deleteProduct(@PathVariable long id) {
        Optional<Product> product = productService.getById(id);
        if (product.isEmpty()) return "Product not found";
        productService.delete(product.get().getId());
        return "Product deleted successfully";
    }
}
