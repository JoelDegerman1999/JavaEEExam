package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.repositories.ProductRepository;
import se.joeldegerman.javaeewebshop.services.interfaces.CartService;
import se.joeldegerman.javaeewebshop.services.CartServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import java.util.Optional;

@Controller
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartServiceImpl service, ProductService productService) {
        this.cartService = service;
        this.productService = productService;
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("cart", cartService.getCartVM());
        return "Cart";
    }

    @PostMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productService.getById(productId);
        if (product.isPresent()) {
            cartService.addToCart(product.get());
        }
        return "redirect:/";
    }


//    AJAX METHODS
    @PostMapping("/ajax/cart/add/{id}")
    @ResponseBody
    public void addProductToCartAjax(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productService.getById(productId);
        if (product.isPresent()) {
            cartService.addToCart(product.get());
        }
    }

    @PutMapping("/ajax/cart/decrease/{id}")
    @ResponseBody
    public void decreaseItemQuantity(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productService.getById(productId);
        product.ifPresent(cartService::decreaseItemCount);
    }

    @PutMapping("/ajax/cart/increase/{id}")
    @ResponseBody
    public void increaseItemQuantity(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productService.getById(productId);
        product.ifPresent(cartService::increaseItemCount);
    }

    @GetMapping("/ajax/cart/notempty")
    @ResponseBody
    public boolean checkIfCartIsNotEmpty() {
        boolean b = cartService.getCartSize() > 0;
        return !b;
    }


}
