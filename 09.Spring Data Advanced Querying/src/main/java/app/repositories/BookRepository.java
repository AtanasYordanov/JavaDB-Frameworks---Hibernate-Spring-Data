package app.repositories;

import app.entities.Book;
import app.dto.ReducedBook;
import app.enums.AgeRestriction;
import app.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAgeRestrictionEquals(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int count);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal low, BigDecimal high);

    @Query("SELECT b FROM Book AS b WHERE YEAR(b.releaseDate) <> :year")
    List<Book> findBooksNotReleasedInYear(@Param(value = "year") int year);

    List<Book> findAllByReleaseDateBefore(Date date);

    List<Book> findAllByTitleContaining(String pattern);

    @Query("SELECT b FROM Book AS b WHERE b.author.lastName LIKE :pattern%")
    List<Book> findAllWithAuthorNameStartingWith(@Param(value = "pattern") String pattern);

    @Query("SELECT COUNT(b) FROM Book AS b WHERE LENGTH(b.title) > :len")
    int findAllWithTitleLongerThan(@Param(value = "len") int len);

    @Query("SELECT new app.dto.BookDto(b.title, b.editionType, b.ageRestriction, b.price) " +
            "FROM Book AS b WHERE b.title = :title")
    ReducedBook findBookByTitle(@Param(value = "title") String title);

    @Modifying
    @Query("UPDATE Book AS b " +
            "SET b.copies = b.copies + :number " +
            "WHERE b.releaseDate > :date")
    int increaseBookCopiesForBooksAfterYear(@Param(value = "date") Date date, @Param(value = "number") int number);

    @Modifying
    @Query("DELETE FROM Book AS b " +
            "WHERE b.copies < :number")
    int removeBooksWithCopiesLessThan(@Param(value = "number") int number);
}
