package juUnpa.API.Repositories;

import juUnpa.API.Entities.Game;
import juUnpa.API.Entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
