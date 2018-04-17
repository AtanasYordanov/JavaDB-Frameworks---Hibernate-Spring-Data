package app.repositories;

import app.model.entities.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Long>{
    Photographer findByFirstNameAndLastName(String firstName, String lastName);

    List<Photographer> findAllByOrderByFirstNameAscLastNameDesc();

    @Query(value = "SELECT * FROM photographers AS p\n" +
            "JOIN cameras AS c\n" +
            "ON p.primary_camera_id = c.id\n" +
            "JOIN lenses AS l\n" +
            "ON p.id = l.owner_id\n" +
            "\tAND l.focal_length <= 30\n" +
            "ORDER BY p.first_name", nativeQuery = true)
    List<Photographer> getLandscapePhotographers();

    @Query("SELECT p FROM Photographer AS p " +
            "JOIN p.primaryCamera AS pc " +
            "JOIN p.secondaryCamera AS sc " +
            "WHERE pc.make = sc.make AND " +
            "p.lenses.size > 0")
    List<Photographer> getSameCameraPhotographers();
}
