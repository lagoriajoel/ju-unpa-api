package juUnpa.API.Repositories;

import juUnpa.API.Entities.Program;
import juUnpa.API.Entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourmentProgramRepository extends JpaRepository<Program, Integer> {
    @Query(value = "SELECT * FROM ju_unpa.program where tourment_id=:id", nativeQuery=true)
    List<Program> findByProgramsOfTourment(int id);

    @Query(value = "SELECT * FROM ju_unpa.program where program.tourment_id in (SELECT tourment.id FROM ju_unpa.tourment WHERE sport_id=:id)", nativeQuery=true)
    List<Program> findByProgramsOfSport(int id);

}
