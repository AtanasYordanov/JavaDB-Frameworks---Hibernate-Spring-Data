package app.repositories;

import app.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    List<Category> findAllByNameIsStartingWith(String string);
}
