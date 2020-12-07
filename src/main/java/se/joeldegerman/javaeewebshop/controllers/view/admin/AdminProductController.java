package se.joeldegerman.javaeewebshop.controllers.view.admin;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.repositories.CategoryRepository;
import se.joeldegerman.javaeewebshop.repositories.OrderRepository;
import se.joeldegerman.javaeewebshop.services.ProductServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product/")
public class AdminProductController {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    public AdminProductController(ProductServiceImpl productService, OrderRepository orderRepository, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("all")
    public String showAdminIndex(Model model) {
        return productsPaginated(1, model);
    }

    @GetMapping("page")
    public String productsPaginated(@RequestParam(value = "pageNo", required = false) Integer pageNo, Model model) {
        if (pageNo != null) {
            Page<Product> productPage = productService.findPaginated(pageNo, 10);
            List<Product> products = productPage.getContent();
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", productPage.getTotalPages());
            model.addAttribute("totalItems", productPage.getTotalElements());
            model.addAttribute("products", products);
        }
        return "Admin/Product/Index";
    }

    @GetMapping("update/{id}")
    public String showUpdatePage(@PathVariable(name = "id") long productId, Model model) {
        Optional<Product> optionalProduct = productService.getById(productId);
        model.addAttribute("categories", categoryRepository.findAll());
        optionalProduct.ifPresent(product -> model.addAttribute("product", product));
        return "Admin/Product/Update";
    }

    @PostMapping("update/{id}")
    public RedirectView updateProduct(@ModelAttribute Product product, @PathVariable long id) {
        var redirectView = new RedirectView();
        product.setId(id);
        Product updatedProduct = productService.update(product);
        if (updatedProduct != null) {
            redirectView.setUrl("/admin/product/all");
        } else {
            redirectView.setUrl("/admin/product/update/" + id);
        }
        return redirectView;
    }

    @GetMapping("create")
    public String productForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        return "Admin/Product/Create";

    }

    @PostMapping("create")
    public String productForm(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "Admin/Product/Create";
        }
        productService.create(product);
        return "redirect:/admin/product/all";
    }

    @PostMapping("delete/{id}")
    public RedirectView deleteProduct(@PathVariable long id) {
        productService.delete(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/admin/product/all");
        return redirectView;
    }

}
