package juUnpa.API.Services;

import juUnpa.API.Entities.UnidadAcademica;
import juUnpa.API.Repositories.UnidadAcademicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UnidadAcademicaServiceImpl implements UnidadAcademicaService {
    @Autowired
    UnidadAcademicaRepository repository;
    @Override
    public List<UnidadAcademica> listar() {
        return repository.findAll();
    }

    @Override
    public Optional<UnidadAcademica> listarPorId(int id) {
        return repository.findById(id);
    }

    @Override
    public UnidadAcademica guardar(UnidadAcademica unidadAcademica) {
        return repository.save(unidadAcademica);
    }

    @Override
    public void eliminaar(int id) {
         repository.deleteById(id);
    }
}
