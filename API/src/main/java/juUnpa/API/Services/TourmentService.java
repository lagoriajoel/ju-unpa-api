package juUnpa.API.Services;

import juUnpa.API.Entities.Tourment;

import java.util.List;
import java.util.Optional;

public interface TourmentService {

    List<Tourment> listar();

    Optional<Tourment> listOfSport(int idSport);
    Optional<Tourment> listarPorId(int id);
    Tourment guardar(Tourment tourment);

    void elimnar(int id);
}
