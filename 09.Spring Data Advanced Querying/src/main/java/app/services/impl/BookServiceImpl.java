package app.services.impl;

import app.entities.Book;
import app.dto.ReducedBook;
import app.enums.AgeRestriction;
import app.enums.EditionType;
import app.repositories.BookRepository;
import app.services.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
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
    public List<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findAllByAgeRestrictionEquals(ageRestriction);
    }

    @Override
    public List<Book> findGoldBooksWithCopiesLessThan5000() {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000);
    }

    @Override
    public List<Book> findBooksWithPriceLowerThan5AndMoreThan40() {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40));
    }

    @Override
    public List<Book> findBooksNotReleasedInYear(int year) {
        return this.bookRepository.findBooksNotReleasedInYear(year);
    }

    @Override
    public List<Book> findBooksReleasedBefore(Date date) {
        return this.bookRepository.findAllByReleaseDateBefore(date);
    }

    @Override
    public List<Book> findBooksWithTitleContaining(String pattern) {
        return this.bookRepository.findAllByTitleContaining(pattern);
    }

    @Override
    public List<Book> findBooksWithAuthorNameStartingWith(String pattern) {
        return this.bookRepository.findAllWithAuthorNameStartingWith(pattern);
    }

    @Override
    public int findBooksWithTitleLongerThan(int length) {
        return this.bookRepository.findAllWithTitleLongerThan(length);
    }

    @Override
    public ReducedBook findBookByTitle(String title) {
        return this.bookRepository.findBookByTitle(title);
    }

    @Override
    public int increaseCopiesForBooksReleasedAfter(Date date, int number) {
        return this.bookRepository.increaseBookCopiesForBooksAfterYear(date, number);
    }

    @Override
    public int removeBooksWithCopiesLessThan(int number) {
        return this.bookRepository.removeBooksWithCopiesLessThan(number);
    }
}
