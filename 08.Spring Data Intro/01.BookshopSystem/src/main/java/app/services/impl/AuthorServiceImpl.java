package app.services.impl;

import app.entities.Author;
import app.repositories.AuthorRepository;
import app.services.api.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findByID(Long id) {
       return this.authorRepository.getOne(id);
    }

    @Override
    public void remove(Author object) {
        this.authorRepository.delete(object);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public void save(Author object) {
        this.authorRepository.save(object);
    }

    @Override
    public List<Author> findAllByOrderByBooksDesc() {
        return authorRepository.findAuthorsByOrOrderByBooksDesc();
    }

    @Override
    public List<Author> findAuthorsByBooksReleaseDateBefore1990() {
        return authorRepository.findAuthorsByBooksReleaseDateBefore1990();
    }


}
