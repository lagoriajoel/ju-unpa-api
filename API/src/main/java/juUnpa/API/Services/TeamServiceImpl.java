package juUnpa.API.Services;

import juUnpa.API.Entities.Team;
import juUnpa.API.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;
    @Override
    public List<Team> listar() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> listarPorId(int id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team guardar(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> listarPorDisciplina(int id) {
        return teamRepository.findByTeamsSport(id);
    }

    @Override
    public List<Team> listarPorTorneo(int id) {
        return teamRepository.findByTeamsOfTourment(id);
    }

    @Override
    public void eliminar(int id) {
   teamRepository.deleteById(id);
    }
}
