package juUnpa.API.Repositories;

import juUnpa.API.Entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TourmentRepository extends JpaRepository<Tournament, Integer> {

    @Query(value = "SELECT * FROM ju_unpa.tournament WHERE sport_id=:id", nativeQuery=true)
    Optional<Tournament> findByTourmentOfSport(int id);


}
