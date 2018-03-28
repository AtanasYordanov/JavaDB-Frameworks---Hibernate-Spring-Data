package app.repositories;

import app.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT b.title FROM books b WHERE year(b.release_date) > '2000'", nativeQuery = true)
    List<String> findAllBooksAfter2000();

    List<Book> findBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String authorFirstName, String authorLastName);
}
