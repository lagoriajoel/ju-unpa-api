package juUnpa.API.Repositories;

import juUnpa.API.Entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    @Query(value = "SELECT * FROM ju_unpa.team where sport_id=:id", nativeQuery = true)
    List<Team> findByTeamsSport(int id);

    @Query(value = "SELECT * FROM ju_unpa.team where torneo_id=:id", nativeQuery = true)
    List<Team> findByTeamsOfTourment(int id);

    @Query(value = "SELECT * FROM team  where sport_id in (SELECT id FROM sport where id=:sport_id) and unidad_id=:unidad_id", nativeQuery = true)
    Optional<Team> findRepeat(int sport_id, int unidad_id);
}







