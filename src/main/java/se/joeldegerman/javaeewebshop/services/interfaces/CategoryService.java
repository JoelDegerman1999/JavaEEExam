package se.joeldegerman.javaeewebshop.services.interfaces;

import org.springframework.data.domain.Sort;
import se.joeldegerman.javaeewebshop.models.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll(Sort sort);

    List<Category> getAll();

    Category create(Category category);

    Category update(Category category);

    Optional<Category> getById(long id);

    Optional<Category> getByCategoryName(String name);
}
