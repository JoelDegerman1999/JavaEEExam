package se.joeldegerman.javaeewebshop.controllers.rest.admin;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.joeldegerman.javaeewebshop.models.dto.CategoryDto;
import se.joeldegerman.javaeewebshop.models.dto.ProductDto;
import se.joeldegerman.javaeewebshop.models.entity.Category;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.services.ProductServiceImpl;
import se.joeldegerman.javaeewebshop.services.interfaces.CategoryService;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/product/")
public class AdminProductRestController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public AdminProductRestController(ProductServiceImpl productService, CategoryService categoryService, ModelMapper modelMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("all")
    public List<Product> all() {
        return productService.getAll();
    }

    @GetMapping("{id}")
    public Optional<Product> getById( @PathVariable long id) {
        return productService.getById(id);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable long id) {
        productService.delete(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody @Valid ProductDto productDto, BindingResult result) {
        if(result.hasErrors()) {
            ResponseEntity.badRequest().build();
        }
        Product product = convertToEntity(productDto);
        product.setId(id);
        productService.update(product);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("create")
    public ResponseEntity<Optional<Product>> create(@RequestBody @Valid ProductDto productDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Category> optionalCategory = categoryService.getByCategoryName(productDto.getCategory().getCategoryName());
        Product product = productService.create(convertToEntity(productDto));
        optionalCategory.ifPresent(product::setCategory);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(Optional.of(product));
    }



    /*
    Mapper methods
     */

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        CategoryDto categoryDto = modelMapper.map(product.getCategory(), CategoryDto.class);
        productDto.setCategory(categoryDto);
        return productDto;
    }

    private Product convertToEntity(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Optional<Category> optionalCategory = categoryService.getByCategoryName(productDto.getCategory().getCategoryName());
        optionalCategory.ifPresent(product::setCategory);
        if(optionalCategory.isEmpty()) {
            product.setCategory(new Category(productDto.getCategory().getCategoryName()));
        }
        return product;
    }
}
