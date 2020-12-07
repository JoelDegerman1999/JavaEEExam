package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import se.joeldegerman.javaeewebshop.models.entity.Order;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.models.security.CustomUserDetail;
import se.joeldegerman.javaeewebshop.repositories.UserRepository;
import se.joeldegerman.javaeewebshop.services.CartServiceImpl;
import se.joeldegerman.javaeewebshop.services.ProductServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.CartService;
import se.joeldegerman.javaeewebshop.services.interfaces.CategoryService;
import se.joeldegerman.javaeewebshop.services.interfaces.OrderService;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final OrderService orderService;

    public HomeController(ProductServiceImpl productService, CategoryService categoryService, OrderService orderService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.orderService = orderService; }

    @GetMapping("/")
    public String index(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/profile")
    public String getUserProfile(@AuthenticationPrincipal CustomUserDetail user, Model model) {
        List<Order> orders = orderService.findOrderByUsername(user.getUsername());
        model.addAttribute("orders", orders);
        model.addAttribute("user", user);
        return "Profile";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable long id, Model model) {
        Optional<Product> optionalProduct = productService.getById(id);
        if (optionalProduct.isPresent()) {
            model.addAttribute("product", optionalProduct.get());
            return "ProductDetail";
        }
        return index(model);
    }

    @GetMapping("/category/{category}")
    public String productsByCategory(@PathVariable String category, Model model) {
        model.addAttribute("products", productService.findAllByCategory(category));
        model.addAttribute("categories", categoryService.getAll(Sort.by(Sort.Direction.ASC, "categoryName")));
        return "Index";
    }

    @GetMapping("search")
    public String search(@RequestParam(value = "s", required = false) String search, Model model) {
        model.addAttribute("categories", categoryService.getAll(Sort.by(Sort.Direction.ASC, "categoryName")));
        if (search != null) {
            if (!search.isBlank()) {
                model.addAttribute("products", productService.search(search));
                model.addAttribute("search", search);
            } else {
                return index(model);
            }
        }
        return "Index";
    }

    @GetMapping("page")
    public String findPaginated(@RequestParam(value = "pageNo", required = false) Integer pageNo, Model model) {
        model.addAttribute("categories", categoryService.getAll(Sort.by(Sort.Direction.ASC, "categoryName")));
        if (pageNo != null) {
            Page<Product> productPage = productService.findPaginated(pageNo, 12);
            List<Product> products = productPage.getContent();
            // Creating new list since the above list is immutable, and therefore can't sort it
            List<Product> sortedList = new ArrayList<>(products);
            sortedList.sort(Comparator.comparing(Product::getId));
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", productPage.getTotalPages());
            model.addAttribute("totalItems", productPage.getTotalElements());
            model.addAttribute("products", sortedList);
        }
        return "Index";
    }
}
