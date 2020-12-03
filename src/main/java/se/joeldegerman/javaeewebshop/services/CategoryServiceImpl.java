package se.joeldegerman.javaeewebshop.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.joeldegerman.javaeewebshop.models.entity.Category;
import se.joeldegerman.javaeewebshop.repositories.CategoryRepository;
import se.joeldegerman.javaeewebshop.services.interfaces.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll(Sort sort) {
        return categoryRepository.findAll(sort);
    }

    @Override
    public Category getByCategoryName(String name) {
        Optional<Category> optionalCategory = categoryRepository.findByCategoryName(name);
        if(optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        return null;
    }
}
