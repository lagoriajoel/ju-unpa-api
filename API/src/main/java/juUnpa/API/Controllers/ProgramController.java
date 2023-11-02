package juUnpa.API.Controllers;

import juUnpa.API.Entities.Program;
import juUnpa.API.Entities.Team;
import juUnpa.API.Services.ProgramService;
import juUnpa.API.Services.TourmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/programs")
@CrossOrigin("*")
public class ProgramController {
    @Autowired
    ProgramService programService;
    @Autowired
    TourmentService tourmentService;

    @GetMapping("/list")
    public ResponseEntity<List<?>> programsList(){
        return new ResponseEntity<>(programService.listar(), HttpStatus.OK);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> programsOfID(@PathVariable int id){

        Optional<Program> program = programService.listarPorId(id);

        if(program != null) {
            return new ResponseEntity<>(programService.listarPorId(id).get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("listOfTourment/{id}")
    public ResponseEntity<?> programsOfTourment(@PathVariable int id){

        List<Program> programList= programService.listarPorTorneo(id);
        if (programList.isEmpty()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje", "No existen equipos para esta disciplina"));
        }
        return ResponseEntity.ok(programList);

    }

    @GetMapping("listOfSport/{id}")
    public ResponseEntity<?> programsOfSport(@PathVariable int id){

        List<Program> programList= programService.listarPorDeporte(id);
        if (programList.isEmpty()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje", "No existen equipos para esta disciplina"));
        }
        return ResponseEntity.ok(programList);

    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Program program){
        return new ResponseEntity<>(programService.guardar(program),HttpStatus.CREATED);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<Program> programs){
        return new ResponseEntity<>(programService.guardarTodos(programs),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        programService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}