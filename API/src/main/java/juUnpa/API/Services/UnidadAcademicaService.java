package juUnpa.API.Services;

import juUnpa.API.Entities.UnidadAcademica;

import java.util.List;
import java.util.Optional;

public interface UnidadAcademicaService {
    List<UnidadAcademica> listar();
    Optional<UnidadAcademica> listarPorId(int id);

    UnidadAcademica guardar(UnidadAcademica unidadAcademica);
    void eliminaar(int id);
}
