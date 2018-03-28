package app.services.api;

import app.entities.Author;

import java.util.List;

public interface AuthorService {
    Author findByID(Long id);

    void remove(Author object);

    List<Author> findAll();

    void save(Author object);

    List<Author> findAllByOrderByBooksDesc();

    List<Author> findAuthorsByBooksReleaseDateBefore1990();
}
