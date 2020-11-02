package se.joeldegerman.javaeewebshop.controllers;

import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import se.joeldegerman.javaeewebshop.models.Product;
import se.joeldegerman.javaeewebshop.services.ProductService;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        model.addAttribute("emptyProduct", new Product());
        return "Index";
    }

    @PostMapping("/product/create")
    public String createProduct(@ModelAttribute Product product, Model model) {
        productService.createProduct(product);
        model.addAttribute("products", productService.getAllProducts());
        return "redirect:/";
    }
}
