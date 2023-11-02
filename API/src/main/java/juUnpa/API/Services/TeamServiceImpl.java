package juUnpa.API.Services;

import juUnpa.API.Entities.Team;
import juUnpa.API.Entities.UnidadAcademica;
import juUnpa.API.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UnidadAcademicaService unidadAcademicaService;
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
          Optional<UnidadAcademica> unidadAcademicaOptional= unidadAcademicaService.listarPorId(team.getUnidadAcademica().getId());
          if (!unidadAcademicaOptional.isPresent())

          team.setUnidadAcademica(unidadAcademicaOptional.get());
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
