package se.joeldegerman.javaeewebshop.controllers.rest;

import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.models.viewmodels.CartViewModel;
import se.joeldegerman.javaeewebshop.services.interfaces.CartService;
import se.joeldegerman.javaeewebshop.services.CartServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class CartRestController {
    private final CartService cartService;
    private final ProductService productService;

    public CartRestController(CartServiceImpl service, ProductService productService) {
        this.cartService = service;
        this.productService = productService;
    }


    @PostMapping("cart/add/{id}")
    public void addToCart(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productService.getById(productId);
        product.ifPresent(cartService::addToCart);
    }

    @DeleteMapping("cart/delete/{id}")
    public void deleteProductFromCart(@PathVariable(name = "id") long productId) {
        Optional<Product> product = productService.getById(productId);
        product.ifPresent(cartService::removeFromCart);
    }



    @GetMapping("/cart/size")
    public int getCartSize() {
        return cartService.getCartSize();
    }

    @GetMapping("/cart")
    public CartViewModel getCart() {
        return cartService.getCartVM();
    }

    @GetMapping("/session")
    public String getSession(HttpSession session) {
        return session.getId();
    }

}
