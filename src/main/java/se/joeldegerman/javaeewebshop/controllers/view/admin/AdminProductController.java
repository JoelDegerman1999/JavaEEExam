package se.joeldegerman.javaeewebshop.controllers.view.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.joeldegerman.javaeewebshop.helpers.UserHelper;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.repositories.OrderRepository;
import se.joeldegerman.javaeewebshop.services.ProductServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import java.util.List;

@Controller
@RequestMapping("/admin/")
public class AdminProductController {

    private ProductService productService;
    private OrderRepository orderRepository;

    public AdminProductController(ProductServiceImpl productService, OrderRepository orderRepository) {
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("index")
    public String showAdminIndex(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        System.out.println(allProducts);
        model.addAttribute("products", allProducts);
        model.addAttribute("nameofuser", UserHelper.getUsernameFromLoggedInUser(SecurityContextHolder.getContext()));
        model.addAttribute("isAdmin", UserHelper.checkIfUserIsAdmin(SecurityContextHolder.getContext()));
        return "/admin/product/Index";
    }

    @GetMapping("update/{id}")
    public String showUpdatePage(@PathVariable(name = "id") long productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        return "admin/product/Update";
    }

    @PostMapping("update/{id}")
    public String updateProduct(@ModelAttribute Product product, @PathVariable long id) {
        product.setId(id);
        Product updatedProduct = productService.updateProduct(product);
        if(updatedProduct != null) return "redirect:/admin/index";
        return "redirect:/admin/update/" + product.getId();
    }

    @GetMapping("add/product")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("nameofuser", UserHelper.getUsernameFromLoggedInUser(SecurityContextHolder.getContext()));
        model.addAttribute("isAdmin", UserHelper.checkIfUserIsAdmin(SecurityContextHolder.getContext()));
        return "admin/product/CreateProduct";

    }

    @PostMapping("add/product")
    public String productForm(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/admin/index";
    }
}
