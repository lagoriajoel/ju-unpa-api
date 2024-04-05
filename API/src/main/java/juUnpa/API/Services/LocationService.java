package juUnpa.API.Services;

import juUnpa.API.Entities.Location;
import juUnpa.API.Entities.information;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> listar();

    Optional<Location> listarPorId(int id);

    Location guardar(Location Location);

    void eliminar(int id);
}
