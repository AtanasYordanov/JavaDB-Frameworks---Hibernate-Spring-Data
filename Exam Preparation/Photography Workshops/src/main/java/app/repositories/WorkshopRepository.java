package app.repositories;

import app.model.entities.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Long>{
    @Query(value = "SELECT * FROM workshops AS w\n" +
            "JOIN workshops_participants AS wp\n" +
            "ON w.id = wp.workshop_id\n" +
            "GROUP BY w.id\n" +
            "HAVING COUNT(wp.participant_id >= 5)", nativeQuery = true)
    List<Workshop> getWorkshopsWithMoreThanFiveParticipants();
}
