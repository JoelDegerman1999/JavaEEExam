package se.joeldegerman.javaeewebshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.Cart;
import se.joeldegerman.javaeewebshop.models.CartItem;
import se.joeldegerman.javaeewebshop.models.CartViewModel;
import se.joeldegerman.javaeewebshop.models.Product;
import se.joeldegerman.javaeewebshop.repositories.ProductRepository;
import se.joeldegerman.javaeewebshop.services.CartService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class CartRestController {
    private CartService service;
    private ProductRepository productRepository;

    public CartRestController(CartService service, ProductRepository productRepository) {
        this.service = service;
        this.productRepository = productRepository;
    }

    @PostMapping("/api/cart/add")
    public void addToCart() {
    }

    @DeleteMapping("/cart/delete/{id}")
    public void deleteProductFromCart(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            service.removeFromCart(product.get());
        }
    }

    @GetMapping("/api/cart/size")
    public int getCartSize() {
        return service.getCartSize();
    }


    @GetMapping("/api/cart")
    public CartViewModel getCart() {
        return service.getCart();
    }

    @GetMapping("/api/session")
    public String getSession(HttpSession session) {
        return session.getId();
    }



}
