package juUnpa.API.Repositories;

import juUnpa.API.Entities.Game;
import juUnpa.API.Entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Game, Integer> {

    @Query(value = "SELECT * FROM ju_unpa.game where program_id=:id", nativeQuery=true)
    List<Game> findGamesByPrograms(int id);

}
