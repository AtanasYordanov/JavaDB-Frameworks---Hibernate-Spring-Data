package app.services.impl;

import app.entities.Book;
import app.repositories.BookRepository;
import app.services.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findByID(Long id) {
        return this.bookRepository.getOne(id);
    }

    @Override
    public void remove(Book object) {
        this.bookRepository.delete(object);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public void save(Book object) {
        this.bookRepository.save(object);
    }

    @Override
    public List<String> findAllBooksAfter2000() {
        return this.bookRepository.findAllBooksAfter2000();
    }

    @Override
    public List<Book> findBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateAndTitle() {
        return this.bookRepository.findBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc("George", "Powell");
    }
}
