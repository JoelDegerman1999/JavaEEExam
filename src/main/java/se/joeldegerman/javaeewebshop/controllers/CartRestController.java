package se.joeldegerman.javaeewebshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import se.joeldegerman.javaeewebshop.models.Cart;
import se.joeldegerman.javaeewebshop.models.CartItem;
import se.joeldegerman.javaeewebshop.models.CartViewModel;
import se.joeldegerman.javaeewebshop.models.Product;
import se.joeldegerman.javaeewebshop.services.CartService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CartRestController {
    private CartService service;

    public CartRestController(CartService service) {
        this.service = service;
    }

    @PostMapping("/api/cart/add")
    public void addToCart() {
        service.addToCart();
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
