package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.joeldegerman.javaeewebshop.helpers.UserHelper;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.repositories.ProductRepository;
import se.joeldegerman.javaeewebshop.services.interfaces.CartService;
import se.joeldegerman.javaeewebshop.services.CartServiceImpl;

import java.util.Optional;

@Controller
public class CartController {

    private CartService cartService;
    private ProductRepository productRepository;

    public CartController(CartServiceImpl service, ProductRepository productRepository) {
        this.cartService = service;
        this.productRepository = productRepository;
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("cart", cartService.getCartVM());
        return "Cart";
    }

    @PostMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            cartService.addToCart(product.get());
        }
        return "redirect:/";
    }



}
