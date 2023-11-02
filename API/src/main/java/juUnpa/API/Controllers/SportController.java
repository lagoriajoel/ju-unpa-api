package juUnpa.API.Controllers;


import juUnpa.API.Entities.Sport;
import juUnpa.API.Services.sportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/disciplinas")
@CrossOrigin("*")
public class SportController {
    @Autowired
    sportService sportService;


    @GetMapping("/list")
    public ResponseEntity<List<?>>SportList(){
        return new ResponseEntity<>(sportService.listar(), HttpStatus.OK);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> sportOfID(@PathVariable int id){
        Sport sport = sportService.listarporId(id).get();

        if(sport != null) {
            return new ResponseEntity<>(sportService.listarporId(id).get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Sport sport){
        return new ResponseEntity<>(sportService.guardar(sport),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Sport sport, @PathVariable int id){

        Optional<Sport> optionalDisciplinaDeportiva = sportService.listarporId(id);
        if(!optionalDisciplinaDeportiva.isPresent()){


            return ResponseEntity.unprocessableEntity().build();
        }

        sport.setId(optionalDisciplinaDeportiva.get().getId());
        sportService.guardar(sport);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){

        Optional<Sport> disciplinaDeportivaOptional = sportService.listarporId(id);

        if(!disciplinaDeportivaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        sportService.eliminar(disciplinaDeportivaOptional.get().getId());
        return ResponseEntity.ok().build();
    }
}
