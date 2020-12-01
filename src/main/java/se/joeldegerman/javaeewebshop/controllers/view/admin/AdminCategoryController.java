package se.joeldegerman.javaeewebshop.controllers.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import se.joeldegerman.javaeewebshop.models.entity.Category;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.repositories.CategoryRepository;

import java.util.List;

@Controller
@RequestMapping("admin/")
public class AdminCategoryController {

    private final CategoryRepository categoryRepository;

    public AdminCategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("category/all")
    public String index(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "Admin/Category/Index";
    }

    @GetMapping("category/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "Admin/Category/Create";
    }

    @PostMapping("category/create")
    public RedirectView createCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        var redirectView = new RedirectView();
        redirectView.setUrl("/admin/category/all");
        return redirectView;
    }

}
