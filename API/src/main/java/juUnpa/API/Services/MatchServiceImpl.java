package juUnpa.API.Services;

import juUnpa.API.Entities.Game;
import juUnpa.API.Repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService{
    @Autowired
    MatchRepository matchRepository;

    @Override
    public List<Game> listar() {
        return matchRepository.findAll();
    }

    @Override
    public List<Game> listarPorPrograma(int id) {
        return matchRepository.findGamesByPrograms(id);
    }

    @Override
    public Optional<Game> listarPorId(int id) {
        return matchRepository.findById(id);
    }

    @Override
    public Game guardar(Game game) {
        return matchRepository.save(game);
    }

    @Override
    public void eliminar(int id) {
                    matchRepository.deleteById(id);
    }
}
