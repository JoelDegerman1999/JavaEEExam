package se.joeldegerman.javaeewebshop.services.interfaces;

import org.springframework.data.domain.Sort;
import se.joeldegerman.javaeewebshop.models.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll(Sort sort);
    Category getByCategoryName(String name);
}
