package se.joeldegerman.javaeewebshop.controllers.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import se.joeldegerman.javaeewebshop.models.entity.Category;
import se.joeldegerman.javaeewebshop.services.interfaces.CategoryService;

import java.util.List;

@Controller
@RequestMapping("admin/category")
public class AdminCategoryController {

    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("all")
    public String index(Model model) {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "Admin/Category/Index";
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "Admin/Category/Create";
    }

    @PostMapping("create")
    public RedirectView createCategory(@ModelAttribute Category category) {
        categoryService.create(category);
        var redirectView = new RedirectView();
        redirectView.setUrl("/admin/category/all");
        return redirectView;
    }

}
