package juUnpa.API.Services;

import juUnpa.API.Entities.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> listar();
    Optional<Team> listarPorId(int id);

    Team guardar(Team team);

    List<Team> listarPorDisciplina(int id);

    List<Team> listarPorTorneo(int id);


    void eliminar(int id);
}
