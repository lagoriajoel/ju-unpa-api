package juUnpa.API.Services;

import juUnpa.API.Entities.Game;
import juUnpa.API.Entities.information;

import java.util.List;
import java.util.Optional;

public interface InfoService {
    List<information> listar();

    Optional<information> listarPorId(int id);

    information guardar(information information);

    void eliminar(int id);
}
