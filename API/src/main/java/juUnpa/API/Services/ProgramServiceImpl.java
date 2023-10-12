package juUnpa.API.Services;

import juUnpa.API.Entities.Program;
import juUnpa.API.Repositories.TourmentProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramServiceImpl implements ProgramService {
    @Autowired
    TourmentProgramRepository programRepository;

    @Override
    public List<Program> listar() {
        return programRepository.findAll();
    }

    @Override
    public List<Program> listarPorTorneo(int id) {
        return programRepository.findByProgramsOfTourment(id);
    }

    @Override
    public Optional<Program> listarPorId(int id) {
        return programRepository.findById(id);
    }

    @Override
    public Program guardar(Program program) {
        return programRepository.save(program);
    }

    @Override
    public void eliminar(int id) {
           programRepository.deleteById(id);
    }
}
