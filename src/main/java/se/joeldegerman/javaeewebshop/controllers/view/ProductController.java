package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.joeldegerman.javaeewebshop.models.Product;
import se.joeldegerman.javaeewebshop.models.viewmodels.CartViewModel;
import se.joeldegerman.javaeewebshop.services.CartService;
import se.joeldegerman.javaeewebshop.services.ProductService;

import java.util.List;

@Controller
public class ProductController {

    final ProductService productService;
    final CartService cartService;

    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        CartViewModel cartVM = cartService.getCartVM();
        model.addAttribute("products", allProducts);
        model.addAttribute("cart", cartVM);
        return "Index";
    }

}
