package se.joeldegerman.javaeewebshop.controllers.rest;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.joeldegerman.javaeewebshop.models.dto.CategoryDto;
import se.joeldegerman.javaeewebshop.models.dto.ProductDto;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product/")
public class ProductRestController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductRestController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("all")
    public List<ProductDto> all() {
        return modelMapper.map(productService.getAll(), new TypeToken<List<ProductDto>>() {
        }.getType());
    }

    @GetMapping("{id}")
    public Optional<ProductDto> findById(@PathVariable long id) {
        Optional<Product> optionalProduct = productService.getById(id);
        return optionalProduct.map(this::convertToDto);
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

}
