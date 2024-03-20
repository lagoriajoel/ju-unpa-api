package juUnpa.API.Repositories;

import juUnpa.API.Entities.Program;
import juUnpa.API.Entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourmentProgramRepository extends JpaRepository<Program, Integer> {
    @Query(value = "SELECT * FROM ju_unpa.program where tournament_id=:id", nativeQuery=true)
    List<Program> findByProgramsOfTourment(int id);

    @Query(value = "SELECT * FROM ju_unpa.program where program.tournament_id in (SELECT tournament.id FROM ju_unpa.tournament WHERE sport_id=:id)", nativeQuery=true)
    List<Program> findByProgramsOfSport(int id);

}
