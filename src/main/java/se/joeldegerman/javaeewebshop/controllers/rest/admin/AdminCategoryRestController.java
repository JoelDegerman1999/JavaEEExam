package se.joeldegerman.javaeewebshop.controllers.rest.admin;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.joeldegerman.javaeewebshop.models.dto.CategoryDto;
import se.joeldegerman.javaeewebshop.models.entity.Category;
import se.joeldegerman.javaeewebshop.services.interfaces.CategoryService;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin/category")
public class AdminCategoryRestController {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public AdminCategoryRestController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("all")
    public List<Category> all() {
        return categoryService.getAll();
    }

    @GetMapping("{id}")
    public Optional<Category> getById(@PathVariable long id) {
        return categoryService.getById(id);
    }

    @PostMapping("create")
    public ResponseEntity<Category> create(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.create(modelMapper.map(categoryDto, Category.class));
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).body(category);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Category> update(@PathVariable long id, @RequestBody Category category) {
        category.setId(id);
        categoryService.update(category);
        return ResponseEntity.noContent().build();
    }


}
