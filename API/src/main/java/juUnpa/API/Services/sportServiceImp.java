package juUnpa.API.Services;

import juUnpa.API.Entities.Sport;
import juUnpa.API.Repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class sportServiceImp implements sportService {

    @Autowired
    SportRepository sportRepository;
    @Override
    public List<Sport> listar() {
        return sportRepository.findAll();
    }

    @Override
    public Optional<Sport> listarporId(int id) {
        return sportRepository.findById(id);
    }



    @Override
    public Sport guardar(Sport sport) {
        return sportRepository.save(sport);
    }

    @Override
    public void eliminar(int id) {
                sportRepository.deleteById(id);
    }
}
