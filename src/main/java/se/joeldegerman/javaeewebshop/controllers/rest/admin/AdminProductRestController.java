package se.joeldegerman.javaeewebshop.controllers.rest.admin;

import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.services.ProductServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/product/")
public class AdminProductRestController {

    private final ProductService productService;

    public AdminProductRestController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("all")
    public List<Product> getAllProducts() {
        List<Product> products = productService.getAll();
        return products;
    }

    @PostMapping("create")
    public Product createProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("update/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product) {
        product.setId(id);
        return productService.update(product);
    }

    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.delete(id);
    }

}
