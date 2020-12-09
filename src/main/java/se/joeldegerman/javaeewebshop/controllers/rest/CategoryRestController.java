package se.joeldegerman.javaeewebshop.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.joeldegerman.javaeewebshop.models.entity.Category;
import se.joeldegerman.javaeewebshop.services.interfaces.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryRestController {
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("all")
    public List<Category> all() {
        return categoryService.getAll();
    }

    @GetMapping("{categoryName}")
    public Optional<Category> getByName(@PathVariable String categoryName) {
        return categoryService.getByCategoryName(categoryName);
    }
}
