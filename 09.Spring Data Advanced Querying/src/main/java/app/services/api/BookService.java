package app.services.api;

import app.entities.Book;
import app.dto.ReducedBook;
import app.enums.AgeRestriction;
import java.util.Date;
import java.util.List;

public interface BookService {
    Book findByID(Long id);

    void remove(Book object);

    List<Book> findAll();

    void save(Book object);

    List<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findGoldBooksWithCopiesLessThan5000();

    List<Book> findBooksWithPriceLowerThan5AndMoreThan40();

    List<Book> findBooksNotReleasedInYear(int year);

    List<Book> findBooksReleasedBefore(Date date);

    List<Book> findBooksWithTitleContaining(String pattern);

    List<Book> findBooksWithAuthorNameStartingWith(String pattern);

    int findBooksWithTitleLongerThan(int length);

    ReducedBook findBookByTitle(String title);

    int increaseCopiesForBooksReleasedAfter(Date date, int number);

    int removeBooksWithCopiesLessThan(int number);
}
