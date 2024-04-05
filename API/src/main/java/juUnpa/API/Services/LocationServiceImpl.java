package juUnpa.API.Services;

import juUnpa.API.Entities.Location;
import juUnpa.API.Entities.information;
import juUnpa.API.Repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LocationServiceImpl implements LocationService{


    @Autowired
    LocationRepository locationRepository;
    @Override
    public List<Location> listar() {
        return locationRepository.findAll();
    }

    @Override
    public Optional<Location> listarPorId(int id) {
        return locationRepository.findById(id);
    }

    @Override
    public Location guardar(Location Location) {
        return locationRepository.save(Location);
    }

    @Override
    public void eliminar(int id) {
   locationRepository.deleteById(id);
    }
}
