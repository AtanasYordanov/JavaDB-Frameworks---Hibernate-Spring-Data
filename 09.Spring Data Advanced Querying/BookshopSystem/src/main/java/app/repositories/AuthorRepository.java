package app.repositories;

import app.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByFirstNameEndsWith(String end);

    @Query("SELECT CONCAT(a.firstName, ' ', a.lastName), " +
            "SUM(b.copies) AS copies " +
            "FROM Author AS a " +
            "JOIN Book AS b ON b.author = a " +
            "GROUP BY a.id " +
            "ORDER BY copies DESC")
    List<Object[]> getBookCopiesPerAuthor();

    @Procedure(name = "get_author_books_count")
    int getAuthorBooksCountProcedure(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
