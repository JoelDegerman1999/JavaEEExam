package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.helpers.UserHelper;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.services.ProductService;

import java.util.List;

@Controller
public class AdminController {

    private ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin/index")
    public String showAdminIndex(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        model.addAttribute("nameofuser", UserHelper.getUsernameFromLoggedInUser(SecurityContextHolder.getContext()));
        model.addAttribute("isAdmin", UserHelper.checkIfUserIsAdmin(SecurityContextHolder.getContext()));
        return "/admin/Index";
    }

    @GetMapping("/admin/update/{id}")
    public String showUpdatePage(@PathVariable(name = "id") long productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "admin/Update";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id")long productId) {
        productService.deleteProduct(productId);
        return "redirect:/admin/index";
    }

    @GetMapping("/admin/add/product")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/CreateProduct";
    }
}
