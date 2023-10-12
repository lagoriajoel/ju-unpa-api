package juUnpa.API.Services;

import juUnpa.API.Entities.Tourment;
import juUnpa.API.Repositories.TourmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TourmentServiceImpl implements TourmentService {
    @Autowired
    TourmentRepository torneoRepository;
    @Override
    public List<Tourment> listar() {
        return torneoRepository.findAll();
    }

    @Override
    public Optional<Tourment> listarPorId(int id) {
        return torneoRepository.findById(id);
    }

    @Override
    public Tourment guardar(Tourment tourment) {
        return torneoRepository.save(tourment);
    }

    @Override
    public void elimnar(int id) {
        torneoRepository.deleteById(id);
    }
}
