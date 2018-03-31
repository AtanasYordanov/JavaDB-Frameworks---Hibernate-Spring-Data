package app.services.api;

import app.entities.Category;

import java.util.List;

public interface CategoryService {
    Category findByID(Long id);

    void remove(Category object);

    List<Category> findAll();

    void save(Category object);
}
