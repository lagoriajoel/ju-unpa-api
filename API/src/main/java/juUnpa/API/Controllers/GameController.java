package juUnpa.API.Controllers;

import juUnpa.API.Entities.Game;
import juUnpa.API.Entities.Program;
import juUnpa.API.Services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/games")
@CrossOrigin("*")
public class GameController {
@Autowired
    MatchService gameService;


    @GetMapping("/list")
    public ResponseEntity<List<?>> listarJuegos(){
        return new ResponseEntity<>(gameService.listar(), HttpStatus.OK);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable int id){
        Optional<Game> program = gameService.listarPorId(id);

        if(program != null) {
            return new ResponseEntity<>(gameService.listarPorId(id).get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("listOfProgram/{id}")
    public ResponseEntity<?> obtenerPorPrograma(@PathVariable int id){

        List<Game> programList= gameService.listarPorPrograma(id);
        if (programList.isEmpty()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Mensaje", "No existen equipos para esta disciplina"));
        }
        return ResponseEntity.ok(programList);

    }

    @PostMapping("/save")
    public ResponseEntity<?> guardar(@RequestBody Game game){
        return new ResponseEntity<>(gameService.guardar(game),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Game game){

        Optional<Game> gameOptional=gameService.listarPorId(id);

        gameOptional.get().setFecha(game.getFecha());
        gameOptional.get().setLugar(game.getLugar());
        gameOptional.get().setHorario(game.getHorario());
        gameOptional.get().setScore_1(game.getScore_1());
        gameOptional.get().setScore_2(game.getScore_2());

        gameService.guardar(gameOptional.get());
        return  ResponseEntity.ok().build();


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id){
        gameService .eliminar(id);
        return ResponseEntity.ok().build();
    }
}