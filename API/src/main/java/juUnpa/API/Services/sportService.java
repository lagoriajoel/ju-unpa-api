package juUnpa.API.Services;

import juUnpa.API.Entities.Sport;

import java.util.List;
import java.util.Optional;

public interface sportService {

List<Sport> listar();
Optional<Sport> listarporId(int id);

Sport guardar(Sport sport);

void eliminar(int id);

}
