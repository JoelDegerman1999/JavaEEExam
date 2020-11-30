package se.joeldegerman.javaeewebshop.controllers.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.models.security.CustomUserDetail;
import se.joeldegerman.javaeewebshop.repositories.CategoryRepository;
import se.joeldegerman.javaeewebshop.repositories.UserRepository;
import se.joeldegerman.javaeewebshop.services.CartServiceImpl;
import se.joeldegerman.javaeewebshop.services.ProductServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.CartService;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import java.util.List;

@Controller
public class HomeController {

    final ProductService productService;
    final CartService cartService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public HomeController(ProductServiceImpl productService, CategoryRepository categoryRepository, CartServiceImpl cartService, UserRepository userRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/profile")
    public String getUserProfile(@AuthenticationPrincipal CustomUserDetail user, Model model) {
        userRepository.findByUsername(user.getUsername());
        model.addAttribute("user",user);
        return "Profile";
    }


    @GetMapping("/product/{id}")
    public String product(@PathVariable long id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "ProductDetail";
        }
        return index(model);
    }

    @GetMapping("/category/{category}")
    public String productsByCategory(@PathVariable String category, Model model) {
        model.addAttribute("products", productService.findAllProductsByCategory(category));
        model.addAttribute("categories", categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryName")));
        return "Index";
    }

    @GetMapping("search")
    public String search(@RequestParam(value = "s", required = false) String search, Model model) {
        model.addAttribute("categories", categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryName")));
        if (search != null) {
            if (!search.isBlank()) {
                model.addAttribute("products", productService.searchProducts(search));
                model.addAttribute("search", search);
            } else {
                return index(model);
            }
        }
        return "Index";
    }

    @GetMapping("page")
    public String findPaginated(@RequestParam(value = "pageNo", required = false) Integer pageNo, Model model) {
        model.addAttribute("categories", categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryName")));
        if (pageNo != null) {
            Page<Product> productPage = productService.findPaginated(pageNo, 12);
            List<Product> products = productPage.getContent();
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", productPage.getTotalPages());
            model.addAttribute("totalItems", productPage.getTotalElements());
            model.addAttribute("products", products);
        }
        return "Index";
    }
}
