package juUnpa.API.Services;

import juUnpa.API.Entities.information;
import juUnpa.API.Repositories.infoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    infoRepository infoRepository;
    @Override
    public List<information> listar() {
        return infoRepository.findAll();
    }



    @Override
    public Optional<information> listarPorId(int id) {
        return infoRepository.findById(id);
    }

    @Override
    public information guardar(information information) {
        return infoRepository.save(information);
    }

    @Override
    public void eliminar(int id) {

        infoRepository.deleteById(id);

    }
}
