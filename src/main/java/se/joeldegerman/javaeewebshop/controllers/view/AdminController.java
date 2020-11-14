package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.joeldegerman.javaeewebshop.helpers.UserHelper;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.repositories.OrderRepository;
import se.joeldegerman.javaeewebshop.services.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    private ProductService productService;
    private OrderRepository orderRepository;

    public AdminController(ProductService productService, OrderRepository orderRepository) {
        this.productService = productService;
        this.orderRepository = orderRepository;
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

    @GetMapping("/admin/add/product")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("nameofuser", UserHelper.getUsernameFromLoggedInUser(SecurityContextHolder.getContext()));
        model.addAttribute("isAdmin", UserHelper.checkIfUserIsAdmin(SecurityContextHolder.getContext()));
        return "admin/CreateProduct";

    }

    @PostMapping("/admin/add/product")
    public String productForm(@ModelAttribute Product product) {
        productService.createProduct(product);
        return "redirect:/admin/index";
    }

    @GetMapping("/admin/get/order/nonsent")
    public String getNonSentOrders(Model model) {
        Optional<List<Order>> nonSentOrders = orderRepository.getNonSentOrders();
        if (nonSentOrders.isPresent()) model.addAttribute("nonSentOrders", nonSentOrders.get());
        return "admin/Test";
    }

    @GetMapping("/admin/get/order/send")
    public String sendOrders() {
        Optional<List<Order>> nonSentOrders = orderRepository.getNonSentOrders();
        if (nonSentOrders.isPresent()) {
            for (var order: nonSentOrders.get()) {
                order.setOrderSent(true);
                orderRepository.save(order);
            }
        }
        return "redirect:/admin/get/order/nonsent";
    }
}
