package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.services.CartServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.CartService;
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
        var a = "hej";
        model.addAttribute("cart", cartService.getCart());
        return "Cart";
    }

    @PostMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productService.getById(productId);
        product.ifPresent(cartService::addToCart);
        return "redirect:/";
    }

    //    AJAX METHODS
    @PostMapping("/ajax/cart/add/{id}")
    @ResponseBody
    public void addProductToCartAjax(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productService.getById(productId);
        product.ifPresent(cartService::addToCart);
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
        boolean cartSizeBiggerThanZero = cartService.getCartSize() > 0;
        return !cartSizeBiggerThanZero;
    }

}
