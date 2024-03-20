package juUnpa.API.Services;

import juUnpa.API.Entities.Tournament;

import java.util.List;
import java.util.Optional;

public interface TournamentService {

    List<Tournament> listar();

    Optional<Tournament> listOfSport(int idSport);
    Optional<Tournament> listarPorId(int id);
    Tournament guardar(Tournament tournament);

    void elimnar(int id);
}
