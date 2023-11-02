package juUnpa.API.Repositories;

import juUnpa.API.Entities.Program;
import juUnpa.API.Entities.Tourment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TourmentRepository extends JpaRepository<Tourment, Integer> {

    @Query(value = "SELECT * FROM ju_unpa.tourment WHERE sport_id=:id", nativeQuery=true)
    Optional<Tourment> findByTourmentOfSport(int id);


}
