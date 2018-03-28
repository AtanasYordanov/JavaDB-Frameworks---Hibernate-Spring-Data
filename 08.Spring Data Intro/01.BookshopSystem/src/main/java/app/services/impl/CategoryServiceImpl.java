package app.services.impl;

import app.entities.Category;
import app.repositories.CategoryRepository;
import app.services.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findByID(Long id) {
        return this.categoryRepository.getOne(id);
    }

    @Override
    public void remove(Category object) {
        this.categoryRepository.delete(object);
    }

    @Override
    public List<Category> findAll() {
       return this.categoryRepository.findAll();
    }

    @Override
    public void save(Category object) {
        this.categoryRepository.save(object);
    }
}
