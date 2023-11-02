package juUnpa.API.Services;

import juUnpa.API.Entities.Program;

import java.util.List;
import java.util.Optional;

public interface ProgramService {

    List<Program> listar();

    List<Program> listarPorTorneo(int id);
    List<Program> listarPorDeporte(int id);

    Optional<Program> listarPorId(int id);
    Program guardar(Program program);

    List<Program> guardarTodos(List<Program> programas);

    void  eliminar(int id);

 }
