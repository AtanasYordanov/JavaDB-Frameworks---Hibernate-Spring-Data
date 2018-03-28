package app.repositories;

import app.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "SELECT authors.id, authors.first_name, authors.last_name\n" +
            "  FROM (SELECT a.id, a.first_name, a.last_name, count(b.id) AS count\n" +
            "        FROM authors AS a\n" +
            "          INNER JOIN books AS b\n" +
            "            ON a.id = b.author_id\n" +
            "        GROUP BY a.id, a.first_name, a.last_name) AS authors\n" +
            "ORDER BY authors.count DESC;", nativeQuery = true)
    List<Author> findAuthorsByOrOrderByBooksDesc();

    @Query(value = "SELECT authors.id, authors.first_name, authors.last_name\n" +
            "  FROM (SELECT a.id, a.first_name, a.last_name, count(b.id) AS count\n" +
            "        FROM authors AS a\n" +
            "          INNER JOIN books AS b\n" +
            "            ON a.id = b.author_id\n" +
            "           AND year(b.release_date) < 1990\n" +
            "        GROUP BY a.id, a.first_name, a.last_name\n" +
            "       HAVING count(b.id) > 0) AS authors\n" +
            "ORDER BY authors.count DESC;\n", nativeQuery = true)
    List<Author> findAuthorsByBooksReleaseDateBefore1990();
}
