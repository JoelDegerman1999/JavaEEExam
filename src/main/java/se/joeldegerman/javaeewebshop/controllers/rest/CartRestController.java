package se.joeldegerman.javaeewebshop.controllers.rest;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.models.Cart;
import se.joeldegerman.javaeewebshop.models.dto.CartDto;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.services.interfaces.CartService;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("api/cart/")
public class CartRestController {

    private final CartService cartService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public CartRestController(CartService cartService, ProductService productService, ModelMapper modelMapper) {
        this.cartService = cartService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("get")
    public CartDto getCartDto() {
        return convertToDto(cartService.getCart());
    }

    @PostMapping("add/{id}")
    public ResponseEntity<Object> addProduct(@PathVariable(name = "id") long productId) {
        productService.getById(productId).ifPresent(cartService::addToCart);
        return ResponseEntity.ok("Product added");
    }

    @PostMapping("decreaseCount/{productId}")
    public ResponseEntity<Object> decreaseItemCount(@PathVariable long productId) {
        Optional<Product> optionalProduct = productService.getById(productId);
        if (optionalProduct.isEmpty()) return ResponseEntity.badRequest().body("Item not found");
        if (cartService.isProductInCart(optionalProduct.get())) {
            cartService.decreaseItemCount(optionalProduct.get());
            return ResponseEntity.ok("Item count decreased");
        }
        return ResponseEntity.badRequest().body("Item not in cart");
    }

    @PostMapping("increaseCount/{productId}")
    public ResponseEntity<Object> increaseItemCount(@PathVariable long productId) {
        Optional<Product> optionalProduct = productService.getById(productId);
        if (optionalProduct.isEmpty()) return ResponseEntity.badRequest().body("Item not found");
        if (cartService.isProductInCart(optionalProduct.get())) {

            cartService.increaseItemCount(optionalProduct.get());
            return ResponseEntity.ok("Item count increased");
        }
        return ResponseEntity.badRequest().body("Item not in cart");
    }

    /*
    Mapper methods
     */
    private CartDto convertToDto(Cart cart) {
        return modelMapper.map(cart, CartDto.class);
    }
}
