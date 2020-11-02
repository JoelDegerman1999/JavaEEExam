package se.joeldegerman.javaeewebshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.joeldegerman.javaeewebshop.models.Cart;
import se.joeldegerman.javaeewebshop.services.CartService;

@Controller
public class CartController {

    private CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        service.addToCart();
        System.out.println(service.getCart());
        model.addAttribute("shoppingcart", service.getCart());
        return "Cart";
    }
}
