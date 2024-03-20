package juUnpa.API.Services;

import juUnpa.API.Entities.Tournament;
import juUnpa.API.Repositories.TourmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TournamentServiceImpl implements TournamentService {
    @Autowired
    TourmentRepository torneoRepository;
    @Override
    public List<Tournament> listar() {
        return torneoRepository.findAll();
    }

    @Override
    public Optional<Tournament> listOfSport(int idSport) {
        return torneoRepository.findByTourmentOfSport(idSport);
    }

    @Override
    public Optional<Tournament> listarPorId(int id) {
        return torneoRepository.findById(id);
    }

    @Override
    public Tournament guardar(Tournament tournament) {
        return torneoRepository.save(tournament);
    }

    @Override
    public void elimnar(int id) {
        torneoRepository.deleteById(id);
    }
}
