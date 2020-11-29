package se.joeldegerman.javaeewebshop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.Category;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.repositories.CategoryRepository;
import se.joeldegerman.javaeewebshop.repositories.ProductRepository;
import se.joeldegerman.javaeewebshop.services.interfaces.ProductService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public Product getProductById(long id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) return product.get();
        return null;
    }

    public void createProduct(Product product) {
        repository.save(product);
    }

    public void deleteProduct(long id) {
        repository.delete(getProductById(id));
    }

    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        List<Product> products = repository.findAll();
        products.sort(Comparator.comparing(Product::getId));
        return products;
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        Optional<List<Product>> optionalProducts = repository.searchProduct(keyword);
        if (optionalProducts.isPresent()) {
            return optionalProducts.get();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Product> findAllProductsByCategory(String categoryName) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(categoryName);
        if (optionalCategory.isPresent()) {
            Optional<List<Product>> optionalProducts = repository.findAllByCategory(optionalCategory.get().getId());
            if (optionalProducts.isPresent()) {
                return optionalProducts.get();
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Page<Product> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page -1, size);
        return repository.findAll(pageable);
    }
}
