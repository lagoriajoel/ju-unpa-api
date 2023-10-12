package juUnpa.API.Services;

import juUnpa.API.Entities.Game;

import java.util.List;
import java.util.Optional;

public interface MatchService {

    List<Game> listar();
    List<Game> listarPorPrograma(int id);

    Optional<Game> listarPorId(int id);

    Game guardar(Game game);

    void eliminar(int id);

}
