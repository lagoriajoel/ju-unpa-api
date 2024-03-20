package juUnpa.API.Controllers;

import juUnpa.API.Entities.UnidadAcademica;
import juUnpa.API.Services.UnidadAcademicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/unidadesAcademicas")
@CrossOrigin("*")
public class UnidadAcademicaController {
        @Autowired
        UnidadAcademicaService unidadAcademicaService;

    @GetMapping("/list")
    public ResponseEntity<List<?>> listarUnidades(){
        return new ResponseEntity<>(unidadAcademicaService.listar(), HttpStatus.OK);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> obtenerUnidadPorId(@PathVariable int id){
        UnidadAcademica unidadAcademica = unidadAcademicaService.listarPorId(id).get();

        if(unidadAcademica != null) {
            return new ResponseEntity<>(unidadAcademicaService.listarPorId(id).get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardar(@RequestBody UnidadAcademica unidadAcademica){
        return new ResponseEntity<>(unidadAcademicaService.guardar(unidadAcademica),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> editar(@RequestBody UnidadAcademica unidad, @PathVariable int id){

        Optional<UnidadAcademica> optionalUnidadAcademica= unidadAcademicaService.listarPorId(id);
        if(!optionalUnidadAcademica.isPresent()){


            return ResponseEntity.unprocessableEntity().build();
        }

        unidad.setId(optionalUnidadAcademica.get().getId());
        unidadAcademicaService.guardar(unidad);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> eliminar(@PathVariable int id){
        unidadAcademicaService.eliminaar(id);
        return ResponseEntity.ok().build();
    }
}
