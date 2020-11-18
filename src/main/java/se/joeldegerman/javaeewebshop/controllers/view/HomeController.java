package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.models.viewmodels.CartViewModel;
import se.joeldegerman.javaeewebshop.services.CartServiceImpl;
import se.joeldegerman.javaeewebshop.services.ProductServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.CartService;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import java.util.List;

@Controller
public class HomeController {

    final ProductService productService;
    final CartService cartService;

    public HomeController(ProductServiceImpl productService, CartServiceImpl cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        CartViewModel cartVM = cartService.getCartVM();
        model.addAttribute("keyword", keyword);
        model.addAttribute("cart", cartVM);
        if (keyword == null) {
            List<Product> allProducts = productService.getAllProducts();
            model.addAttribute("products", allProducts);
        } else {
            List<Product> products = productService.searchProducts(keyword);
            model.addAttribute("products", products);
        }
        return "Index";
    }
}
