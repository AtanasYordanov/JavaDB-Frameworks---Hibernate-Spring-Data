package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.model.entities.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    @Query(value = "SELECT * FROM races AS r " +
            "ORDER BY r.id DESC " +
            "LIMIT 1", nativeQuery = true)
    Race getLastRaceEntryId();
}
