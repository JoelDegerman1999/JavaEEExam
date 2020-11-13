package se.joeldegerman.javaeewebshop.controllers.rest;

import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.models.viewmodels.CartViewModel;
import se.joeldegerman.javaeewebshop.repositories.ProductRepository;
import se.joeldegerman.javaeewebshop.services.CartService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class CartRestController {
    private CartService service;
    private ProductRepository productRepository;

    public CartRestController(CartService service, ProductRepository productRepository) {
        this.service = service;
        this.productRepository = productRepository;
    }


    @PostMapping("cart/add/{id}")
    public void addToCart(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            service.addToCart(product.get());
        }
    }

    @DeleteMapping("cart/delete/{id}")
    public void deleteProductFromCart(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            service.removeFromCart(product.get());
        }
    }

    @PutMapping("/cart/decrease/{id}")
    public void decreaseItemQuantity(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            service.decreaseItemCount(product.get());
        }
    }

    @PutMapping("/cart/increase/{id}")
    public void increaseItemQuantity(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            service.increaseItemCount(product.get());
        }
    }

    @GetMapping("/cart/size")
    public int getCartSize() {
        return service.getCartSize();
    }

    @GetMapping("/cart")
    public CartViewModel getCart() {
        return service.getCartVM();
    }

    @GetMapping("/session")
    public String getSession(HttpSession session) {
        return session.getId();
    }

}
