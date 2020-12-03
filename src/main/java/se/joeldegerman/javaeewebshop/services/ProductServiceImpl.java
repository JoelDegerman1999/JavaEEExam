package se.joeldegerman.javaeewebshop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.entity.Category;
import se.joeldegerman.javaeewebshop.models.entity.Product;
import se.joeldegerman.javaeewebshop.repositories.CategoryRepository;
import se.joeldegerman.javaeewebshop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements se.joeldegerman.javaeewebshop.services.interfaces.ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Product> getById(long id) {
        return repository.findById(id);
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public void delete(long id) {
        Optional<Product> optional = getById(id);
        optional.ifPresent(repository::delete);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = repository.findAll();
        products.sort(Comparator.comparing(Product::getId));
        return products;
    }

    @Override
    public List<Product> search(String keyword) {
        Optional<List<Product>> optionalProducts = repository.searchProduct(keyword);
        if (optionalProducts.isPresent()) {
            return optionalProducts.get();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Product> findAllByCategory(String categoryName) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(categoryName);
        if (optionalCategory.isPresent()) {
            Optional<List<Product>> optionalProducts = repository.findAllByCategory(optionalCategory.get().getId());
            if (optionalProducts.isPresent()) {
                List<Product> products = optionalProducts.get();
                products.sort(Comparator.comparing(Product::getId));
                return products;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public Page<Product> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page -1, size, Sort.by("id"));
        Page<Product> products = repository.findAll(pageable);
        return products;
    }
}
